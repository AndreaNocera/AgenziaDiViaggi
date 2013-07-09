
package utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtils {

	public DateUtils() {}
	
	public static GregorianCalendar getGregorianCalendarFromString(String str) {
		String data[] = str.split("/");
		int year = (int) Integer.parseInt(data[0]);
		int month = (int) Integer.parseInt(data[1]);
		int day = (int) Integer.parseInt(data[2]);
		
		return new GregorianCalendar(year, month, day);
	}
	
	public static String getStringFromGregorianCalendar(GregorianCalendar cal) {
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		
		String str = month + "/" + day + "/" + year;
		
		return str;
	}

}
