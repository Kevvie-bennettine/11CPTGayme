package levels;

/**
 * object representing an event in the game
 */
public class GameEvent
{
	/*
	the type of event and key to access the frame
	if it was dialogue or selection key will be the key of the page
	if it was changeroom then key of the room
	 */
	EventType type;
	String key;
	
	/**
	 * constructor
	 * @param et the type of the event
	 * @param s  the key to access the specific event from maps in Pages
	 */
	public GameEvent(EventType et, String s)
	{
		type = et;
		key = s;
	}
	
	public EventType getType()
	{
		return this.type;
	}
	
	public String getKey()
	{
		return this.key;
	}
}
