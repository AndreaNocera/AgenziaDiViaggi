package gestioneutenti.model;

import gestioneutenti.model.ruoli.FactoryRuoli;
import gestioneutenti.model.ruoli.RuoloNonEsistenteException;

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
	
	public Utente creaUtente(DatiUtente dati, String mail, int ruolo, Login login) throws RuoloNonEsistenteException {
		return new Utente(dati, mail, factoryRuoli.assegnaRuolo(ruolo), login);		
	}

}
