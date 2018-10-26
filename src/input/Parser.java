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
       
        /*
        if(commands.isPrimaryCommand(words[0]))
        	return new Command(words[0], words[1], words[2], words[3]);
            
        else 
            return new Command(null, words[1], words[2], words[3]); 
         */
        
        return new Command(words[0], words[1], words[2],words[3]);
    }
    
   
}