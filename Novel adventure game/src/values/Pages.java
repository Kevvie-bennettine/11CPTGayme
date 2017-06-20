package values;

import java.util.HashMap;

import levels.DialoguePage;
import levels.SelectionPage;

/*
 * static class for all the selection and dialogues
 */

public class Pages
{
	static HashMap<String, DialoguePage> dialogues = new HashMap<String, DialoguePage>();
	static HashMap<String, SelectionPage> selections = new HashMap<String, SelectionPage>();
	
	//method for retrieving a DialoguePage
	public static DialoguePage getDialogue(String s)
	{
		return dialogues.get(s);
	}
	
	//method for retrieving a SelectionPage
	public static SelectionPage getSelection(String s)
	{
		return selections.get(s);
	}
	
	//static initilizer block, put all settings here
	static
	{
		/*
		DialoguePage diaTest1 = new DialoguePage("Test1", "Test2", "Lucy: Yo ur gay?");
		dialogues.put("Rm1Dl1", diaTest1);
		*/
		
		dialogues.put("libraryStart1", new DialoguePage("Margarette", "null", "Margarette: Ah... Did I just fell asleep?"));
		dialogues.put("libraryStart2", new DialoguePage("Margarette", "null", "Margarette: Um... it's 7:36 now..."));
		dialogues.put("libraryStart3", new DialoguePage("Margarette", "null", "Margarette: Why didn't the custodians wake me up? Anyways, I need to go home now."));
		
		dialogues.put("libraryRepeat1", new DialoguePage("Margarette", "null", "There's no one in the library"));
		
		dialogues.put("corridor1Start1", new DialoguePage("Margarette", "null", "Margarette: The lights are all off, and the doors are all locked..."));
		dialogues.put("corridor1Start2", new DialoguePage("Margarette", "Phantom", "Phantom: Greetings..."));
		dialogues.put("corridor1Start3", new DialoguePage("Margarette", "Phantom", "Margarette: DaHeck? Who are you?"));
		dialogues.put("corridor1Start4", new DialoguePage("Margarette", "Phantom", "Phantom: You need not fear. I am just telling you that you can go to the storage room."));
		dialogues.put("corridor1Start5", new DialoguePage("Margarette", "null", "Margarette: Um... The storage room is north of this corridor, but I am not suppose to go in there..."));
		
		dialogues.put("corridor1Repeat1", new DialoguePage("Margarette", "null", "The corridor is empty..."));
		
		dialogues.put("corridor2First1", new DialoguePage("Margarette", "null", "The door to the lobby and the other side of the school is locked..."));
		
		dialogues.put("classroomFirst1", new DialoguePage("Margarette", "null", "There is no one in this classroom..."));
		
		dialogues.put("storageStart1", new DialoguePage("Margarette", "null", "Margarette: So this is how the storage room looks like."));
		dialogues.put("storageStart2", new DialoguePage("Margarette", "null", "Margarette: Eh? There is a key here."));
		dialogues.put("storageStart3", new DialoguePage("Margarette", "null", "Margarette picked up the key"));
		
		dialogues.put("storageRepeat1", new DialoguePage("Margarette", "null", "The storage room is full of books"));
		
		dialogues.put("corridor2Start1", new DialoguePage("Margarette", "Phantom", "Phantom: The key is to the office..."));
		dialogues.put("corridor2Start2", new DialoguePage("Margarette", "Phantom", "Margarette: The key... Hey! Stop scaring me!"));
		dialogues.put("corridor2Start3", new DialoguePage("Margarette", "Phantom", "Phantom: ..."));
		dialogues.put("corridor2Start4", new DialoguePage("Margarette", "null", "Margarette: He is gone... The office is south of here."));
		
		dialogues.put("officeStart1", new DialoguePage("Margarette", "null", "Margarette: Oh! The Door key is here!"));
		dialogues.put("officeStart2", new DialoguePage("Margarette", "null", "Margarette picked up the keys"));
		
		dialogues.put("officeRepeat1", new DialoguePage("Margarette", "null", "There is no one in the office..."));
		
		dialogues.put("corridor2Repeat1", new DialoguePage("Margarette", "null", "Margarette: I got the keys now, I can go west to the lobby."));
		
		dialogues.put("lobbyStart1", new DialoguePage("Margarette", "null", "Margarette: Phew! Finally!"));
		dialogues.put("lobbyStart2", new DialoguePage("Margarette", "Phantom", "Phantom: Farewell, my friend."));
		dialogues.put("lobbyStart3", new DialoguePage("Margarette", "Phantom", "Margarette: Who are you? Tell me!"));
		dialogues.put("lobbyStart4", new DialoguePage("Margarette", "Phantom", "Phantom: You don't need to know..."));
		dialogues.put("lobbyStart5", new DialoguePage("Margarette", "null", "Margarette: Hey! Come back..."));
		dialogues.put("lobbyStart6", new DialoguePage("Margarette", "null", "Margarette: Such a wierd person..."));
		dialogues.put("lobbyStart7", new DialoguePage("Margarette", "null", "Margarette: Anyways, I can leave now."));
	}
}
