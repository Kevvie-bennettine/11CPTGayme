package values;
/*
 * static template class for the in game values
 */

public class Values
{
	//the width and height of the game window
	public static final int screenwidth = 600;
	public static final int screenheight = 600;
	
	//the height of the text panel
	public static final int textpanelheight = 200;
	//the x y position of the text
	public static final int textpaneltextx = 30;
	public static final int textpaneltexty = 40;
	//the wrapping width of the text
	public static final int textwarppingwidth = 340;
	
	public static final int pictureheight = screenheight - textpanelheight;
	
	public static final int directionPanelDimensions = 300;
	public static final int directionArrowDimensions = 60;
	public static final int directionPanelMargine = 40;
	
	public static final int selectionSpriteDimensions = 300;
	
	public static final int startRoomX = 0;
	public static final int startRoomY = 0;
	
	public static final int typewriterDelay = 50;
	
	//public static final String titlepic = "title.png";
	
	public static final String saveFileName = "save";
	
	public static final String roomSelectionText = "Choose which direction you want to go next.";
}
