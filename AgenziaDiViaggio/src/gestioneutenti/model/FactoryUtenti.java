package gestioneutenti.model;

import gestioneutenti.exception.RuoloException;
import gestioneutenti.model.ruoli.FactoryRuoli;
import gestioneutenti.model.ruoli.Ruolo;

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
	
	public Utente creaUtente(DatiUtente dati, String mail, int ruolo, Login login) {
		factoryRuoli = FactoryRuoli.getInstance();
		Ruolo mRuolo;
		try {
			mRuolo = factoryRuoli.assegnaRuolo(ruolo);
			return new Utente(dati, mail, mRuolo, login);
		} catch (RuoloException e) {
			e.printStackTrace();
			return null;
		}				
	}

}
