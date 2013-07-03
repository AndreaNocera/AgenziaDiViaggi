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
	private UtenteDAO utenteDAO = null;
	
	private ControllerGestisciProfilo() {
		this.utenteDAO = UtenteJdbcDAO.getInstance();
	}
	
	public static synchronized ControllerGestisciProfilo getInstance() {
		if(singletonControllerGestisciProfilo == null) {
			singletonControllerGestisciProfilo = new ControllerGestisciProfilo();
		}
		
		return singletonControllerGestisciProfilo;
	}

	public UtenteBean findByUsername(String username) throws UtenteInesistenteException {
		return this.utenteDAO.findByUsername(username);
	}

	public void aggiornaProfilo(LoginBean loginBean, UtenteBean nUtenteBean) throws UtenteInesistenteException {
		if (this.utenteDAO.verifyLogin(loginBean)) {
			this.utenteDAO.update(nUtenteBean);
		}
	}
	
	public void aggiornaProfilo(LoginBean loginBean, UtenteBean nUtenteBean, String confermaNuovaPassword) throws UtenteInesistenteException, PasswordNonCoincidentiException {
		if (this.utenteDAO.verifyLogin(loginBean) && matchPassword(nUtenteBean.getPassword(), confermaNuovaPassword)) {
			this.utenteDAO.update(nUtenteBean);
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
