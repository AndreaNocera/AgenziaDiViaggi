package gestione_Catalogo.exception;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class TrattaInesistenteException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public TrattaInesistenteException(){
		
	}
	
	public TrattaInesistenteException(String m){
		super(m);
	}
	
	public String toString(){
		return "Tratta non esistente";
	}
}
