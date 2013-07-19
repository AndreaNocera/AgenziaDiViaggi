/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model
 * 
 * @name FactoryPassword.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.marciani.model;

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
