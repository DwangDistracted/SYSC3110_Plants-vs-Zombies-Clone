package Input;

import java.util.ArrayList;
import java.util.Collection;

public class CommandWords
{
    // a constant array that holds all valid command words
    private ArrayList<String> validCommands; 
    private String[] basicCommands = {"help","play","quit","pass","sf","ps","load"};
    //private String[] advancedCommands;


    public CommandWords()
    {
    	validCommands = new ArrayList<String>();
        for(String i : basicCommands)
        {
        	validCommands.add(i);
        }
    }

  
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.size(); i++) {
            if(validCommands.get(i).equals(aString))
                return true;
        }
        
        return false;
    }
    
    public void addValidCommand(String command)
    {
    	validCommands.add(command);
    }


    public void showAll() 
    {
        for(String command: validCommands) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}