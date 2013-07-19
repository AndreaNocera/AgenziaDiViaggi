/**
 * @project WebVoyager
 * 
 * @package utils
 * 
 * @name DateUtils.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.marciani.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtils {

	public DateUtils() {}
	
	public static GregorianCalendar getGregorianCalendarFromString(String str) {
		String data[] = str.split("/");
		
		int month = (int) Integer.parseInt(data[0]);
		int day = (int) Integer.parseInt(data[1]);
		int year = (int) Integer.parseInt(data[2]);
		
		return new GregorianCalendar(year, month, day);
	}
	
	public static String getStringFromGregorianCalendar(GregorianCalendar cal) {
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		
		String str = month + "/" + day + "/" + year;
		
		return str;
	}
	
	public static String getDBStringFromGregorianCalendar(GregorianCalendar cal) {
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		
		String str = year + "/" + month + "/" + day;
		
		return str;
	}

}
