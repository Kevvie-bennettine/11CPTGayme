package levels;

/**
 * defines a single room
 * with coordinate, background, event when entered, and allowed direction
 */

public class Room
{
	PlotHandler handle;
	RoomCoordinate coor;
	String background;
	/**
	 * the direction allowed out of this room
	 * return by implementation of interface
	 * represented as bit flags 0b_abcd
	 * a = north, b = east, c = south, d = west
	 */
	AllowedDirectionHandler allowedDirection;
	
	/**
	 * the constructor for room with solid link(i.e. the direction allowed never changes)
	 * @param erh PlotHandler returns the event invoked everytime this room is entered
	 * @param x   x coordinate of the room
	 * @param y   y coordinate of the room
	 * @param s   the backgroung sprite as a key int the SpriteDescriptions.roomSprites map
	 * @param b   the allowed direction
	 */
	public Room(PlotHandler erh, int x, int y, String s, byte b)
	{
		handle = erh;
		coor = new RoomCoordinate(x, y);
		background = s;
		allowedDirection = () -> {return b;};
	}
	
	/**
	 * the constructor for room with fluid link(i.e. the direction allowed can change)
	 * @param erh PlotHandler returns the event invoked everytime this room is entered
	 * @param x   x coordinate of the room
	 * @param y   y coordinate of the room
	 * @param s   the backgroung sprite as a key int the SpriteDescriptions.roomSprites map
	 * @param b   the allowed direction
	 */
	public Room(PlotHandler erh, int x, int y, String s, AllowedDirectionHandler ad)
	{
		handle = erh;
		coor = new RoomCoordinate(x, y);
		background = s;
		allowedDirection = ad;
	}
	
	public RoomCoordinate getCoordinate()
	{
		return this.coor;
	}
	
	public PlotHandler getHandler()
	{
		return this.handle;
	}
	
	public String getBackgroundSprite()
	{
		return this.background;
	}
	
	public byte getAllowedDirection()
	{
		return this.allowedDirection.handle();
	}
}
