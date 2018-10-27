package input;

import java.util.Scanner;

import util.Logger;


/**
 * This class converts the user's input into an instance of Command ie. converts
 * the input into words.
 * 
 * @author Michael Patsula
 *
 */
public class Parser {
	private Parser() {
	}

	private static Logger LOG = new Logger("Parser");
	private static Scanner reader = new Scanner(System.in);

	/**
	 * Creates a Command instance based on the user input (ie converts the input
	 * tokens into variables)
	 * 
	 * @return a Command instance
	 */
	public static Command getCommand() {
		String inputLine; // will hold the full input line
		String[] words = { null, null, null, null };

		inputLine = reader.nextLine();
		LOG.debug("Read " + inputLine);

		Scanner tokenizer = new Scanner(inputLine);
		for (int counter = 0; counter < words.length; counter++) {
			if (tokenizer.hasNext()) {
				words[counter] = tokenizer.next();
				LOG.debug("Tokenized " + counter + ": " + words[counter]);
			}
		}
		tokenizer.close();
		if (!CommandWords.isPrimaryCommand(words[0])) {
			LOG.debug("Not a primary command");
			return null; // invalid command
		} else {
			return new Command(words[0], words[1], words[2], words[3]);
		}
	}

}