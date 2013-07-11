/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.controller
 * 
 * @name ControllerLogin.java
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
import webvoyager.model.FactoryResetCode;
import webvoyager.model.Login;
import webvoyager.model.PasswordProvvisoria;
import webvoyager.model.Utente;
import webvoyager.model.bean.LoginBean;
import webvoyager.model.bean.UtenteBean;
import webvoyager.utils.mailer.Mailer;
import webvoyager.utils.mailer.WebMailer;

public class ControllerLogin {
		
	private static ControllerLogin singletonControllerLogin = null;
	private static UtenteDAO utenteDAO = null;
	private static Mailer mailer = null;

	private ControllerLogin() {}
	
	public static synchronized ControllerLogin getInstance() {
		if (singletonControllerLogin == null) {
			singletonControllerLogin = new ControllerLogin();
		}
		
		utenteDAO = UtenteJdbcDAO.getInstance();
		mailer = WebMailer.getInstance();
		
		return singletonControllerLogin;
	}
		
	public synchronized UtenteBean login(LoginBean loginBean) throws BeanInconsistenteException, UtenteInesistenteException {
		Login login = new Login().fromBean(loginBean);
		UtenteBean utenteBean = utenteDAO.findByLogin(login);
		
		return utenteBean;
	}
	
	public synchronized void impostaResetCode(String username) throws UtenteInesistenteException, BeanInconsistenteException, UtenteEsistenteException {		
		FactoryResetCode factoryResetCode = FactoryResetCode.getInstance();
		PasswordProvvisoria resetCode = factoryResetCode.creaResetCode();
		UtenteBean utenteBean = utenteDAO.findByUsername(username).setPassword(String.valueOf(resetCode.getCodice()));
		Utente utente = new Utente().fromBean(utenteBean);
		String mail = utente.getDatiUtente().getMail();
		utenteDAO.update(utente);		
		mailer.inviaMail(mail, "Voyager ResetPassword", "Ciao " + username + "!\n\nLa tua password provvisoria è: " + resetCode.getCodice() + "\n\nSaluti dal team di Voyager.");
	}	

}
