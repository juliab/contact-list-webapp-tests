package utils;

import net.serenitybdd.core.Serenity;

/**
 * Utility class for logging information to Serenity reports.
 */
public class Logger {
	
	/**
     * Logs the provided information with a custom title.
     *
     * @param title The title for the log entry.
     * @param info  The information to be logged.
     */
	public static void log(String title, String info) {
		Serenity.recordReportData().withTitle(title).andContents(info);
	}
	
	/**
     * Logs user-related information with a default title "User info".
     *
     * @param info The user-related information to be logged.
     */
	public static void logUserInfo(String info) {
		Serenity.recordReportData().withTitle("User info").andContents(info);
	}
}
