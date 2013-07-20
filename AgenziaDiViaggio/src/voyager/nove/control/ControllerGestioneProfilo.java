/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.controller
 * 
 * @name ControllerGestioneProfilo.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package voyager.nove.control;

import voyager.nove.exception.DatiUtenteInconsistentiException;
import voyager.nove.exception.LoginInconsistenteException;
import voyager.nove.exception.UtenteInesistenteException;
import voyager.nove.model.utente.Utente;
import voyager.nove.model.utente.bean.UtenteBean;
import voyager.nove.persistence.dao.UtenteDAO;
import voyager.nove.persistence.dao.UtenteJDBCDAO;

public class ControllerGestioneProfilo {
	
	private static ControllerGestioneProfilo singletonControllerGestisciProfilo = null;
	private static UtenteDAO utenteDAO = null;
	
	private ControllerGestioneProfilo() {}
	
	public static synchronized ControllerGestioneProfilo getInstance() {
		if(singletonControllerGestisciProfilo == null) {
			singletonControllerGestisciProfilo = new ControllerGestioneProfilo();
		}
		
		utenteDAO = UtenteJDBCDAO.getInstance();
		
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
