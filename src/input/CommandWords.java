package input;

import java.util.ArrayList;
import java.util.Collection;

import util.Logger;

import assets.Plant;
import assets.PlantTypes;
/**
 * This class holds the valid primary commands and checks
 * whether or not a primary command or plant command is valid. 
 * @author Michael Patsula
 */
public class CommandWords
{
	private static Logger LOG = new Logger("CommandWords");
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
             if(word.toString().equals(input))
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
    		if(type.toString().equals(input))
    		{
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Displays all of the valid primary commands
     */
    public void showPrimaryCommands() 
    {
    	for (validPrimaryCmd word : validPrimaryCmd.values())
        {
    		LOG.info(word.toString() + "  ");
        }
        LOG.info("\n");
    }
}