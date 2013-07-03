/**
 * @project WebVoyager
 * 
 * @package home.controller
 * 
 * @name ControllerHome.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package home.controller;

import javax.swing.JPanel;

import gestioneutenti.model.bean.UtenteBean;
import gestioneutenti.model.competenze.Competenza;
import gestioneutenti.view.BoundaryAmministraUtenti;
import gestioneutenti.view.BoundaryGestisciProfilo;
import gestioneutenti.view.BoundaryLogin;

public class ControllerHome {
	
	private static ControllerHome singletonControllerHome = null;
	
	private ControllerHome() {}
	
	public static synchronized ControllerHome getInstance() {
		if(singletonControllerHome == null) {
			singletonControllerHome = new ControllerHome();
		}
		
		return singletonControllerHome;
	}
	
	public synchronized JPanel getBoundary(UtenteBean utenteBean, int competenza) {
		switch(competenza) {
		case Competenza.GESTIONE_PROFILO:
			return new BoundaryGestisciProfilo(utenteBean);
		case Competenza.AMMINISTRAZIONE_UTENTI:
			return new BoundaryAmministraUtenti();
		case Competenza.GESTIONE_CATALOGO:
			return new JPanel();
		case Competenza.GESTIONE_OFFERTA:
			return new JPanel();
		default:
			return new JPanel();
		}
	}
	
	public synchronized void logout() {
		BoundaryLogin boundary = new BoundaryLogin();
		boundary.setVisible(true);
	}

}
