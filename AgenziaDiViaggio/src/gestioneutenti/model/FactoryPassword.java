package gestioneutenti.model;

/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model
 * 
 * @name FactoryResetCode.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

import java.util.Random;

public final class FactoryPassword {
	
	private static FactoryPassword singletonFactoryPassword = null;

	private FactoryPassword() {}

	public static synchronized FactoryPassword getInstance() {
		if (singletonFactoryPassword == null) {
			singletonFactoryPassword = new FactoryPassword();
			return singletonFactoryPassword;
		}
		
		return singletonFactoryPassword;
	}
	
	public String creaPassword() {
		Random randomGenerator = new Random();
		String password = String.valueOf(randomGenerator.nextInt(Integer.MAX_VALUE) + 1);
		return password;
	}

}
