/**
 * @project WebVoyager
 * 
 * @package home.helper
 * 
 * @name HelperAmministraUtenti.java
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
	
	private static String PATH_LOGO = "common/img/Voyager.png";

	private HelperHome() {}
	
	public static synchronized HelperHome getInstance() {
		if(singletonHelperHome == null) {
			singletonHelperHome = new HelperHome();
		}
		
		return singletonHelperHome;
	}
	
	public String getLogo() {
		String html = new String();
		
		html += "<div class = \"panelLogo\" id = \"panelLogo\" align = \"center\">\n\n" +
				"<img class = \"logo\" id = \"logoVoyager\" border = \"0\" src = \"" + PATH_LOGO + "\" >\n\n" +
				"</div>";
			
		return html;
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
			html += "<p><button class = \"buttonCompetenza buttonIconLabelExtraLarge\" id = \"button" + c.asCompactString() + "\" name = \"competenza\" value = \"" + c.getId() + "\" type = \"button\">" + c.asString() + "</button></p>";		}
		
		return html;
	}

}
