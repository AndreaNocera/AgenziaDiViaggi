/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model.ruoli
 * 
 * @name Amministratore.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package gestioneutenti.model.ruoli;

import gestioneutenti.model.competenze.AmministrazioneUtenti;
import gestioneutenti.model.competenze.Competenza;
import gestioneutenti.model.competenze.GestioneCatalogo;
import gestioneutenti.model.competenze.GestioneOfferta;
import gestioneutenti.model.competenze.GestioneProfilo;
import gestioneutenti.model.competenze.SignIn;

public class Amministratore extends AbstractRuolo {
	
	private static final long serialVersionUID = 1L;

	private static Amministratore singletonAmministratore = null;
	
	static int ID = Ruolo.AMMINISTRATORE;
	static String STRING = "Amministratore";
	static Competenza[] COMPETENZE = {AmministrazioneUtenti.getInstance(), GestioneCatalogo.getInstance(), GestioneOfferta.getInstance(), GestioneProfilo.getInstance(), SignIn.getInstance()};

	protected Amministratore(int id, String string, Competenza[] competenze) {
		super(id, string, competenze);
	}
	
	public static synchronized Amministratore getInstance() {
		if (singletonAmministratore == null) {
			singletonAmministratore = new Amministratore(ID, STRING, COMPETENZE);
			return singletonAmministratore;
		}
		
		return singletonAmministratore;
	}

}
