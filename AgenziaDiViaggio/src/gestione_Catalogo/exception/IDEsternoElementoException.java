/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */


package gestione_Catalogo.exception;

public class IDEsternoElementoException extends Exception {

	private static final long serialVersionUID = 1L;

	public IDEsternoElementoException(){
		
	}
	
	public IDEsternoElementoException(String m){
		super(m);
	}

	public String toString(){
		return "Elemento non presente.";
	}
	
}
