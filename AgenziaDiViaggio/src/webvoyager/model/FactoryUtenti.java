/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model
 * 
 * @name FactoryUtenti.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.model;

import webvoyager.model.ruoli.FactoryRuoli;

public final class FactoryUtenti {
	
	private static FactoryUtenti singletonFactoryUtenti = null;
	private static FactoryRuoli factoryRuoli = null;

	private FactoryUtenti() {}

	public static synchronized FactoryUtenti getInstance() {
		if (singletonFactoryUtenti == null) {
			singletonFactoryUtenti = new FactoryUtenti();
			factoryRuoli = FactoryRuoli.getInstance();
			return singletonFactoryUtenti;
		}
		
		return singletonFactoryUtenti;
	}
	
	public Utente creaUtente(DatiUtente dati, int ruolo, Login login) {
		factoryRuoli = FactoryRuoli.getInstance();
		return new Utente(dati, factoryRuoli.assegnaRuolo(ruolo), login);				
	}

}
