package gestioneutenti.model;

public interface Competenza {
	
	public static final int LOGIN = 0;
	public static final int GESTISCI_PROFILO = 1;
	public static final int AMMINISTRA_UTENTI = 2;
	
	int getId();
	
	String asString();
	
	String getJSP();
	
	Class<?> getController();	
	
}
