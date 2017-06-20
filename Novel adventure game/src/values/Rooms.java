package values;

import java.util.HashMap;

import javafx.util.Pair;
import levels.AllowedDirectionHandler;
import levels.EventType;
import levels.GameEvent;
import levels.PlotHandler;
import levels.Room;
import novel.GamePivot;

public class Rooms
{
	public static HashMap<Pair<Integer, Integer>, Room> rooms = new HashMap<Pair<Integer, Integer>, Room>();
	
	public static void addRoom(Room r)
	{
		rooms.put(new Pair(r.getCoordinate().getX(), r.getCoordinate().getY()), r);
	}
	
	/**
	 * get the room using a string like "11,-7"
	 * @param s the string representing the coordinates of the room
	 * @return the according room in the HashMap
	 */
	public static Room getRoomByString(String s)
	{
		int comma = s.indexOf(',');
		
		int x = Integer.parseInt(s.substring(0, comma));
		int y = Integer.parseInt(s.substring(comma + 1, s.length()));
		
		//System.out.println("Rooms.getRoomByString(): " + "x and y values: " + x + ", " + y);
		
		return rooms.get(new Pair(x, y));
	}
	
	/**
	 * get a room object using numbers
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return  the according room in the HashMap
	 */
	public static Room getRoomByNumber(int x, int y)
	{
		return rooms.get(new Pair(x,y));
	}
	
	//static initializer block
	static
	{
		/*
		PlotHandler rm1Ph = () -> { return new GameEvent[]{new GameEvent(EventType.dialogue, "Rm1Dl1"), new GameEvent(EventType.credits, "")}; };
		Room roomTest1 = new Room(rm1Ph, 0, 0, "Test1", (byte) 0b0001);
		
		addRoom(roomTest1);
		*/
		
		//plot handler for library
		PlotHandler libPh = () -> 
		{
			if(GamePivot.getGameValues().getArray()[0] == 0)
			{
				GamePivot.getGameValues().getArray()[0] = 1;
				return new GameEvent[]{new GameEvent(EventType.dialogue, "libraryStart1"),
						new GameEvent(EventType.dialogue, "libraryStart2"),
						new GameEvent(EventType.dialogue, "libraryStart3")};
			}
			else
			{
				return new GameEvent[]{new GameEvent(EventType.dialogue, "libraryRepeat1")};
			}
		};
		
		Room library = new Room(libPh, 0, 0, "library", (byte)0b1000);
		
		//plot handler for corridor 1
		PlotHandler cor1Ph = () ->
		{
			if(GamePivot.getGameValues().getArray()[1] == 0)
			{
				GamePivot.getGameValues().getArray()[1] = 1;
				return new GameEvent[]{new GameEvent(EventType.dialogue, "corridor1Start1"),
						new GameEvent(EventType.dialogue, "corridor1Start2"),
						new GameEvent(EventType.dialogue, "corridor1Start3"),
						new GameEvent(EventType.dialogue, "corridor1Start4"),
						new GameEvent(EventType.dialogue, "corridor1Start5")};
			}
			else
			{
				return new GameEvent[]{new GameEvent(EventType.dialogue, "corridor1Repeat1")};
			}
		};
		
		Room corridor1 = new Room(cor1Ph, 0, 1, "corridor", (byte)0b1111);
		
		//plot handler for classroom
		PlotHandler clsPh = () ->
		{
			return new GameEvent[]{new GameEvent(EventType.dialogue, "classroomFirst1")};
		};
		
		Room classroom = new Room(clsPh, 1, 1, "classroom", (byte)0b0001);
		
		//plot handler for storage
		PlotHandler stoPh = () ->
		{
			if(GamePivot.getGameValues().getArray()[1] == 1)
			{
				GamePivot.getGameValues().getArray()[1] = 2;
				return new GameEvent[]{new GameEvent(EventType.dialogue, "storageStart1"),
						new GameEvent(EventType.dialogue, "storageStart2"),
						new GameEvent(EventType.dialogue, "storageStart3")};
			}
			else
			{
				return new GameEvent[]{new GameEvent(EventType.dialogue, "storageRepeat1")};
			}
		};
		
		Room storage = new Room(stoPh, 0, 2, "storage", (byte)0b0010);
		
		//plot handler for corridor 2
		PlotHandler cor2Ph = () ->
		{
			if(GamePivot.getGameValues().getArray()[1] < 2)//if storage room event is not yet invoked
			{
				return new GameEvent[]{new GameEvent(EventType.dialogue, "corridor2First1")};
			}
			else if(GamePivot.getGameValues().getArray()[1] == 2)//if storage room event is invoked
			{
				GamePivot.getGameValues().getArray()[1] = 3;
				return new GameEvent[]{new GameEvent(EventType.dialogue, "corridor2Start1"),
						new GameEvent(EventType.dialogue, "corridor2Start2"),
						new GameEvent(EventType.dialogue, "corridor2Start3"),
						new GameEvent(EventType.dialogue, "corridor2Start4")};
			}
			else
			{
				return new GameEvent[]{new GameEvent(EventType.dialogue, "corridor2Repeat1")};
			}
		};
		
		AllowedDirectionHandler cor2Ah = () ->
		{
			if(GamePivot.getGameValues().getArray()[1] < 2)//if before storage event
			{
				return 0b0100;
			}
			else if(GamePivot.getGameValues().getArray()[1] < 4)//if before office event
			{
				return 0b0110;
			}
			else//if after office event
			{
				return 0b0111;
			}
		};
		
		Room corridor2 = new Room(cor2Ph, -1, 1, "corridor", cor2Ah);
		
		//plot handler for office
		PlotHandler offPh = () ->
		{
			//if corridor event in invoked
			if(GamePivot.getGameValues().getArray()[1] == 3)
			{
				GamePivot.getGameValues().getArray()[1] = 4;
				return new GameEvent[]{new GameEvent(EventType.dialogue, "officeStart1"),
						new GameEvent(EventType.dialogue, "officeStart2")};
			}
			else
			{
				return new GameEvent[]{new GameEvent(EventType.dialogue, "officeRepeat1")};
			}
		};
		
		Room office = new Room(offPh, -1, 0, "office", (byte)0b1000);
		
		//plot handler for lobby
		PlotHandler lobPh = () ->
		{
			return new GameEvent[]
					{
							new GameEvent(EventType.dialogue, "lobbyStart1"),
							new GameEvent(EventType.dialogue, "lobbyStart2"),
							new GameEvent(EventType.dialogue, "lobbyStart3"),
							new GameEvent(EventType.dialogue, "lobbyStart4"),
							new GameEvent(EventType.dialogue, "lobbyStart5"),
							new GameEvent(EventType.dialogue, "lobbyStart6"),
							new GameEvent(EventType.dialogue, "lobbyStart7"),
							new GameEvent(EventType.credits, "")
					};
		};
		
		Room lobby = new Room(lobPh, -2, 1, "lobby", (byte)0b0000);
		
		addRoom(library);
		addRoom(corridor1);
		addRoom(storage);
		addRoom(classroom);
		addRoom(corridor2);
		addRoom(office);
		addRoom(lobby);
	}
}
