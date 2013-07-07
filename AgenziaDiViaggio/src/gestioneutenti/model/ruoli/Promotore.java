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

package gestioneutenti.model.ruoli;

import gestioneutenti.model.competenze.Competenza;
import gestioneutenti.model.competenze.GestioneCatalogo;
import gestioneutenti.model.competenze.GestioneOfferta;
import gestioneutenti.model.competenze.GestioneProfilo;
import gestioneutenti.model.competenze.SignIn;

public class Promotore extends AbstractRuolo{
	
	private static final long serialVersionUID = 1L;

	private static Promotore singletonPromotore = null;
	
	private static int ID = Ruolo.PROMOTORE;
	private static String STRING = "Promotore";
	private static Competenza[] COMPETENZE = {GestioneCatalogo.getInstance(), GestioneOfferta.getInstance(), GestioneProfilo.getInstance(), SignIn.getInstance()};

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
