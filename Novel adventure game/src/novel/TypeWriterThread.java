package novel;

import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import values.Values;

public class TypeWriterThread implements Runnable
{
	String text;
	GamePivot pivot;
	
	/**
	 * @param s  the text in a string that is going to be shown
	 * @param gp the GamePivot of the injected target
	 */
	public TypeWriterThread(String s, GamePivot gp)
	{
		text = s;
		pivot = gp;
	}
	
	public void run()
	{
		//disable the key handler to prevent jam or deadlock
		pivot.keyhandle.disable();
		
		//code for putting text on screen
		for(int ct=1; ct < text.length() + 1; ct++)
		{
			final int fct = ct;
			
			//change the text using the gui thread
			Platform.runLater( () -> {pivot.main.changeText(text.substring(0, fct));} );
			
			//System.out.println("TypeWriterThread.run(): value of substring: " + text.substring(0, ct));
			try
			{
				TimeUnit.MILLISECONDS.sleep(Values.typewriterDelay);
			}
			catch(InterruptedException e)
			{
				
			}
		}
		
		pivot.keyhandle.enable();
	}
}
