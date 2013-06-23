package gestioneutenti.model.competenze;

public class GestioneOfferta extends AbstractCompetenza {
	
	private static GestioneOfferta singletonGestioneOfferta;
	
	private static int ID = Competenza.GESTIONE_OFFERTA;
	private static String STRING = "Gestione Offerta";

	protected GestioneOfferta(int id, String string) {
		super(id, string);
	}
	
	public static synchronized GestioneOfferta getInstance() {
		if (singletonGestioneOfferta == null) {
			singletonGestioneOfferta = new GestioneOfferta(ID, STRING);
			return singletonGestioneOfferta;
		}
		
		return singletonGestioneOfferta;
	}

}
