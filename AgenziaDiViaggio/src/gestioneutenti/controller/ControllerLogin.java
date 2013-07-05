/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.controller
 * 
 * @name ControllerLogin.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package gestioneutenti.controller;

import javax.swing.JFrame;

import utils.mailer.Mailer;
import utils.mailer.StandaloneMailer;
import utils.mailer.WebMailer;
import gestioneutenti.dao.UtenteDAO;
import gestioneutenti.dao.UtenteJdbcDAO;
import gestioneutenti.exception.DatiUtenteInconsistentiException;
import gestioneutenti.exception.LoginInconsistenteException;
import gestioneutenti.exception.UtenteInesistenteException;
import gestioneutenti.model.*;
import gestioneutenti.model.bean.LoginBean;
import gestioneutenti.model.bean.UtenteBean;
import gestioneutenti.view.utils.DialogReimpostaPassword;
import home.view.BoundaryHome;

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
		mailer = StandaloneMailer.getInstance();
		
		return singletonControllerLogin;
	}
	
	public static synchronized ControllerLogin getWebInstance() {
		if (singletonControllerLogin == null) {
			singletonControllerLogin = new ControllerLogin();
		}
		
		utenteDAO = UtenteJdbcDAO.getWebInstance();
		mailer = WebMailer.getInstance();
		
		return singletonControllerLogin;
	}
		
	public synchronized UtenteBean login(LoginBean loginBean) throws UtenteInesistenteException, LoginInconsistenteException {
		Login login = new Login().fromBean(loginBean);
		UtenteBean utenteBean = utenteDAO.findByLogin(login);
		
		return utenteBean;
	}
	
	public synchronized void home(UtenteBean utenteBean) {
		BoundaryHome home = new BoundaryHome(utenteBean);
		home.setVisible(true);		
	}

	public synchronized void mostraDialogReimpostaPassword(JFrame owner) {
		DialogReimpostaPassword dialog = new DialogReimpostaPassword(owner);
		dialog.setVisible(true);
	}
	
	public synchronized void impostaResetCode(String username) throws UtenteInesistenteException, DatiUtenteInconsistentiException, LoginInconsistenteException {		
		FactoryResetCode factoryResetCode = FactoryResetCode.getInstance();
		ResetCode resetCode = factoryResetCode.creaResetCode();
		UtenteBean utenteBean = utenteDAO.findByUsername(username).setPassword(String.valueOf(resetCode.getCodice()));
		Utente utente = new Utente().fromBean(utenteBean);
		String mail = utente.getDatiUtente().getMail();
		utenteDAO.update(utente);		
		mailer.inviaMail(mail, "Voyager ResetPassword", "Ciao " + username + "!\n\nLa tua password provvisoria è: " + resetCode.getCodice() + "\n\nSaluti dal team di Voyager.");
	}	

}
