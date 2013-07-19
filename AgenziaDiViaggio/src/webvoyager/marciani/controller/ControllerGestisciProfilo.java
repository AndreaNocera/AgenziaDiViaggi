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

package webvoyager.marciani.controller;

import webvoyager.marciani.dao.UtenteDAO;
import webvoyager.marciani.dao.UtenteJdbcDAO;
import webvoyager.marciani.exception.BeanInconsistenteException;
import webvoyager.marciani.exception.UtenteEsistenteException;
import webvoyager.marciani.exception.UtenteInesistenteException;
import webvoyager.marciani.model.Login;
import webvoyager.marciani.model.Utente;
import webvoyager.marciani.model.bean.LoginBean;
import webvoyager.marciani.model.bean.UtenteBean;

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
