package input;
public class Command
{
	private String[] commandWords;
   
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
    
}

