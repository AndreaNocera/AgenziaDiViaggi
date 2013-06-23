package gestioneutenti.exception;

import gestioneutenti.model.Login;

public class LoginErratoException extends Exception {

	private static final long serialVersionUID = 1L;

	public LoginErratoException() {}

	public LoginErratoException(Login login) {
		super("Login errato : " + login.getUsername() + " " + login.getPassword());
	}

}
