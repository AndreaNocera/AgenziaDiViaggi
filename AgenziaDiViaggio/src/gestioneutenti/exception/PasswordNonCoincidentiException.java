/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.exception
 * 
 * @name PasswordNonCoincidentiException.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package gestioneutenti.exception;

public class PasswordNonCoincidentiException extends Exception {

	private static final long serialVersionUID = 1L;

	public PasswordNonCoincidentiException() {
		super();
	}

	public PasswordNonCoincidentiException(String msg) {
		super(msg);
	}

}
