/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model.ruoli
 * 
 * @name Promotore.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package voyager.nove.model.utente.ruolo;

import voyager.nove.model.utente.competenze.Competenza;
import voyager.nove.model.utente.competenze.GestioneCatalogo;
import voyager.nove.model.utente.competenze.GestioneOfferta;
import voyager.nove.model.utente.competenze.GestioneProfilo;
import voyager.nove.model.utente.competenze.Login;

public class Promotore extends AbstractRuolo{
	
	private static final long serialVersionUID = 1L;

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
