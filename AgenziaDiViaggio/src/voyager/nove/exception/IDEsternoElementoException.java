/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */


package voyager.nove.exception;

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
