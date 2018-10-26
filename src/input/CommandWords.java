package input;

import java.util.ArrayList;
import java.util.Collection;

import assets.Plant;
import assets.PlantTypes;

public class CommandWords
{
	private enum validPrimaryCmd {HELP,PLAY,QUIT,LOAD,PASS,PLACE};
    
    /**
     * Used to determine whether or not a given user input string 
     * is a valid primary command (word1).
     * @param input
     * @return a boolean - Yes if the unit is valid, false otherwise
     */
    public boolean isPrimaryCommand(String input)
    {
        for (validPrimaryCmd word : validPrimaryCmd.values())
        {
             if(word.toString() == input)
             {
            	 return true;
             }
        }
        return false;
    }
    
    /**
     * Used to determine whether or not a given user input string 
     * is a valid unit.
     * @param input
     * @return a boolean - Yes if the unit is valid, false otherwise
     */
    public boolean isValidUnit(String input)
    {
    	for(PlantTypes type : PlantTypes.values())
    	{
    		if(PlantTypes.toPlant(type).toString() == input)
    		{
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Displays all of the valid primary commands
     */
    public void showAll() 
    {
    	for (validPrimaryCmd word : validPrimaryCmd.values())
        {
    		System.out.print(word.toString() + "  ");
        }
        System.out.println();
    }
}