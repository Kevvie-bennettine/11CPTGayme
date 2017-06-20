package values;

import java.io.File;
import java.util.HashMap;

import javafx.scene.image.Image;

/*
 * static class for all sprites
 */

public class SpriteDescriptions
{
	public static HashMap<String, Image> globalSprites = new HashMap<String, Image>();
	public static HashMap<String, Image> selectionSprites = new HashMap<String, Image>();
	public static HashMap<String, Image> characterSprites = new HashMap<String, Image>();
	public static HashMap<String, Image> roomSprites = new HashMap<String, Image>();
	
	/**
	 * loads an image form raw name stored in the Sprites file
	 * @param s the name of the image file with extension
	 * @return the image created
	 */
	public static Image loadImage(String s)
	{
		try
		{
			String st = "file:Sprites" + File.separator + s;
			Image im = new Image(st);
			return im;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	static
	{
		globalSprites.put("textpane", loadImage("border.png"));
		globalSprites.put("title", loadImage("title.png"));
		globalSprites.put("startScreenArrow", loadImage("arrow.png"));
		globalSprites.put("credits", loadImage("credit.png"));
		
		globalSprites.put("NorthArrow", loadImage("directionNorth.png"));
		globalSprites.put("EastArrow", loadImage("directionEast.png"));
		globalSprites.put("SouthArrow", loadImage("directionSouth.png"));
		globalSprites.put("WestArrow", loadImage("directionWest.png"));
		globalSprites.put("SaveSelection", loadImage("saveSelection.png"));
		
		//selectionSprites.put("direction", new Image(""));
		
		//these are all tests
		
		characterSprites.put("Margarette", loadImage("Margarette_normal.png"));
		characterSprites.put("Phantom", loadImage("Phantom_normal.png"));
		
		roomSprites.put("library", loadImage("library.jpg"));
		roomSprites.put("corridor", loadImage("corridor.jpg"));
		roomSprites.put("classroom", loadImage("classroom.jpg"));
		roomSprites.put("storage", loadImage("storage.jpg"));
		roomSprites.put("lab", loadImage("lab.jpg"));
		roomSprites.put("office", loadImage("office.jpg"));
		roomSprites.put("boys_wash", loadImage("washroom2.jpg"));
		roomSprites.put("girls_wash", loadImage("washroom1.jpg"));
		roomSprites.put("lobby", loadImage("lobby.jpg"));
	}
}
