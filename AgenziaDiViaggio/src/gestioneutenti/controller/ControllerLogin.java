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

import java.util.GregorianCalendar;
import javax.swing.JFrame;

import utils.mailer.Mailer;

import gestioneutenti.exception.LoginErratoException;
import gestioneutenti.exception.LoginInconsistenteException;
import gestioneutenti.exception.RuoloException;
import gestioneutenti.exception.DatiUtenteInconsistentiException;
import gestioneutenti.model.*;
import gestioneutenti.model.bean.LoginBean;
import gestioneutenti.model.bean.UtenteBean;
import gestioneutenti.model.ruoli.*;
import gestioneutenti.view.utils.DialogReimpostaPassword;
import home.view.BoundaryHome;

public class ControllerLogin {
	
	FactoryUtenti fu = FactoryUtenti.getInstance();
	
	Utente[] utenti = new Utente[5];	
	
	private static ControllerLogin singletonControllerLogin = null;

	private ControllerLogin() {
		try {
			inizializzaUtentiProva();
		} catch (RuoloException | DatiUtenteInconsistentiException | LoginErratoException | LoginInconsistenteException exc) {
			exc.printStackTrace();
		}
	}

	public static synchronized ControllerLogin getInstance() {
		if (singletonControllerLogin == null) {
			singletonControllerLogin = new ControllerLogin();
			return singletonControllerLogin;
		}
		
		return singletonControllerLogin;
	}
		
	public synchronized UtenteBean login(LoginBean loginBean) throws LoginErratoException {
		for (Utente utente : utenti) {
			if (loginBean.getUsername().equals(utente.getLogin().getUsername()) &&
					loginBean.getPassword().equals(utente.getLogin().getPassword())) {
				return new UtenteBean().setUsername(utente.getLogin().getUsername()).setRuolo(utente.getRuolo()).setNome(utente.getDatiUtente().getNome());
			}
		}	
		
		throw new LoginErratoException();
	}
	
	public synchronized void home(UtenteBean utenteBean) {
		BoundaryHome home = new BoundaryHome(utenteBean);
		home.setVisible(true);		
	}

	public synchronized void mostraDialogReimpostaPassword(JFrame owner) {
		DialogReimpostaPassword dialog = new DialogReimpostaPassword(owner);
		dialog.setVisible(true);
	}
	
	public synchronized void inviaResetCode(String username) {
		FactoryResetCode factoryResetCode = FactoryResetCode.getInstance();
		ResetCode resetCode = factoryResetCode.creaResetCode();
		//registraResetCode(username, resetCode);
		String mail = "giacomo.marciani@gmail.com";
		//mail = retrieveMail(username);
		Mailer mailer = Mailer.getInstance();
		mailer.inviaMail(mail, "Voyager ResetCode", "Ciao " + username + "!\n\nLa tua password provvisoria è: " + resetCode.getCodice() + "\n\nTi ricordiamo che l'accesso provvisorio ha una validità di un giorno.\n\nSaluti dal team di Voyager.");
	}

	private void inizializzaUtentiProva() throws RuoloException, DatiUtenteInconsistentiException, LoginErratoException, LoginInconsistenteException {
		utenti[0] = fu.creaUtente(new DatiUtente("Giacomo", "Marciani", "Roma", "giacomo.marciani@gmail.com", new GregorianCalendar(1990, 06, 27), "Uomo"), Ruolo.AMMINISTRATORE, new Login("amministratore", "password"));
		utenti[1] = fu.creaUtente(new DatiUtente("Giacomo", "Marciani", "Roma", "giacomo.marciani@gmail.com", new GregorianCalendar(1990, 06, 27), "Uomo"), Ruolo.PROMOTORE, new Login("promotore", "password"));
		utenti[2] = fu.creaUtente(new DatiUtente("Giacomo", "Marciani", "Roma", "giacomo.marciani@gmail.com", new GregorianCalendar(1990, 06, 27), "Uomo"), Ruolo.PROGETTISTA, new Login("progettista", "password"));
		utenti[3] = fu.creaUtente(new DatiUtente("Giacomo", "Marciani", "Roma", "giacomo.marciani@gmail.com", new GregorianCalendar(1990, 06, 27), "Uomo"), Ruolo.VENDITORE, new Login("venditore", "password"));
		utenti[4] = fu.creaUtente(new DatiUtente("Giacomo", "Marciani", "Roma", "giacomo.marciani@gmail.com", new GregorianCalendar(1990, 06, 27), "Uomo"), Ruolo.CLIENTE, new Login("cliente", "password"));
	}	

}
