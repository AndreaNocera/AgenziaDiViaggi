/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model.ruoli
 * 
 * @name Venditore.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package voyager.nove.model.utente.ruolo;

import voyager.nove.model.utente.competenze.Competenza;
import voyager.nove.model.utente.competenze.GestioneProfilo;
import voyager.nove.model.utente.competenze.Login;

public class Venditore extends AbstractRuolo{
	
	private static final long serialVersionUID = 1L;

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
