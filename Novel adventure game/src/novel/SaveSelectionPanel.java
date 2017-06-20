package novel;

import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.image.ImageView;
import values.SpriteDescriptions;
import values.Values;

/**
 * the screen to prompt for saving the game
 */
public class SaveSelectionPanel
{
	Group root = new Group();
	SubScene panel = new SubScene(root, Values.directionPanelDimensions, Values.directionPanelDimensions);
	
	ImageView prompt;
	
	public SaveSelectionPanel()
	{
		panel.setLayoutX(Values.screenwidth / 2 - Values.directionPanelDimensions / 2);
		panel.setLayoutY(Values.pictureheight / 2 - Values.directionPanelDimensions / 2);
		
		prompt = new ImageView(SpriteDescriptions.globalSprites.get("SaveSelection"));
		prompt.setFitWidth(Values.directionPanelDimensions);
		prompt.setFitHeight(Values.directionPanelDimensions);
		
		root.getChildren().add(prompt);
	}
	
	/**
	 * getter method for the panel
	 * @return the SubScene representing the panel
	 */
	public SubScene getPanel()
	{
		return this.panel;
	}
}
