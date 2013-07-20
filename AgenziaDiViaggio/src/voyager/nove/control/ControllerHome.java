package voyager.nove.control;

import javax.swing.JPanel;

import voyager.nove.model.utente.bean.UtenteBean;
import voyager.nove.model.utente.competenze.Competenza;
import voyager.nove.view.BoundaryAmministraUtenti;
import voyager.nove.view.BoundaryGestisciProfilo;
import voyager.nove.view.BoundaryLogin;

/**
 * @name ControllerHome
 *
 * @project Voyager
 *
 * @package voyager.nove.control
 *
 * @author Giacomo Marciani
 *
 */
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
