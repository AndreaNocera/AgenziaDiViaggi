package gestioneutenti.model;

import gestioneutenti.exception.RuoloException;
import gestioneutenti.model.ruoli.FactoryRuoli;

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
		try {
			return new Utente(dati, factoryRuoli.assegnaRuolo(ruolo), login);
		} catch (RuoloException e) {
			e.printStackTrace();
			return null;
		}				
	}

}
