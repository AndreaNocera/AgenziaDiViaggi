
package gestioneutenti.exception;

import gestioneutenti.model.Login;

public class LoginInconsistenteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public LoginInconsistenteException() {}

	public LoginInconsistenteException(Login login) {
		super("Login inconsistente : " + login.getUsername() + " " + login.getPassword());
	}

}
