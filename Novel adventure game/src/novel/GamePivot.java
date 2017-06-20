package novel;

import java.util.Vector;

import javafx.scene.Group;
import javafx.scene.Scene;
import levels.EventType;
import levels.GameEvent;
import levels.RoomCoordinate;
import values.Pages;
import values.Rooms;
import values.Values;

/**
 * the mother control center of the game
 * singleton
 */
public class GamePivot
{
	private static GamePivot gamepivot;
	
	Group root = new Group();
	Scene scene = new Scene(root, Values.screenwidth, Values.screenheight);
	
	StartScreen start;
	MainScreen main;
	KeyHandler keyhandle;
	GameValues gameValues;
	
	//keep tract if game is running
	boolean gameRunning = false;
	
	Vector<GameEvent> eventqueue = new Vector<GameEvent>();
	
	private GamePivot()
	{
		start = new StartScreen();
		main = new MainScreen();
		keyhandle = new KeyHandler();
		
		scene.setOnKeyReleased(keyhandle);
		
		gameValues = new GameValues();
		
		main.setPivot(this);
		keyhandle.setPivot(this);
		root.getChildren().add(start.getScreen());
	}
	
	/**
	 * factory method hiding the actual object,
	 * keeping the class singleton
	 */
	public static void create()
	{
		gamepivot = new GamePivot();
	}
	
	/**
	 * getter method of the actual object
	 * @return the only GamePivot object
	 */
	public static GamePivot get()
	{
		return gamepivot;
	}
	
	/**
	 * getter method for the plot interface implementations
	 * to access the in game values
	 * @return the GameValues object
	 */
	public static GameValues getGameValues()
	{
		return gamepivot.gameValues;
	}
	
	/**
	 * getter method of the screen
	 * @return the javafx Scene that is the screen of the game
	 */
	public Scene getScene()
	{
		return scene;
	}
	
	/**
	 * update the game(start next the event in the queue)
	 */
	public void update()
	{
		if(eventqueue.isEmpty())
		{
			main.selectNextRoom();
			return;
		}
		
		GameEvent evnt = eventqueue.elementAt(0);
		eventqueue.removeElement(evnt);
		
		EventType type = evnt.getType();
		String key = evnt.getKey();
		
		//invoke each type of event
		if(type == EventType.changeroom)
		{
			main.changeRoom(Rooms.getRoomByString(key));
		}
		else if(type == EventType.dialogue)
		{
			main.invokeDialogue(Pages.getDialogue(key));
		}
		else if(type == EventType.selection)
		{
			main.invokeSelection(Pages.getSelection(key));
		}
		else if(type == EventType.credits)
		{
			main.credits();
		}
	}
	
	/**
	 * insert game events
	 * @param ev the array of GameEvent to be added to the queue
	 */
	public void insertevent(GameEvent[] ev)
	{
		for(GameEvent e : ev)
		{
			eventqueue.add(e);
		}
	}
	
	/**
	 * return back to title screen and reset some values in MainScreen
	 * @param save decide if save the game or not
	 */
	public void backToTitle(boolean save)
	{
		if(save)
		{
			//write to file
			RoomCoordinate rc = main.currentRoom.getCoordinate();
			gameValues.writeSaveFile(rc);
		}
		
		main.resetValues();
		//change gui 
		root.getChildren().removeAll(main.getScreen());
		root.getChildren().add(start.getScreen());
		eventqueue.clear();
		
		gameRunning = false;
	}
	
	/**
	 * start game at specific room coordinate
	 * @param x the x coordinate of the room starting
	 * @param y ths y coordinate of the room starting
	 */
	public void startGame(int x, int y)
	{
		main.changeRoom(Rooms.getRoomByNumber(x, y));
		//change gui
		root .getChildren().removeAll(start.getScreen());
		root.getChildren().add(main.getScreen());
		
		gameRunning = true;
		update();
	}
	
	/**
	 * starts a new play of the game
	 */
	public void newGame()
	{
		gameValues.newGame();
		startGame(Values.startRoomX, Values.startRoomY);
	}
	
	/**
	 * resume game from saved file
	 */
	public void resumeGame()
	{
		RoomCoordinate rc = gameValues.readSaveFile();
		startGame(rc.getX(), rc.getY());
	}
}
