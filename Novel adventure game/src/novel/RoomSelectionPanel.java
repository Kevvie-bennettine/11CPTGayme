package novel;

import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.image.ImageView;
import values.SpriteDescriptions;
import values.Values;

/**
 * the gui module that controls the room selection panel
 */
public class RoomSelectionPanel
{
	Group root = new Group();
	SubScene s1 = new SubScene(root, 300, 300);
	
	ImageView[] directionArrows;
	
	public RoomSelectionPanel()
	{
		s1.setLayoutX(Values.screenwidth / 2 - Values.directionPanelDimensions / 2);
		s1.setLayoutY(Values.pictureheight / 2 - Values.directionPanelDimensions / 2);
		
		//create the array
		directionArrows = new ImageView[4];
		for(int ct=0; ct<4; ct++)
		{
			directionArrows[ct] = new ImageView();
		}
		
		directionArrows[0].setImage(SpriteDescriptions.globalSprites.get("NorthArrow"));
		directionArrows[1].setImage(SpriteDescriptions.globalSprites.get("EastArrow"));
		directionArrows[2].setImage(SpriteDescriptions.globalSprites.get("SouthArrow"));
		directionArrows[3].setImage(SpriteDescriptions.globalSprites.get("WestArrow"));
		
		//the position of arrows
		directionArrows[0].setX(Values.directionPanelDimensions / 2 - Values.directionArrowDimensions / 2);
		directionArrows[0].setY(Values.directionPanelMargine);
		
		directionArrows[1].setX(Values.directionPanelDimensions - Values.directionArrowDimensions - Values.directionPanelMargine);
		directionArrows[1].setY(Values.directionPanelDimensions / 2 - Values.directionArrowDimensions / 2);
		
		directionArrows[2].setX(Values.directionPanelDimensions / 2 - Values.directionArrowDimensions / 2);
		directionArrows[2].setY(Values.directionPanelDimensions - Values.directionArrowDimensions - Values.directionPanelMargine);
		
		directionArrows[3].setX(Values.directionPanelMargine); 
		directionArrows[3].setY(Values.directionPanelDimensions / 2 - Values.directionArrowDimensions / 2);
	}
	
	/**
	 * the getter method of the panel
	 * @return the javafx SubScene that is the graphical representation of the panel
	 */
	public SubScene getPanel()
	{
		return s1;
	}
	
	/**
	 * set direction arrows to appear from bit flag
	 * @param b the allowed direction as bit flag, specified in AllowedDiredctionHandler
	 */
	public void setSelection(byte b)
	{
		//reset all arrows
		root.getChildren().removeAll(directionArrows);
		
		for(int ct=0, flag=8; ct<4; ct++, flag/=2)
		{
			//bitwise operator for bitflags
			if((b & flag) > 0)
			{
				root.getChildren().add(directionArrows[ct]);
			}
		}
	}
}
