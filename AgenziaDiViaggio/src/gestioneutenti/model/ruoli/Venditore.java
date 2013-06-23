package gestioneutenti.model.ruoli;

import gestioneutenti.model.competenze.Competenza;
import gestioneutenti.model.competenze.GestioneProfilo;
import gestioneutenti.model.competenze.Login;

public class Venditore extends AbstractRuolo{
	
	private static Venditore singletonVenditore = null;
	
	private static int ID = Ruolo.VENDITORE;
	private static String STRING = "Venditore";
	private static Competenza[] COMPETENZE = {GestioneProfilo.getInstance(), Login.getInstance()};

	protected Venditore(int id, String string, Competenza[] competenze) {
		super(id, string, competenze);
	}

	public static synchronized Ruolo getInstance() {
		if (singletonVenditore == null) {
			singletonVenditore = new Venditore(ID, STRING, COMPETENZE);
			return singletonVenditore;
		}
		
		return singletonVenditore;
	}

}
