/**
 * Utility class for logging information to Serenity reports.
 */

package utils;

import net.serenitybdd.core.Serenity;

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
		log("User info", info);
	}

	/**
	 * Logs contact information with a default title "Contact data".
	 *
	 * @param info The contact information to be logged.
	 */
	public static void logContactData(String info) {
		log("Contact data", info);
	}
}
