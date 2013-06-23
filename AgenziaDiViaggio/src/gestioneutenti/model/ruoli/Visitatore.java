package gestioneutenti.model.ruoli;

import gestioneutenti.model.competenze.Competenza;
import gestioneutenti.model.competenze.GestioneProfilo;
import gestioneutenti.model.competenze.Login;

//singleton

public class Visitatore extends AbstractRuolo {
	
	private static Visitatore singletonVisitatore = null;
	
	private static int ID = Ruolo.VISITATORE;
	private static String STRING = "Visitatore";
	private static Competenza[] COMPETENZE = {GestioneProfilo.getInstance(), Login.getInstance()};	

	protected Visitatore(int id, String string, Competenza[] competenze) {
		super(id, string, competenze);
	}

	public static synchronized Ruolo getInstance() {
		if (singletonVisitatore == null) {
			singletonVisitatore = new Visitatore(ID, STRING, COMPETENZE);
			return singletonVisitatore;
		}
		
		return singletonVisitatore;
	}
	
}
