/**
 * 
 */
package gestione_Catalogo.exception;

/**
 * @authors
 * Remo Sperlongano
 * Ivan Torre
 */
public class OffertaInesistenteException extends Exception {
	

	private static final long serialVersionUID = 1L;

	public OffertaInesistenteException(){
		
	}
	
	public OffertaInesistenteException(String m){
		super(m);
	}

	public String toString(){
		return "Offerta inesistente";
	}

}
