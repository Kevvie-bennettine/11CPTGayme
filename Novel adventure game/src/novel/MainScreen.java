package novel;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import levels.DialoguePage;
import levels.Room;
import levels.SelectionPage;
import values.SpriteDescriptions;
import values.Values;

/**
 * the game-playing screen of the game,
 * contains all the gui objects which can contain sprites
 */
public class MainScreen
{
	//final constants for state of screen
	public static final int DIALOGUE = 0;
	public static final int SELECTION = 1;
	public static final int ROOMSELECTION = 2;
	public static final int SAVESELECTION = 3;
	
	Group root = new Group();
	SubScene screen = new SubScene(root, Values.screenwidth, Values.screenheight);
	
	//reference to the GamePivot
	GamePivot pivot;
	
	//the sprite for characters and selection section
	ImageView leftSprite;
	ImageView rightSprite;
	ImageView selectionSprite;
	
	//the selection screen for every end a room
	RoomSelectionPanel roomPanel;
	SubScene roomEndSelection;
	
	//the saving selection screen
	SaveSelectionPanel savePanel;
	SubScene saveSelection;
	
	//the sprite for background
	ImageView roomSprite;
	
	//the text pane(area of text)
	ImageView textSection;
	Text text;
	
	//the ImageView for the credit screen
	ImageView creditImage;
	
	//state of the screen, 0 is dialogue, 1 is selection, 2 is room selection
	int screenState = -1;
	//value to check if save panel is on
	boolean savePanelOn = false;
	
	SelectionPage currentSelection;
	DialoguePage currentDialogue;
	Room currentRoom;
	
	public MainScreen()
	{
		//create the imageview objects in constructor
		leftSprite = new ImageView();
		rightSprite = new ImageView();
		selectionSprite = new ImageView();
		roomSprite = new ImageView();
		
		//set the dimensions of the mutable sprites
		leftSprite.setX(0);
		leftSprite.setY(0);
		leftSprite.setFitWidth(Values.screenwidth / 2);
		leftSprite.setFitHeight(Values.pictureheight);
		rightSprite.setX(Values.screenwidth / 2);
		rightSprite.setY(0);
		rightSprite.setFitWidth(Values.screenwidth / 2);
		rightSprite.setFitHeight(Values.pictureheight);
		selectionSprite.setX(Values.screenwidth / 2 - Values.selectionSpriteDimensions / 2);
		selectionSprite.setY(Values.pictureheight / 2 - Values.selectionSpriteDimensions / 2);
		selectionSprite.setFitWidth(Values.selectionSpriteDimensions);
		selectionSprite.setFitHeight(Values.selectionSpriteDimensions);
		roomSprite.setX(0);
		roomSprite.setY(0);
		roomSprite.setFitWidth(Values.screenwidth);
		roomSprite.setFitHeight(Values.pictureheight);
		roomSprite.setPreserveRatio(true);
		
		//create the text section
		textSection = new ImageView();
		text = new Text();
		
		textSection.setX(0);
		textSection.setY(Values.pictureheight);
		textSection.setFitWidth(Values.screenwidth);
		textSection.setFitHeight(Values.textpanelheight);
		text.setX(Values.textpaneltextx);
		text.setY(Values.pictureheight + Values.textpaneltexty);
		text.setFont(new Font("Arial", 14));
		
		Image textSectionImage = SpriteDescriptions.globalSprites.get("textpane");
		textSection.setImage(textSectionImage);
		
		text.setWrappingWidth(Values.textwarppingwidth);
		
		//Panel for selecting room
		roomPanel = new RoomSelectionPanel();
		roomEndSelection = roomPanel.getPanel();
		
		//panel for saving selection
		savePanel = new SaveSelectionPanel();
		saveSelection = savePanel.getPanel();
		
		root.getChildren().addAll(textSection, text, roomSprite);
	}
	
	/**
	 * the getter method of the screen
	 * @return the javafx SubScene which is the game screen
	 */
	public SubScene getScreen()
	{
		return screen;
	}
	
	/**
	 * set the reference to the GamePivot
	 * @param gp the mother GamePivot to be injected
	 */
	public void setPivot(GamePivot gp)
	{
		pivot = gp;
	}
	
	/**
	 * reset values when player leaves and (maybe) saves game
	 */
	public void resetValues()
	{
		this.screenState = -1;
		this.currentDialogue = null;
		this.currentRoom = null;
		this.currentSelection = null;
		this.revokeSavePanel();
		this.resetAllSprites();
	}
	
	/*
	public void setLeft(Image i)
	{
		leftSprite.setImage(i);
	}
	
	public void setRight(Image i)
	{
		rightSprite.setImage(i);
	}
	
	public void setSelect(Image i)
	{
		selectionSprite.setImage(i);
	}
	*/
	
	/**
	 * set an Image to the room background ImageView while keeping it fills an entire area
	 * @param i the Image to be set
	 */
	public void setRoomImage(Image i)
	{
		//get the ratio
		double imageRatio = i.widthProperty().doubleValue() / i.heightProperty().doubleValue();
		double screenRatio = (double)Values.screenwidth / (double)Values.pictureheight;
		
		if(imageRatio > screenRatio)
		{
			roomSprite.setFitWidth(0);
			roomSprite.setFitHeight(Values.pictureheight);
		}
		else
		{
			roomSprite.setFitWidth(Values.screenwidth);
			roomSprite.setFitHeight(0);
		}
		
		roomSprite.setImage(i);
		roomSprite.toBack();
	}
	
	
	/**
	 * set the text of the textSection, to be called by TypeWriterThread
	 * @param s the new text
	 */
	public void changeText(String s)
	{
		text.setText(s);
	}
	
	/**
	 * invoke the typewriter effect
	 * @param s the text to be shown
	 */
	public void invokeTypewriter(String s)
	{
		//gives the gui the thread
		//Platform.runLater(new TypeWriterThread(s, pivot));
		Thread t = new Thread(new TypeWriterThread(s, pivot));
		t.start();
	}
	
	/**
	 * remove all sprites from screen
	 */
	private void resetAllSprites()
	{
		root.getChildren().removeAll(leftSprite, rightSprite, selectionSprite, roomEndSelection);
	}
	
	/**
	 * update the screen to one of three states
	 * dialogue, selection, room selection
	 * @param i an integer representing the upcoming state
	 */
	private void screenStateUpdate(int i)
	{
		if(screenState != i)
		{
			resetAllSprites();
			screenState = i;
			
			if(i == DIALOGUE)
			{
				root.getChildren().addAll(leftSprite, rightSprite);
			}
			else if(i == SELECTION)
			{
				root.getChildren().addAll(selectionSprite);
			}
			else if(i == ROOMSELECTION)
			{
				root.getChildren().add(roomEndSelection);
			}
		}
	}
	
	/**
	 * invoke a dialogue screen
	 * @param pa the dialogue page to be invoked
	 */
	public void invokeDialogue(DialoguePage pa)
	{
		screenStateUpdate(DIALOGUE);
		
		//set the image of the characters
		leftSprite.setImage(SpriteDescriptions.characterSprites.get(pa.leftsprite));
		rightSprite.setImage(SpriteDescriptions.characterSprites.get(pa.rightsprite));
		
		invokeTypewriter(pa.speech);
	}
	
	/**
	 * invoke a selection screen
	 * @param pa the selection page to be invoked
	 */
	public void invokeSelection(SelectionPage pa)
	{
		screenStateUpdate(SELECTION);
		
		//set the image
		selectionSprite.setImage(SpriteDescriptions.selectionSprites.get(pa.sprite));
		
		invokeTypewriter(pa.text);
	}
	
	/**
	 * cause the game to change to the next room
	 * @param r the room to go next
	 */
	public void changeRoom(Room r)
	{
		//System.out.println("MainScreen.changeRoom(): \'r\' status: " + r);
		
		this.setRoomImage(SpriteDescriptions.roomSprites.get(r.getBackgroundSprite()));
		//flush the values before entering each room
		pivot.gameValues.flush();
		pivot.insertevent(r.getHandler().handle());
		currentRoom = r;
		pivot.update();
	}
	
	/**
	 * starts the selection for next room at the end of each room
	 */
	public void selectNextRoom()
	{
		screenStateUpdate(ROOMSELECTION);
		roomPanel.setSelection(currentRoom.getAllowedDirection());
		
		invokeTypewriter(Values.roomSelectionText);
	}
	
	/**
	 * invoke the save panel
	 */
	public void invokeSavePanel()
	{
		this.savePanelOn = true;
		root.getChildren().addAll(saveSelection);
	}
	
	/**
	 * return back to gameplay from save panel
	 */
	public void revokeSavePanel()
	{
		this.savePanelOn = false;
		root.getChildren().removeAll(saveSelection);
	}
	
	/**
	 * start the end game credits
	 */
	public void credits()
	{
		resetAllSprites();
		
		Image image = SpriteDescriptions.globalSprites.get("credits");
		creditImage = new ImageView(image);
		double imageHeight = image.getHeight();
		double imageY = - (imageHeight - Values.screenheight);
		
		//create the animation
		Timeline tl = new Timeline();
		
		KeyFrame startframe = new KeyFrame(Duration.seconds(0), new KeyValue(creditImage.yProperty(), 0));
		KeyFrame stopframe = new KeyFrame(Duration.seconds(15), new KeyValue(creditImage.yProperty(), imageY));
		KeyFrame endframe = new KeyFrame(Duration.seconds(25), (ae) ->
		{//lambda expression for EventHandler<ActionEvent>
			//not saving because the game ends
			pivot.backToTitle(false);
			//reset the credit image
			root.getChildren().removeAll(creditImage);
		});
		
		tl.getKeyFrames().addAll(startframe, stopframe, endframe);
		
		root.getChildren().add(creditImage);
		
		tl.play();
	}
}
