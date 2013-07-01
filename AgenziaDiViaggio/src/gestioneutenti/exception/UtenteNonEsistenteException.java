package gestioneutenti.exception;

/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.exception
 * 
 * @name UtenteNonEsistenteException.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

public class UtenteNonEsistenteException extends Exception {
	private static final long serialVersionUID = 19898L;

	public UtenteNonEsistenteException(String message) {
		super(message);
		}
}
