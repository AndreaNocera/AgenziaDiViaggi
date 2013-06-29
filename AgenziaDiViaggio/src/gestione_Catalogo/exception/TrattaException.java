package gestione_Catalogo.exception;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class TrattaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public TrattaException(){
		
	}
	
	public TrattaException(String m){
		super(m);
	}
	
	public String toString(){
		return "Tratta gia' esistente";
	}

}
