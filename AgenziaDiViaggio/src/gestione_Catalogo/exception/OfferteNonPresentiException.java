package gestione_Catalogo.exception;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class OfferteNonPresentiException extends Exception {
	private static final long serialVersionUID = 1L;

	public OfferteNonPresentiException(){
		
	}
	
	public OfferteNonPresentiException(String m){
		super(m);
	}

	public String toString(){
		return "Non ci sono offerte per il viaggio.";
	}

}
