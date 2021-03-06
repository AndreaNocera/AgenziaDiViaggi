/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.exception
 * 
 * @name LoginInconsistenteException.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.marciani.exception;

import webvoyager.marciani.model.Login;

public class LoginInconsistenteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public LoginInconsistenteException() {}

	public LoginInconsistenteException(Login login) {
		super("SignIn inconsistente : " + login.getUsername() + " " + login.getPassword());
	}

}
