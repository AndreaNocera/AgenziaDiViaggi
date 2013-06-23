package gestioneutenti.controller;

import java.util.GregorianCalendar;
import javax.swing.JDialog;
import javax.swing.JFrame;

import gestioneutenti.exception.LoginErratoException;
import gestioneutenti.exception.LoginInconsistenteException;
import gestioneutenti.exception.RuoloException;
import gestioneutenti.exception.DatiUtenteInconsistentiException;
import gestioneutenti.model.*;
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
		
	public synchronized Utente login(Login login) throws LoginErratoException {
		for (Utente u : utenti) {
			if (login.equals(u.getLogin())) {
				return u;
			}
		}
			
		throw new LoginErratoException();					
	}
	
	public synchronized void home(Utente utente) {
		BoundaryHome boundary = new BoundaryHome(utente);
		boundary.setVisible(true);		
	}
	
	public synchronized boolean reimpostaPassword(String username, String password, String resetCode) {
		return true;
	}

	public synchronized void mostraDialogReimpostaPassword(JFrame owner, String username) {
		DialogReimpostaPassword dialog = new DialogReimpostaPassword(owner, username);
		dialog.setVisible(true);
	}
	
	public synchronized void chiudi(JFrame frame) {
		frame.setVisible(false);
	}
	
	public synchronized void chiudi(JDialog dialog) {
		dialog.setVisible(false);
	}

	private void inizializzaUtentiProva() throws RuoloException, DatiUtenteInconsistentiException, LoginErratoException, LoginInconsistenteException {
		utenti[0] = fu.creaUtente(new DatiUtente("Giacomo", "Marciani", "Roma", "giacomo.marciani@gmail.com", new GregorianCalendar(1990, 06, 27), "Uomo"), Ruolo.AMMINISTRATORE, new Login("amministratore", "password"));
		utenti[1] = fu.creaUtente(new DatiUtente("Giacomo", "Marciani", "Roma", "giacomo.marciani@gmail.com", new GregorianCalendar(1990, 06, 27), "Uomo"), Ruolo.PROMOTORE, new Login("promotore", "password"));
		utenti[2] = fu.creaUtente(new DatiUtente("Giacomo", "Marciani", "Roma", "giacomo.marciani@gmail.com", new GregorianCalendar(1990, 06, 27), "Uomo"), Ruolo.PROGETTISTA, new Login("progettista", "password"));
		utenti[3] = fu.creaUtente(new DatiUtente("Giacomo", "Marciani", "Roma", "giacomo.marciani@gmail.com", new GregorianCalendar(1990, 06, 27), "Uomo"), Ruolo.VENDITORE, new Login("venditore", "password"));
		utenti[4] = fu.creaUtente(new DatiUtente("Giacomo", "Marciani", "Roma", "giacomo.marciani@gmail.com", new GregorianCalendar(1990, 06, 27), "Uomo"), Ruolo.CLIENTE, new Login("cliente", "password"));
	}

}
