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

package gestioneutenti.helper;

import java.util.List;

import gestioneutenti.model.bean.UtenteBean;
public class HelperAmministraUtenti {
	
	private static HelperAmministraUtenti singletonHelperAmministraUtenti = null;
	
	private static String PATH_LOGO = "common/img/Voyager.png";

	private HelperAmministraUtenti() {}
	
	public static synchronized HelperAmministraUtenti getInstance() {
		if(singletonHelperAmministraUtenti == null) {
			singletonHelperAmministraUtenti = new HelperAmministraUtenti();
		}
		
		return singletonHelperAmministraUtenti;
	}
	
	public String getLogo() {
		String html = new String();
		
		html += "<div class = \"panelLogo\" id = \"panelLogo\" align = \"center\">\n\n" +
				"<img class = \"logo\" id = \"logoVoyager\" border = \"0\" src = \"" + PATH_LOGO + "\" >\n\n" +
				"</div>";
			
		return html;
	}
	
	public String getListaUtenti(List<UtenteBean> utenti) {
		String html = new String();
		
		if(utenti == null) {
			html += ("<li class = \"ui-widget-content\">" + "admin" + "</li>");
			return html;
		}
		
		for (UtenteBean utente : utenti) {
			html += ("<li class = \"ui-widget-content\">" + utente.getUsername() + "</li>");
		}
		
		return html;
	}

}
