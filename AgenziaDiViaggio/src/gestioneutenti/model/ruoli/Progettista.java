package gestioneutenti.model.ruoli;

import gestioneutenti.model.Competenza;

public class Progettista extends AbstractRuolo{
	
	private static Progettista singletonProgettista = null;
	
	private static int ID = Ruolo.PROGETTISTA;
	private static String STRING = "Progettista";
	private static Competenza[] COMPETENZE = null;

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
