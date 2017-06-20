package levels;

/**
 * the model object for a dialogue page
 */
public class DialoguePage
{
	//the left and right sprite, and the speech in the text section
	//the left and right sprites should be defined using map keys
	public final String leftsprite;
	public final String rightsprite;
	public final String speech;
	
	/**
	 * constructor
	 * @param l the character sprite on the left, as key for SpritesDescriptions.characterSprites
	 * @param r the character sprite on the right, as key for SpritesDescriptions.characterSprites
	 * @param s the text or speech to be shown
	 */
	public DialoguePage(String l, String r, String s)
	{
		this.leftsprite = l;
		this.rightsprite = r;
		this.speech = s;
	}
}
