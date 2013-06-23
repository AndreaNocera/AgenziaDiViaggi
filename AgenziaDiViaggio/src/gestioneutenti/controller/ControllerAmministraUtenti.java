package gestioneutenti.controller;

import gestioneutenti.exception.LoginException;
import gestioneutenti.exception.RuoloException;
import gestioneutenti.exception.UtenteException;
import gestioneutenti.model.DatiUtente;
import gestioneutenti.model.FactoryUtenti;
import gestioneutenti.model.Login;
import gestioneutenti.model.Utente;
import gestioneutenti.model.ruoli.Ruolo;

import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

public class ControllerAmministraUtenti {
	
	FactoryUtenti fu = FactoryUtenti.getInstance();
	
	Utente[] utenti = new Utente[5];
	
	private static ControllerAmministraUtenti singletonControllerAmministraUtenti = null;

	private ControllerAmministraUtenti() {
		try {
			inizializzaUtentiProva();
		} catch (RuoloException | UtenteException | LoginException exc) {
			exc.printStackTrace();
		}
	}

	public static synchronized ControllerAmministraUtenti getInstance() {
		if (singletonControllerAmministraUtenti == null) {
			singletonControllerAmministraUtenti = new ControllerAmministraUtenti();
			return singletonControllerAmministraUtenti;
		}
		
		return singletonControllerAmministraUtenti;
	}	
	
	private void inizializzaUtentiProva() throws RuoloException, UtenteException, LoginException {
		utenti[0] = fu.creaUtente(new DatiUtente("Giacomo", "Marciani", "Roma", new GregorianCalendar(1990, 06, 27), "Uomo"), "giacomo.marciani@gmail.com", Ruolo.AMMINISTRATORE, new Login("amministratore", "password"));
		utenti[1] = fu.creaUtente(new DatiUtente("Giacomo", "Marciani", "Roma", new GregorianCalendar(1990, 06, 27), "Uomo"), "giacomo.marciani@gmail.com", Ruolo.PROMOTORE, new Login("promotore", "password"));
		utenti[2] = fu.creaUtente(new DatiUtente("Giacomo", "Marciani", "Roma", new GregorianCalendar(1990, 06, 27), "Uomo"), "giacomo.marciani@gmail.com", Ruolo.PROGETTISTA, new Login("progettista", "password"));
		utenti[3] = fu.creaUtente(new DatiUtente("Giacomo", "Marciani", "Roma", new GregorianCalendar(1990, 06, 27), "Uomo"), "giacomo.marciani@gmail.com", Ruolo.VENDITORE, new Login("venditore", "password"));
		utenti[4] = fu.creaUtente(new DatiUtente("Giacomo", "Marciani", "Roma", new GregorianCalendar(1990, 06, 27), "Uomo"), "giacomo.marciani@gmail.com", Ruolo.CLIENTE, new Login("cliente", "password"));
	}

	public void rimuoviUtente(String username) {
		JOptionPane.showMessageDialog(null, "Oops! Not implemented function!", "Info", JOptionPane.INFORMATION_MESSAGE);
		
	}

	public void modificaUtente(Utente utente) {
		JOptionPane.showMessageDialog(null, "Oops! Not implemented function!", "Info", JOptionPane.INFORMATION_MESSAGE);
		
	}

	public void creaUtente(Utente utente) {
		JOptionPane.showMessageDialog(null, "Oops! Not implemented function!", "Info", JOptionPane.INFORMATION_MESSAGE);
		
	}

	public void cercaUtente(String query) {
		JOptionPane.showMessageDialog(null, "Oops! Not implemented function!", "Info", JOptionPane.INFORMATION_MESSAGE);
		
	}	

	public Utente[] getUtenti() {
		return utenti;
	}

}
