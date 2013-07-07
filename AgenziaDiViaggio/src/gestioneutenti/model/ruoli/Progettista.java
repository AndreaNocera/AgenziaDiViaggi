/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model.ruoli
 * 
 * @name Progettista.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package gestioneutenti.model.ruoli;

import gestioneutenti.model.competenze.Competenza;
import gestioneutenti.model.competenze.GestioneProfilo;
import gestioneutenti.model.competenze.SignIn;

public class Progettista extends AbstractRuolo{
	
	private static final long serialVersionUID = 1L;

	private static Progettista singletonProgettista = null;
	
	private static int ID = Ruolo.PROGETTISTA;
	private static String STRING = "Progettista";
	private static Competenza[] COMPETENZE = {GestioneProfilo.getInstance(), SignIn.getInstance()};

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
