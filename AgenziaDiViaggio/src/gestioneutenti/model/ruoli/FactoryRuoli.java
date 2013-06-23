package gestioneutenti.model.ruoli;

import gestioneutenti.exception.RuoloException;

public final class FactoryRuoli {
	
	private static FactoryRuoli singletonFactoryRuoli = null;

	private FactoryRuoli() {}

	public static synchronized FactoryRuoli getInstance() {
		if (singletonFactoryRuoli == null) {
			singletonFactoryRuoli = new FactoryRuoli();
			return singletonFactoryRuoli;
		}
		
		return singletonFactoryRuoli;
	}
	
	public Ruolo assegnaRuolo(int ruolo) throws RuoloException {
		switch(ruolo) {
		case Ruolo.AMMINISTRATORE:
			return Amministratore.getInstance();
		case Ruolo.PROMOTORE:
			return Promotore.getInstance();
		case Ruolo.PROGETTISTA:
			return Progettista.getInstance();
		case Ruolo.VENDITORE:
			return Venditore.getInstance();
		case Ruolo.CLIENTE:
			return Cliente.getInstance();
		case Ruolo.VISITATORE:
			return Visitatore.getInstance();
		default:
			throw new RuoloException(ruolo);
		}		
	}

}
