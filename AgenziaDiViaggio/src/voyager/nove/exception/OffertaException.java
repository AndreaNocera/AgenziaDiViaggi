/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */


package voyager.nove.exception;

/*
 * Eccezione per la presenza di offerte di un viaggio da rimuovere
 */

public class OffertaException extends Exception {
	

	private static final long serialVersionUID = 1L;

	public OffertaException(){
		
	}
	
	public OffertaException(String m){
		super(m);
	}

	public String toString(){
		return "Offerte esistenti per il viaggio";
	}

}
