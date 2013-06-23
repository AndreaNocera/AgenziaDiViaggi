package gestioneutenti.exception;

import gestioneutenti.model.Login;

public class LoginException extends Exception {

	private static final long serialVersionUID = 1L;

	public LoginException() {}

	public LoginException(Login login) {
		super("Login errato : " + login.getUsername() + " " + login.getPassword());
	}

}
