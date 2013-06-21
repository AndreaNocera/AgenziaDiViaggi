package gestioneutenti.model.ruoli;

import gestioneutenti.model.Competenza;

public class Amministratore extends AbstractRuolo {
	
	private static Amministratore singletonAmministratore = null;
	
	static int ID = Ruolo.AMMINISTRATORE;
	static String STRING = "Amministratore";
	static Competenza[] COMPETENZE = null;

	protected Amministratore(int id, String string, Competenza[] competenze) {
		super(id, string, competenze);
	}
	
	public static synchronized Amministratore getInstance() {
		if (singletonAmministratore == null) {
			singletonAmministratore = new Amministratore(ID, STRING, COMPETENZE);
			return singletonAmministratore;
		}
		
		return singletonAmministratore;
	}

}
