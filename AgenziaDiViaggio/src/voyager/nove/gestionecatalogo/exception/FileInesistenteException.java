/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */


package voyager.nove.gestionecatalogo.exception;

public class FileInesistenteException extends Exception {
	
	private static final long	serialVersionUID	= 1L;

	public FileInesistenteException(){
		
	}
	
	public FileInesistenteException(String m){
		super (m);
	}
	
	public String toString(){
		return "File Inesistente";
	}
}
