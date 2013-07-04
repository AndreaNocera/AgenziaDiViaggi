/**
 * @project WebVoyager
 * 
 * @package home.helper
 * 
 * @name HelperHome.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package home.helper;

import java.util.Calendar;

import gestioneutenti.model.competenze.Competenza;
import gestioneutenti.model.ruoli.Ruolo;

public class HelperHome {
	
	private static HelperHome singletonHelperHome = null;

	private HelperHome() {}
	
	public static synchronized HelperHome getInstance() {
		if(singletonHelperHome == null) {
			singletonHelperHome = new HelperHome();
		}
		
		return singletonHelperHome;
	}
	
	public String getWelcome(String nome) {
		String html = new String();
		String WELCOME_MESSAGE_AM_PM = (Calendar.getInstance().get(Calendar.AM_PM) == Calendar.AM) ? "Buongiorno" : "Buonasera";
		
		html += "<p class = \"subtitle\" id = \"subtitleWelcomeMessage\">" + WELCOME_MESSAGE_AM_PM + " " + nome + "</p>"; 
	
		return html;
	}
	
	public String getHomePanel(Ruolo ruolo) {
		String html = new String();
		
		Competenza[] competenze = ruolo.getCompetenze();
		for (Competenza c : competenze) {
			html += "<p><button class = \"buttonCompetenza buttonIconLabelExtraLarge\" id = \"button" + c.asCompactString() + "\" name = \"competenza\" value = \"" + c.getId() + "\" type = \"submit\">" + c.asString() + "</button></p>";
		}
		
		return html;
	}

}
