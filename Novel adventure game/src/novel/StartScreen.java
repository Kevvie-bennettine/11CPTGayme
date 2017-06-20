package novel;

import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import values.SpriteDescriptions;
import values.Values;

/**
 * the starting title screen of the game
 */
public class StartScreen
{
	public static final int NEWGAME = 0;
	public static final int RESUME = 1;
	
	Group root = new Group();
	SubScene screen = new SubScene(root, Values.screenwidth, Values.screenheight);
	
	ImageView background;
	
	Text[] options;
	//Text resumegame;
	
	int selectionState = 0;
	final int maxState = 1;
	
	//the arrow that acts as a cursor
	ImageView arrow;
	
	public StartScreen()
	{
		//put in the background image
		background = new ImageView(SpriteDescriptions.globalSprites.get("title"));
		background.setFitWidth(Values.screenwidth);
		background.setFitHeight(Values.screenheight);
		background.setPreserveRatio(true);
		
		arrow = new ImageView(SpriteDescriptions.globalSprites.get("startScreenArrow"));
		arrow.setX(210);
		arrow.setY(385);
		
		root.getChildren().addAll(background, arrow);
		
		//create the options texts
		options = new Text[2];
		
		for(int ct=0; ct< options.length; ct++)
		{
			options[ct] = new Text();
			options[ct].setX(240);
			options[ct].setY(400 + ct * 20);
			options[ct].setFont(new Font("Arial", 16));
			options[ct].setFill(Color.WHITE);
		}
		
		options[0].setText("new game");
		options[1].setText("resume game");
		
		root.getChildren().addAll(options);
	}
	
	/**
	 * getter method for screen
	 * @return the javafx SubScene that have the start screen
	 */
	public SubScene getScreen()
	{
		return this.screen;
	}
	
	/**
	 * moves the selection cursor
	 * @param m kind of selection, positive means up, negative means down
	 */
	public void selectionMove(int m)
	{
		//if increase (move down)
		if(m > 0 && selectionState < maxState)
		{
			selectionState++;
			arrow.setY(arrow.getY() + 20);
		}
		else if(m < 0 && selectionState > 0)
		{
			selectionState--;
			arrow.setY(arrow.getY() - 20);
		}
	}
	
	/**
	 * getter method for current option state
	 * @return the current selection
	 */
	public int getSelectionState()
	{
		return this.selectionState;
	}
}
