/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.exception
 * 
 * @name LoginErratoException.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package voyager.nove.exception;

import voyager.nove.model.utente.Login;

public class LoginErratoException extends Exception {

	private static final long serialVersionUID = 1L;

	public LoginErratoException() {}

	public LoginErratoException(Login login) {
		super("Login errato : " + login.getUsername() + " " + login.getPassword());
	}

}
