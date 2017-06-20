package novel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import levels.RoomCoordinate;
import values.Values;

/**
 * the in game values representing the progress in the plot
 * the byte array is only 62 byte because the first two byte
 * representes the room player is in in the save file
 */
public class GameValues
{
	//the byte array storing in game value
	byte[] val = new byte[62];
	//the temporary array, flushed to val every change of room
	byte[] temp = new byte[62];
	
	/**
	 * read saved file
	 * @return the starting room coordinate(first 2 byte of the file)
	 */
	public RoomCoordinate readSaveFile()
	{
		try
		{
			FileInputStream fis = new FileInputStream(Values.saveFileName);
			
			byte x = (byte)fis.read();
			byte y = (byte)fis.read();
			
			byte[] data = new byte[62];
			fis.read(data);
			
			val = data;
			temp = val.clone();
			
			fis.close();
			
			return new RoomCoordinate(x,y);
		}
		catch (IOException ioe)
		{
			System.out.println("GameValues.readSaveFile(): IOException: " + ioe.getMessage());
		}
		
		return null;
	}
	
	/**
	 * write data to the saved file
	 * @param rc the current room coordinate
	 */
	public void writeSaveFile(RoomCoordinate rc)
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(Values.saveFileName);
			
			fos.write(rc.getX());
			fos.write(rc.getY());
			
			fos.write(val);
			
			fos.close();
		}
		catch(IOException ioe)
		{
			System.out.println("GameValues.writeSaveFile(): IOException: " + ioe.getMessage());
		}
	}
	
	/**
	 * reset all the values so that a new game can start
	 */
	public void newGame()
	{
		val = new byte[62];
		temp = new byte[62];
	}
	
	/**
	 * getter method for in game values
	 * @return the byte array for in game values
	 */
	public byte[] getArray()
	{
		return this.temp;
	}
	
	/**
	 * flush the temporary array to values,
	 * called when change room occured,
	 * to prevent save file errors
	 */
	public void flush()
	{
		val = temp.clone();
	}
}
