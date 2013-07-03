/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.exception
 * 
 * @name RuoloException.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package gestioneutenti.exception;

public class RuoloException extends Exception {

	private static final long serialVersionUID = 1L;

	public RuoloException() {}

	public RuoloException(int ruolo) {
		super("Ruolo non esistente : " + ruolo);
	}

}
