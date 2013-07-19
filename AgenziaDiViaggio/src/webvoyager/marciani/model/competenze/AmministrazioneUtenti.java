/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model.competenze
 * 
 * @name AmministrazioneUtenti.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.marciani.model.competenze;

public class AmministrazioneUtenti extends AbstractCompetenza {
	
	private static final long serialVersionUID = 1L;

	private static AmministrazioneUtenti singletonAmministrazioneUtenti;
	
	private static int ID = Competenza.AMMINISTRAZIONE_UTENTI;
	private static String STRING = "Amministrazione Utenti";

	protected AmministrazioneUtenti(int id, String string) {
		super(id, string);
	}
	
	public static synchronized AmministrazioneUtenti getInstance() {
		if (singletonAmministrazioneUtenti == null) {
			singletonAmministrazioneUtenti = new AmministrazioneUtenti(ID, STRING);
			return singletonAmministrazioneUtenti;
		}
		
		return singletonAmministrazioneUtenti;
	}

}
