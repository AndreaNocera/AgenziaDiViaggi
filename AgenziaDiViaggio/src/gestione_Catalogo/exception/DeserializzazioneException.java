/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */


package gestione_Catalogo.exception;

public class DeserializzazioneException extends Exception {
	
	private static final long	serialVersionUID	= 1L;

	public DeserializzazioneException(){
		
	}
	
	public DeserializzazioneException(String m){
		super(m);
	}
	
}
