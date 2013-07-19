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

package webvoyager.marciani.controller;

import webvoyager.marciani.dao.UtenteDAO;
import webvoyager.marciani.dao.UtenteJdbcDAO;
import webvoyager.marciani.exception.BeanInconsistenteException;
import webvoyager.marciani.exception.UtenteEsistenteException;
import webvoyager.marciani.exception.UtenteInesistenteException;
import webvoyager.marciani.model.FactoryResetCode;
import webvoyager.marciani.model.Login;
import webvoyager.marciani.model.PasswordProvvisoria;
import webvoyager.marciani.model.Utente;
import webvoyager.marciani.model.bean.LoginBean;
import webvoyager.marciani.model.bean.UtenteBean;
import webvoyager.marciani.utils.mailer.Mailer;
import webvoyager.marciani.utils.mailer.WebMailer;

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
