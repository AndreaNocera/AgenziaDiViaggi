/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */


package gestione_Catalogo.exception;

public class SerializzazioneException extends Exception {
	
	private static final long	serialVersionUID	= 1L;

	public SerializzazioneException(){
		
	}
	
	public SerializzazioneException(String m){
		super(m);
	}
	
}
