/**
 * @project WebVoyager
 * 
 * @package utils.mailer
 * 
 * @name Mailer.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package utils.mailer;

public interface Mailer {
	
	static final String MAIL = "giacomo.marciani.voyager@gmail.com";
	static final String USERNAME = "giacomo.marciani.voyager@gmail.com";	
	static final String PASSWORD = "ispwvoyager";
	
	void inviaMail(String mailDestinatario, String oggetto, String messaggio);

}
