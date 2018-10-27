package input;
/**
 * This class holds the tokenized version of the user input.
 * From this class the user will be able to get specified tokens of the user's input.
 * @author Michael Patsula
 *
 */
public class Command
{
	private String[] commandWords = new String[4];
   
    public Command(String firstWord, String secondWord, String thirdWord, String fourthWord)
    {
        commandWords[0] = firstWord;
        commandWords[1] = secondWord;
        commandWords[2] = thirdWord;
        commandWords[3] = fourthWord;
    }
    
    /**
     * Returns a word corresponding to the given index. Word one would corresponds
     * to index = 1.
     * @param index - ex: second word is index 2
     * @return The word corresponding to the given index
     */
    public String getWord(int index)
    {
    	return commandWords[index - 1];
    }
    
    /**
     * Determines if the word is unknown (null)
     * @param index - where word 2 equals index 1
     * @return true if the word is null, otherwise returns false
     */
    public boolean isUnknownWord(int index)
    {
    	 return (commandWords[index - 1] == null);
    }
    
    /**
     * @return the command 
     */
    public String[] getCommandWords()
    {
    	return commandWords;
    }
}

