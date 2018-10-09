package util;

public class Logger {
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

	public static void setDebug() {
		debug = true;
	}
	public static void clearDebug() {
		debug = false;
	}

	public void debug(String log) {
		if(debug) {
			System.out.println(debugPrefix + log);
		}
	}
	public void info(String log) {
		System.out.println(infoPrefix + log);
	}
	public void warn(String log) {
		System.out.println(warnPrefix + log);
	}
	public void error(String log) {
		System.out.println(errorPrefix + log);
	}
	public void prompt(String log) {
		System.err.println(promptPrefix + log);
	}
}
