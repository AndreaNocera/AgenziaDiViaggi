package gestione_Catalogo.exception;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class PrenotazioneException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public PrenotazioneException(){
		
	}
	
	public PrenotazioneException(String m){
		super(m);
	}

	public String toString(){
		return "Prenotazioni esistenti per l'offerta";
	}

}
