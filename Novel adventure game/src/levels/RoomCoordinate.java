package levels;

/**
 * the coordinate of a room in a cartesian plane
 */
public class RoomCoordinate
{
	int x;
	int y;
	
	public RoomCoordinate(int a, int b)
	{
		x = a;
		y = b;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
}
