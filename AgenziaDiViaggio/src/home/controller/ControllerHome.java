package home.controller;

/**
 * @project WebVoyager
 * 
 * @package home.controller
 * 
 * @name ControllerHome.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

import javax.swing.JPanel;

import gestioneutenti.model.bean.UtenteBean;
import gestioneutenti.model.competenze.Competenza;
import gestioneutenti.view.BoundaryAmministraUtenti;
import gestioneutenti.view.BoundaryGestisciProfilo;
import gestioneutenti.view.BoundaryLogin;

public class ControllerHome {
	
	public JPanel getBoundary(UtenteBean utenteBean, int competenza) {
		switch(competenza) {
		case Competenza.GESTIONE_PROFILO:
			BoundaryGestisciProfilo boundary = new BoundaryGestisciProfilo();
			boundary.setUtente(utenteBean);
			return new BoundaryGestisciProfilo();
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
	
	public void logout() {
		BoundaryLogin boundary = new BoundaryLogin();
		boundary.setVisible(true);
	}

}
