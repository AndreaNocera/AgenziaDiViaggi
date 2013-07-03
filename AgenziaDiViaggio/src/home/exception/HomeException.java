/**
 * @project WebVoyager
 * 
 * @package home.exception
 * 
 * @name HomeException.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package home.exception;

public class HomeException extends Exception {

	private static final long serialVersionUID = 1L;

	public HomeException() {
	}

	public HomeException(String msg) {
		super(msg);
	}

}
