package levels;

import java.util.Map;

import javafx.scene.input.KeyCode;

/**
 * model object representing a selection page in the game
 *
 */
public class SelectionPage
{
	public String sprite;
	Map<KeyCode, PlotHandler> response;
	public String text;
	
	/**
	 * constructor
	 * @param sp  the sprite to show for this selection page
	 * @param res the KeyCode to PlotHandler map representing the allowed option, used in KeyHandler
	 * @param t   the text that will be in the text bar
	 */
	public SelectionPage(String sp, Map<KeyCode, PlotHandler> res, String t)
	{
		sprite = sp;
		response = res;
		text = t;
	}
	
	protected SelectionPage()
	{
		
	}
	
	/**
	 * returns a GameEvent array according to key pressed, should be called in KeyHandler
	 * @param kc the javafx KeyCode
	 * @return the GameEvent array for that selection
	 */
	public GameEvent[] select(KeyCode kc)
	{
		PlotHandler ph = response.get(kc);
		
		if(ph != null)
		{
			return ph.handle();
		}
		else
		{
			return null;
		}
	}
}
