package gestioneutenti.model.competenze;

public class AmministrazioneUtenti extends AbstractCompetenza {
	
	private static AmministrazioneUtenti singletonAmministrazioneUtenti;
	
	private static int ID = Competenza.AMMINISTRAZIONE_UTENTI;
	private static String STRING = "Amministra Utenti";

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
