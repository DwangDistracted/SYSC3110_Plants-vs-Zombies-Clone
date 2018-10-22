package input;

import java.util.ArrayList;
import java.util.Collection;

public class CommandWords
{
    // a constant array that holds all valid command words
    //private ArrayList<String> validCommands; 
    private String[] validCommands = {"help","play","quit","load","pass","place"};


    public CommandWords()
    {
    
    }

  
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        
        return false;
    }
    
    /*
    public void addValidCommand(String[] commands)
    {
    	for(String i : commands)
    	{
    		validCommands.add(i);
    	}
    }
	*/

    public void showAll() 
    {
        for(String command: validCommands) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}