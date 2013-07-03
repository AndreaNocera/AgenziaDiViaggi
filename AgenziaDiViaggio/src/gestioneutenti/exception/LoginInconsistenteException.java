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

package gestioneutenti.exception;

import gestioneutenti.model.Login;

public class LoginInconsistenteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public LoginInconsistenteException() {}

	public LoginInconsistenteException(Login login) {
		super("SignIn inconsistente : " + login.getUsername() + " " + login.getPassword());
	}

}
