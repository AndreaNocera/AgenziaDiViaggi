/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.controller
 * 
 * @name ControllerGestisciProfilo.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.controller;

import webvoyager.dao.UtenteDAO;
import webvoyager.dao.UtenteJdbcDAO;
import webvoyager.exception.BeanInconsistenteException;
import webvoyager.exception.UtenteEsistenteException;
import webvoyager.exception.UtenteInesistenteException;
import webvoyager.model.Login;
import webvoyager.model.Utente;
import webvoyager.model.bean.LoginBean;
import webvoyager.model.bean.UtenteBean;

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

	public UtenteBean findByUsername(String username) throws UtenteInesistenteException {
		return utenteDAO.findByUsername(username);
	}
	
	public UtenteBean getUtente(LoginBean loginBean) throws BeanInconsistenteException, UtenteInesistenteException {
		Login login = new Login().fromBean(loginBean);
		return utenteDAO.findByLogin(login);
	}
	
	public void aggiornaProfilo(UtenteBean utenteBean) throws BeanInconsistenteException {
		Utente utente = new Utente().fromBean(utenteBean);
		try {
			utenteDAO.update(utente);
		} catch (UtenteEsistenteException exc) {
			exc.printStackTrace();
		}
	}

}
