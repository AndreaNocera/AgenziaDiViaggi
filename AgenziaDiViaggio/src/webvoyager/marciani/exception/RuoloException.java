/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.exception
 * 
 * @name RuoloException.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.marciani.exception;

public class RuoloException extends Exception {

	private static final long serialVersionUID = 1L;

	public RuoloException() {}

	public RuoloException(int ruolo) {
		super("Ruolo non esistente : " + ruolo);
	}

}
