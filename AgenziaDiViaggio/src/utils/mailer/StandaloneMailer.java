/**
 * @project WebVoyager
 * 
 * @package utils.mailer
 * 
 * @name StandaloneMailer.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package utils.mailer;

public final class StandaloneMailer extends AbstractMailer {
	
	private static StandaloneMailer singletonMailer = null;

	protected StandaloneMailer() {}

	public static synchronized StandaloneMailer getInstance() {
		if (singletonMailer == null) {
			singletonMailer = new StandaloneMailer();
		}
		
		return singletonMailer;
	}

}
