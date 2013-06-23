package gestioneutenti.model.ruoli;

import gestioneutenti.model.competenze.Competenza;
import gestioneutenti.model.competenze.GestioneCatalogo;
import gestioneutenti.model.competenze.GestioneOfferta;
import gestioneutenti.model.competenze.GestioneProfilo;
import gestioneutenti.model.competenze.Login;

public class Promotore extends AbstractRuolo{
	
	private static Promotore singletonPromotore = null;
	
	private static int ID = Ruolo.PROMOTORE;
	private static String STRING = "Promotore";
	private static Competenza[] COMPETENZE = {GestioneCatalogo.getInstance(), GestioneOfferta.getInstance(), GestioneProfilo.getInstance(), Login.getInstance()};

	protected Promotore(int id, String string, Competenza[] competenze) {
		super(id, string, competenze);
	}

	public static synchronized Ruolo getInstance() {
		if (singletonPromotore == null) {
			singletonPromotore = new Promotore(ID, STRING, COMPETENZE);
			return singletonPromotore;
		}
		
		return singletonPromotore;
	}

}
