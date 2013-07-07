/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model.ruoli
 * 
 * @name Cliente.java
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

public class Cliente extends AbstractRuolo {
	
	private static final long serialVersionUID = 1L;

	private static Cliente singletonCliente = null;
	
	private static int ID = Ruolo.CLIENTE;
	private static String STRING = "Cliente";
	private static Competenza[] COMPETENZE = {GestioneProfilo.getInstance(), SignIn.getInstance()};

	protected Cliente(int id, String string, Competenza[] competenze) {
		super(id, string, competenze);
	}

	public static synchronized Ruolo getInstance() {
		if (singletonCliente == null) {
			singletonCliente = new Cliente(ID, STRING, COMPETENZE);
			return singletonCliente;
		}
		
		return singletonCliente;
	}

}
