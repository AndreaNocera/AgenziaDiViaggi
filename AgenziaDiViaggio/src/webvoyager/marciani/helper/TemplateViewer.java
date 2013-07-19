/**
 * @project WebVoyager
 * 
 * @package home.helper
 * 
 * @name TemplateViewer.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.marciani.helper;


import java.util.Calendar;

import webvoyager.marciani.model.competenze.Competenza;
import webvoyager.marciani.model.ruoli.Ruolo;

public class TemplateViewer {
	
	private static TemplateViewer singletonHelperAmministraUtenti = null;
	
	private static String PATH_LOGO = "common/img/Voyager.png";

	private TemplateViewer() {}
	
	public static synchronized TemplateViewer getInstance() {
		if(singletonHelperAmministraUtenti == null) {
			singletonHelperAmministraUtenti = new TemplateViewer();
		}
		
		return singletonHelperAmministraUtenti;
	}
	
	public String getLogo() {
		String html = new String();
		
		html += "<img class = \"logo\" id = \"logoVoyager\" border = \"0\" src = \"" + PATH_LOGO + "\">";
		
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
