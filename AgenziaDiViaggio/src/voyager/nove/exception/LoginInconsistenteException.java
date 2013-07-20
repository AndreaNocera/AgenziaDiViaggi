/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.exception
 * 
 * @name LoginInconsistenteException.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package voyager.nove.exception;

import voyager.nove.model.utente.Login;

public class LoginInconsistenteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public LoginInconsistenteException() {}

	public LoginInconsistenteException(Login login) {
		super("Login inconsistente : " + login.getUsername() + " " + login.getPassword());
	}

}
