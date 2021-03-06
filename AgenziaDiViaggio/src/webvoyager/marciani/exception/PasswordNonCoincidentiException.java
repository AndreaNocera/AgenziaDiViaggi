/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.exception
 * 
 * @name PasswordNonCoincidentiException.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.marciani.exception;

public class PasswordNonCoincidentiException extends Exception {

	private static final long serialVersionUID = 1L;

	public PasswordNonCoincidentiException() {
		super();
	}

	public PasswordNonCoincidentiException(String msg) {
		super(msg);
	}

}
