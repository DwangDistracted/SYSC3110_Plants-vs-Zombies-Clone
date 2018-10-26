package input;

import java.util.Scanner;
/**
 * This class converts the user's input into an instance of Command
 * ie. converts the input into words.
 * @author Michael Patsula
 *
 */
 
public class Parser 
{
    private Scanner reader;         // source of command input

    /**
     * Create a Scanner to read the user's input
     */
    public Parser() 
    {
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
        
        return new Command(words[0], words[1], words[2],words[3]);
    }
    
   
}