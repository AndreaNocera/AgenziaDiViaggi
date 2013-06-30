package gestioneutenti.dao;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import gestioneutenti.exception.DatiUtenteInconsistentiException;
import gestioneutenti.exception.LoginInconsistenteException;
import gestioneutenti.exception.UtenteEsistenteException;
import gestioneutenti.model.DatiUtente;
import gestioneutenti.model.Login;
import gestioneutenti.model.Utente;
import gestioneutenti.model.ruoli.Amministratore;
import gestioneutenti.model.ruoli.Ruolo;
import gestioneutenti.model.ruoli.Venditore;

public class TestFileSystem {

	/**
	 * Test per Persistence_fileSystem
	 * 	 */
	public static void main(String[] args) {

		Persistence_fileSystem pfs = null;
		pfs = new Persistence_fileSystem("data/file_utenti.dat");

		Login cevalloslogin= null;
		Login abokylogin= null;

		try {
			abokylogin= new Login("Ylias", "12345");
			cevalloslogin= new Login("Cevallos", "12345");

		} catch (LoginInconsistenteException e2) {
			e2.printStackTrace();
		}

		DatiUtente dati_nuovo_utente=null;
		DatiUtente dati_nuovo_utente2=null;

		try {
			dati_nuovo_utente = new DatiUtente("Ernset", "Rohan",
					"Ernst.Rohn@gmail.com","Roma", 
					new GregorianCalendar(1991,3,5), "Uomo");
				dati_nuovo_utente2 = new DatiUtente("Andreas", "Friedrick",
					"xxyytt@gmail.com","Roma", 
				new GregorianCalendar(1991,3,5), "Uomo");
		} catch (DatiUtenteInconsistentiException e2) {
			e2.printStackTrace();
		}


		Ruolo ruolo_nuovo_utente= Amministratore.getInstance();
		Ruolo ruolo_nuovo_utente2= Venditore.getInstance();

		Utente nuovo_utente= new Utente(dati_nuovo_utente, ruolo_nuovo_utente, cevalloslogin);
			Utente nuovo_utente2= new Utente(dati_nuovo_utente2, ruolo_nuovo_utente2, abokylogin);

		ArrayList<Utente> lista=null;
		try {
			pfs.SaveNewUser(nuovo_utente);
		} catch (UtenteEsistenteException e) {
			e.printStackTrace();
		}
		try {
		 pfs.SaveNewUser(nuovo_utente2);
		} catch (UtenteEsistenteException e) {
			e.printStackTrace();
		}
		lista= pfs.getAll();
		stampa_lista(lista);
	}
	private static void stampa_lista(ArrayList<Utente> lista) {
		if(lista!=null){
			System.out.println("Lista non vuota_ lista.size= "+lista.size());

		for(int index=0;index<(lista.size());index++){
			System.out.println(lista.get(index).getDatiUtente().getNome()+" "+lista.get(index).getDatiUtente().getCognome());
		}
		}else
			System.out.println("Lista vuota!");
	}
}
