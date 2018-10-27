package input;

import assets.PlantTypes;

/**
 * This class holds the valid primary commands and checks whether or not a
 * primary command or plant command is valid.
 * 
 * @author Michael Patsula
 */
public enum CommandWords {
	HELP, 
	PLAY,
	QUIT, 
	LOAD, 
	PASS, 
	PLACE;

	/**
	 * Used to determine whether or not a given user input string is a valid primary
	 * command (word1).
	 * 
	 * @param input
	 * @return a boolean - Yes if the unit is valid, false otherwise
	 */
	public static boolean isPrimaryCommand(String input) {
		for (CommandWords word : CommandWords.values()) {
			if (word.toString().equalsIgnoreCase(input)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Used to determine whether or not a given user input string is a valid unit.
	 * 
	 * @param input
	 * @return a boolean - Yes if the unit is valid, false otherwise
	 */
	public static boolean isValidUnit(String input) {
		for (PlantTypes type : PlantTypes.values()) {
			if (type.toString().equalsIgnoreCase(input)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Displays all of the valid primary menu commands
	 */
	public static String getPrimaryMenuCommands() {
		return "PLAY | LOAD | QUIT |";
	}

	/**
	 * Displays all of the valid primary game commands
	 */
	public static String getPrimaryGameCommands() {
		StringBuilder s = new StringBuilder();
		for (CommandWords word : CommandWords.values()) {
			if (word != CommandWords.LOAD && word != CommandWords.PLAY)
				s.append(word.toString() + " | ");
		}
		return s.toString();
	}
}