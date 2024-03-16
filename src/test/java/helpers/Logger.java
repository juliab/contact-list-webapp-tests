package helpers;

import net.serenitybdd.core.Serenity;

public class Logger {
	
	public static void log(String title, String info) {
		Serenity.recordReportData().withTitle(title).andContents(info);
	}
	
	public static void logUserInfo(String info) {
		Serenity.recordReportData().withTitle("User info").andContents(info);
	}
}
	