/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model.ruoli
 * 
 * @name Visitatore.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package voyager.nove.model;

public class Visitatore extends AbstractRuolo {
	
	private static final long serialVersionUID = 1L;

	private static Visitatore singletonVisitatore = null;
	
	private static int ID = Ruolo.VISITATORE;
	private static String STRING = "Visitatore";
	private static Competenza[] COMPETENZE = {GestioneProfilo.getInstance(), SignIn.getInstance()};	

	protected Visitatore(int id, String string, Competenza[] competenze) {
		super(id, string, competenze);
	}

	public static synchronized Ruolo getInstance() {
		if (singletonVisitatore == null) {
			singletonVisitatore = new Visitatore(ID, STRING, COMPETENZE);
			return singletonVisitatore;
		}
		
		return singletonVisitatore;
	}
	
}
