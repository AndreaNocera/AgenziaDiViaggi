
/**
 * @project WebVoyager
 * 
 * @package utils.mailer
 * 
 * @name WebMailer.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package utils.mailer;

import javax.mail.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class WebMailer extends AbstractMailer {
	
	private static WebMailer singletonWebMailer = null;

	protected WebMailer() {
		try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            session = (Session) envContext.lookup("mail/Session");             
        } catch (NamingException e) {
            e.printStackTrace();
        }
	}
	
	public static synchronized WebMailer getInstance() {
		if (singletonWebMailer == null) {
			singletonWebMailer = new WebMailer();
		}
		
		return singletonWebMailer;
	}

}
