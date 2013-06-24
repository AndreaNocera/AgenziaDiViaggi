package gestioneutenti.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public final class FactoryResetCode {
	
	private static FactoryResetCode singletonFactoryResetCode = null;

	private FactoryResetCode() {}

	public static synchronized FactoryResetCode getInstance() {
		if (singletonFactoryResetCode == null) {
			singletonFactoryResetCode = new FactoryResetCode();
			return singletonFactoryResetCode;
		}
		
		return singletonFactoryResetCode;
	}
	
	public ResetCode creaResetCode() {
		Random randomGenerator = new Random();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return new ResetCode(randomGenerator.nextInt(), calendar.getTime());			
	}

}
