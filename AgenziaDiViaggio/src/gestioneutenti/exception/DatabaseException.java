package gestioneutenti.exception;

import gestioneutenti.model.Login;

public class DatabaseException extends Exception {

	private static final long serialVersionUID = 1L;

	public DatabaseException() {}

	public DatabaseException(Login login) {
		super("Utente non esistente : " + login.getUsername() + " " + login.getPassword());
	}

}
