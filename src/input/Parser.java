package input;

import java.util.Scanner;

 
public class Parser 
{
    private CommandWords commands;  // holds all valid command words
    private Scanner reader;         // source of command input

    /**
     * Create a parser to read from the terminal window.
     */
    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * Creates a Command instance based on the user input 
     * (ie converts the input tokens into variables)
     * @return a Command instance
     */
    public Command getCommand() 
    {
        String inputLine;   // will hold the full input line
        String[] words = {null,null,null,null};
  
        System.out.print("> ");    

        inputLine = reader.nextLine();

     
        Scanner tokenizer = new Scanner(inputLine);
        for(int counter = 0; counter < words.length; counter++)
        {
        	if(tokenizer.hasNext())
        	{
        		words[counter] = tokenizer.next();
        	}
        }
       
        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if(commands.isCommand(words[0]))
        	return new Command(words[0], words[1], words[2], words[3]);
            
        else 
            return new Command(null, words[1], words[2], words[3]); 
        
    }
    
    /**
     * @return the command instance initialized within Parser
     */
    public CommandWords getCommandWords()
    {
    	return commands;
    }
    

    /**
     * Print out a list of valid command words.
     */
    public void showCommands()
    {
        commands.showAll();
    }
}