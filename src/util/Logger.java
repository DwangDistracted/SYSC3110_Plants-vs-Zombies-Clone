package util;

/**
 * A Logger class that does output.
 * @author david
 */
public class Logger {
	//is debug output enabled
	private static boolean debug = false;
	
	private String debugPrefix = "[DEBUG] - ";
	private String infoPrefix = "[INFO] - ";
	private String warnPrefix = "[WARN] - ";
	private String errorPrefix = "[ERROR] - ";
	private String promptPrefix = "[PROMPT] - ";
	
	public Logger(String name) {
		debugPrefix += "[" + name + "] : ";
		infoPrefix += "[" + name + "] : ";
		warnPrefix += "[" + name + "] : ";
		errorPrefix += "[" + name + "] : ";
		promptPrefix += "[" + name.toUpperCase() + "] : ";
	}
	
	/**
	 * Checks if debug output is enabled
	 */
	public static boolean isDebug() {
		return debug;
	}
	
	/**
	 * Enables debug output.
	 */
	public static void setDebug() {
		debug = true;
	}
	/**
	 * Disables debug output.
	 */
	public static void clearDebug() {
		debug = false;
	}
	
	/**
	 * Prints an Output String with a Debug Level
	 * @param log Output String
	 */
	public void debug(String log) {
		if(debug) {
			System.out.println(debugPrefix + log);
		}
	}
	
	/**
	 * Prints an Output String with a Info Level
	 * @param log Output String
	 */
	public void info(String log) {
		System.out.println(infoPrefix + log);
	}
	
	/**
	 * Prints an Output String with a Warning Level
	 * @param log Output String
	 */
	public void warn(String log) {
		System.out.println(warnPrefix + log);
	}
	
	/**
	 * Prints an Output String with an Error Level
	 * @param log Output String
	 */
	public void error(String log) {
		System.out.println(errorPrefix + log);
	}
	
	/**
	 * Prints an Output String with a Prompt Level
	 * @param log Output String
	 */
	public void prompt(String log) {
		System.err.println(promptPrefix + log); //Using err makes this red and more noticable
	}
}