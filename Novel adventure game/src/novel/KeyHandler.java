package novel;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import levels.EventType;
import levels.GameEvent;
import levels.Room;

/**
 * the EventHandler which will be injected to the gui
 */

public class KeyHandler implements EventHandler<KeyEvent>
{
	GamePivot pivot;
	//disable key handling when text is running
	boolean disabled = false;
	
	public void setPivot(GamePivot gp)
	{
		pivot = gp;
	}
	
	//called whenever key on keyboard is pressed
	public void handle(KeyEvent arg0)
	{
		if(disabled)
		{
			return;
		}
		
		if(pivot.gameRunning)
		{
			this.gameScreenHandle(arg0);
		}
		else
		{
			this.startScreenHandle(arg0);
		}
	}
	
	/**
	 * called when the game is in start screen
	 */
	public void startScreenHandle(KeyEvent ke)
	{
		KeyCode kc = ke.getCode();
		
		if(kc == KeyCode.DOWN)
		{
			pivot.start.selectionMove(1);
		}
		else if(kc == KeyCode.UP)
		{
			pivot.start.selectionMove(-1);
		}
		else if(kc == KeyCode.ENTER)
		{
			if(pivot.start.getSelectionState() == StartScreen.NEWGAME)
			{
				pivot.newGame();
			}
			else if(pivot.start.getSelectionState() == StartScreen.RESUME)
			{
				pivot.resumeGame();
			}
		}
	}
	
	/**
	 * called when the game is running
	 */
	public void gameScreenHandle(KeyEvent ke)
	{
		//state of the game screen
		int state = pivot.main.screenState;
		//the keycode of the event
		KeyCode keycod = ke.getCode();
		//see if there is a save panel
		boolean savePanelOn = pivot.main.savePanelOn;
		//will turn true if scene update needed
		boolean needUpdate = false;
		
		//System.out.println("KeyHandler.gameScreenHandle(): KeyCode inputted: " + keycod);
		
		//if esc is pressed, invoke the saving screen
		if(keycod == KeyCode.ESCAPE)
		{
			if(savePanelOn == false)
			{
				pivot.main.invokeSavePanel();
			}
			else if(savePanelOn == true)
			{
				pivot.main.revokeSavePanel();
			}
			
			return;
		}
		
		if(savePanelOn)
		{
			//select if saving file
			if(keycod == KeyCode.LEFT)
			{
				pivot.backToTitle(true);
			}
			else if(keycod == KeyCode.RIGHT)
			{
				pivot.backToTitle(false);
			}
			
			return;
		}
		
		if(state == MainScreen.DIALOGUE)
		{
			needUpdate = true;
		}
		else if(state == MainScreen.SELECTION)
		{
			//see if an event is mapped onto key
			GameEvent[] vent = pivot.main.currentSelection.select(keycod);
			
			if(vent != null)
			{
				pivot.insertevent(vent);
				needUpdate = true;
			}
		}
		else if(state == MainScreen.ROOMSELECTION)
		{
			Room room = pivot.main.currentRoom;
			int x = room.getCoordinate().getX();
			int y = room.getCoordinate().getY();
			
			//check for each direction
			if(keycod == KeyCode.UP && (room.getAllowedDirection() & 8) > 0)//north
			{
				pivot.insertevent(new GameEvent[]{new GameEvent(EventType.changeroom, x + "," + (y+1) )});
				needUpdate = true;
			}
			else if(keycod == KeyCode.RIGHT && (room.getAllowedDirection() & 4) > 0)//east
			{
				pivot.insertevent(new GameEvent[]{new GameEvent(EventType.changeroom, (x+1) + "," + y )});
				needUpdate = true;
			}
			else if(keycod == KeyCode.DOWN && (room.getAllowedDirection() & 2) > 0)//south
			{
				pivot.insertevent(new GameEvent[]{new GameEvent(EventType.changeroom, x + "," + (y-1) )});
				needUpdate = true;
			}
			else if(keycod == KeyCode.LEFT && (room.getAllowedDirection() & 1) > 0)//west
			{
				pivot.insertevent(new GameEvent[]{new GameEvent(EventType.changeroom, (x-1) + "," + y )});
				needUpdate = true;
			}
		}
				
		if(needUpdate)
		{
			pivot.update();
		}
	}
	
	/**
	 * disable the key handler to prevent jamming or deadlock when
	 * other effect or animation is playing
	 */
	public void disable()
	{
		disabled = true;
	}
	
	/**
	 * re-enable the key handler
	 */
	public void enable()
	{
		disabled = false;
	}
}
