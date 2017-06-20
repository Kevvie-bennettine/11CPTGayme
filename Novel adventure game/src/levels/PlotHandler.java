package levels;

/**
 * the interface for invoking any plot
 * return a series of event according to implementation
 */
public interface PlotHandler
{
	public GameEvent[] handle();
}
