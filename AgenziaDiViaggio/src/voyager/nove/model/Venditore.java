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

package voyager.nove.model;

import gestioneutenti.model.competenze.Competenza;
import gestioneutenti.model.competenze.GestioneProfilo;
import gestioneutenti.model.competenze.SignIn;

public class Venditore extends AbstractRuolo{
	
	private static final long serialVersionUID = 1L;

	private static Venditore singletonVenditore = null;
	
	private static int ID = Ruolo.VENDITORE;
	private static String STRING = "Venditore";
	private static Competenza[] COMPETENZE = {GestioneProfilo.getInstance(), SignIn.getInstance()};

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
