/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.exception
 * 
 * @name DatiUtenteInconsistentiException.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.marciani.exception;

public class BeanInconsistenteException extends Exception {

	private static final long serialVersionUID = 1L;

	public BeanInconsistenteException() {
	}

	public BeanInconsistenteException(String msg) {
		super(msg);
	}

}
