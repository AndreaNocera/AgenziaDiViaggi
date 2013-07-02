
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
