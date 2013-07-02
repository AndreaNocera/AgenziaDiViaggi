package gestioneutenti.controller;

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

import javax.swing.JFrame;
import utils.mailer.Mailer;
import gestioneutenti.dao.UtenteDAO;
import gestioneutenti.dao.UtenteJdbcDAO;
import gestioneutenti.exception.UtenteInesistenteException;
import gestioneutenti.model.*;
import gestioneutenti.model.bean.LoginBean;
import gestioneutenti.model.bean.UtenteBean;
import gestioneutenti.view.utils.DialogReimpostaPassword;
import home.view.BoundaryHome;

public class ControllerLogin {
		
	private static ControllerLogin singletonControllerLogin = null;
	private UtenteDAO utenteDAO = null;

	private ControllerLogin() {
		this.utenteDAO = UtenteJdbcDAO.getInstance();
	}

	public static synchronized ControllerLogin getInstance() {
		if (singletonControllerLogin == null) {
			singletonControllerLogin = new ControllerLogin();
			return singletonControllerLogin;
		}
		
		return singletonControllerLogin;
	}
		
	public synchronized UtenteBean login(LoginBean loginBean) throws UtenteInesistenteException {
		UtenteBean utenteBean = this.utenteDAO.findByLogin(loginBean);
		
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
	
	public synchronized void impostaResetCode(String username) throws UtenteInesistenteException {
		UtenteBean utenteBean = this.utenteDAO.findByUsername(username);
		FactoryResetCode factoryResetCode = FactoryResetCode.getInstance();
		ResetCode resetCode = factoryResetCode.creaResetCode();
		this.utenteDAO.update(utenteBean.setPassword(String.valueOf(resetCode.getCodice())));
		String mail = utenteBean.getMail();
		Mailer mailer = Mailer.getInstance();
		mailer.inviaMail(mail, "Voyager ResetCode", "Ciao " + username + "!\n\nLa tua password provvisoria è: " + resetCode.getCodice() + "\n\nSaluti dal team di Voyager.");
	}	

}
