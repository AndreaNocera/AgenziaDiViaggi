package gestioneutenti.test;

import java.util.GregorianCalendar;

import gestioneutenti.model.DatiUtente;
import gestioneutenti.model.FactoryUtenti;
import gestioneutenti.model.Login;
import gestioneutenti.model.Utente;
import gestioneutenti.model.ruoli.*;

public class TestGestioneUtentiModel {

	public TestGestioneUtentiModel() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws RuoloNonEsistenteException {
		
		FactoryUtenti fu = FactoryUtenti.getInstance();		
		
		Utente[] utenti =  new Utente[3];
		
		utenti[0] = fu.creaUtente(new DatiUtente("Giacomo", "Marciani", "Roma", new GregorianCalendar(1990, 06, 27), "M"), "giacomo.marciani@gmail.com", Ruolo.AMMINISTRATORE, new Login("giacomo.marciani", "password"));
		utenti[1] = fu.creaUtente(new DatiUtente("Giacomo", "Marciani", "Roma", new GregorianCalendar(1990, 06, 27), "M"), "giacomo.marciani@gmail.com", Ruolo.PROMOTORE, new Login("giacomo.marciani", "password"));
		utenti[2] = fu.creaUtente(new DatiUtente("Giacomo", "Marciani", "Roma", new GregorianCalendar(1990, 06, 27), "M"), "giacomo.marciani@gmail.com", Ruolo.PROGETTISTA, new Login("giacomo.marciani", "password"));

		
		for (Utente u : utenti) {
			System.out.println(u.getDatiUtente().getNome() + ", " + u.getDatiUtente().getCognome() + " (" + u.getMail() + ") : " + u.getRuolo().asString());
		}

	}

}
