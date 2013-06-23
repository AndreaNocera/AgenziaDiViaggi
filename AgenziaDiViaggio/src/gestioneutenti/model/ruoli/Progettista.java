package gestioneutenti.model.ruoli;

import gestioneutenti.model.competenze.Competenza;
import gestioneutenti.model.competenze.GestioneProfilo;
import gestioneutenti.model.competenze.Login;

public class Progettista extends AbstractRuolo{
	
	private static Progettista singletonProgettista = null;
	
	private static int ID = Ruolo.PROGETTISTA;
	private static String STRING = "Progettista";
	private static Competenza[] COMPETENZE = {GestioneProfilo.getInstance(), Login.getInstance()};

	protected Progettista(int id, String string, Competenza[] competenze) {
		super(id, string, competenze);
	}

	public static synchronized Ruolo getInstance() {
		if (singletonProgettista == null) {
			singletonProgettista = new Progettista(ID, STRING, COMPETENZE);
			return singletonProgettista;
		}
		
		return singletonProgettista;
	}

}
