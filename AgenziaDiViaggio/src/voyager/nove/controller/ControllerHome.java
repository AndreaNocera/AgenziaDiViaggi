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

package voyager.nove.controller;

import javax.swing.JPanel;

import voyager.nove.model.Competenza;
import voyager.nove.model.bean.UtenteBean;
import voyager.nove.view.BoundaryAmministraUtenti;
import voyager.nove.view.BoundaryGestisciProfilo;
import voyager.nove.view.BoundaryLogin;

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
