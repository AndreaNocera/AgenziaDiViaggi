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
import gestioneutenti.exception.PasswordNonCoincidentiException;
import gestioneutenti.exception.UtenteInesistenteException;
import gestioneutenti.model.bean.LoginBean;
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

	public void aggiornaProfilo(LoginBean loginBean, UtenteBean nUtenteBean) throws UtenteInesistenteException {
		if (utenteDAO.verifyLogin(loginBean)) {
			utenteDAO.update(nUtenteBean);
		}
	}
	
	public void aggiornaProfilo(LoginBean loginBean, UtenteBean nUtenteBean, String confermaNuovaPassword) throws UtenteInesistenteException, PasswordNonCoincidentiException {
		if (utenteDAO.verifyLogin(loginBean) && matchPassword(nUtenteBean.getPassword(), confermaNuovaPassword)) {
			utenteDAO.update(nUtenteBean);
		}				
	}
	
	public boolean matchPassword(String password1, String password2) throws PasswordNonCoincidentiException {
		if (password1.equals(password2)) {
			return true;
		} else {
			throw new PasswordNonCoincidentiException();
		}
	}

}
