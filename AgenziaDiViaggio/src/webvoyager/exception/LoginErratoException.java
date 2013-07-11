/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.exception
 * 
 * @name LoginErratoException.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.exception;

import webvoyager.model.Login;

public class LoginErratoException extends Exception {

	private static final long serialVersionUID = 1L;

	public LoginErratoException() {}

	public LoginErratoException(Login login) {
		super("SignIn errato : " + login.getUsername() + " " + login.getPassword());
	}

}
