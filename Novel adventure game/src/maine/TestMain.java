package maine;

import javafx.application.Application;
import javafx.stage.Stage;
import novel.GamePivot;

public class TestMain extends Application
{
	public void start(Stage sta) throws Exception
	{
		GamePivot.create();
		GamePivot gp = GamePivot.get();
		//gp.newGame();
		
		sta.setScene(gp.getScene());
		sta.show();
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}
