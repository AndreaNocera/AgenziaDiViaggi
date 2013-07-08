/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.controller
 * 
 * @name ControllerGestisciProfilo.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package gestioneutenti.controller;

import gestioneutenti.dao.UtenteDAO;
import gestioneutenti.dao.UtenteJdbcDAO;
import gestioneutenti.exception.DatiUtenteInconsistentiException;
import gestioneutenti.exception.LoginInconsistenteException;
import gestioneutenti.exception.UtenteInesistenteException;
import gestioneutenti.model.Utente;
import gestioneutenti.model.bean.UtenteBean;

public class ControllerGestisciProfilo {
	
	private static ControllerGestisciProfilo singletonControllerGestisciProfilo = null;
	private static UtenteDAO utenteDAO = null;
	
	private ControllerGestisciProfilo() {}
	
	public static synchronized ControllerGestisciProfilo getInstance() {
		if(singletonControllerGestisciProfilo == null) {
			singletonControllerGestisciProfilo = new ControllerGestisciProfilo();
		}
		
		utenteDAO = UtenteJdbcDAO.getInstance();
		
		return singletonControllerGestisciProfilo;
	}
	
	public static synchronized ControllerGestisciProfilo getWebInstance() {
		if(singletonControllerGestisciProfilo == null) {
			singletonControllerGestisciProfilo = new ControllerGestisciProfilo();
		}
		
		utenteDAO = UtenteJdbcDAO.getWebInstance();
		
		return singletonControllerGestisciProfilo;
	}

	public UtenteBean findByUsername(String username) throws UtenteInesistenteException {
		return utenteDAO.findByUsername(username);
	}
	
	public void aggiornaProfilo(UtenteBean utenteBean) throws DatiUtenteInconsistentiException, LoginInconsistenteException {
		Utente utente = new Utente().fromBean(utenteBean);
		utenteDAO.update(utente);
	}

}
