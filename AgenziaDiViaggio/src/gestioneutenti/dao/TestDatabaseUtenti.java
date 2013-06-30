package gestioneutenti.dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import gestioneutenti.exception.LoginInconsistenteException;
import gestioneutenti.exception.UtenteEsistenteException;
import gestioneutenti.model.DatiUtente;
import gestioneutenti.model.Login;
import gestioneutenti.model.Utente;
import gestioneutenti.dao.Utente_db_DAO;
import gestioneutenti.model.ruoli.Amministratore;
import gestioneutenti.model.ruoli.Ruolo;
import gestioneutenti.dao.Persistence_Db;

/**
 * TestDatabaseUtenti è un test per l'utilizzo delle 
 * classi Utente_db_DAO e db_utenti_helper.
 */
public class TestDatabaseUtenti {

	static Persistence_Db db_utenti_helper=Persistence_Db.getinstance();
	Connection conn;
	Statement statement;

	static ArrayList <Utente_db_DAO> lista_utenti_dao;


	public static void main(String[] args) {

		db_utenti_helper.createDb();
		lista_utenti_dao= new ArrayList<Utente_db_DAO>();
		
		Login marcianilogin= null;
		Login abokylogin= null;
		Login cevalloslogin= null;
		Login aqwers_login=null;
		Login acxcvs_login=null;
		
		try {
			marcianilogin = new Login("Giacomo", "12345");
			abokylogin= new Login("Ylias", "12345");
			cevalloslogin= new Login("Cevallos", "12345");
			aqwers_login= new Login("fb", "12345");
			acxcvs_login= new Login("vsd", "12345");
		} catch (LoginInconsistenteException e2) {
			e2.printStackTrace();
		}

		Utente_db_DAO utente1= new Utente_db_DAO("Giacomo", "Marciani", "Roma", "1990-6-27", "Uomo", "giacomo.marciani@gmail.com", 2, marcianilogin, null);
		Utente_db_DAO utente2= new Utente_db_DAO("Jesus", "Cevallos", "Quito", "1991-9-30", "Uomo", "jesusfcevallos@gmail.com", 3, cevalloslogin, null);
		Utente_db_DAO utente3= new Utente_db_DAO("Ylias", "Aboky", "Benin", "1990-6-27", "Uomo", "ilyas.aboky@gmail.com", 0, abokylogin, null);
		Utente_db_DAO utente4= new Utente_db_DAO("nji", "Aboky", "Benin", "1990-6-27", "Uomo", "asdfasdf@gmail.com", 0, acxcvs_login, null);
		Utente_db_DAO utente5= new Utente_db_DAO("okm", "Aboky", "Benin", "1990-6-27", "Uomo", "", 0, aqwers_login, null);
		
		try{
			utente1.save();
			utente2.save();
			utente3.save();
			utente4.save();
			utente5.save();
		}catch(UtenteEsistenteException uee){
			uee.printStackTrace();
		}

		
		try {
			utente4.setLogin(cevalloslogin);
			utente4.update();
		} catch (UtenteEsistenteException e) {
			e.printStackTrace();
		}

		//Si può usare populate_database per popolare la tabella di utenti
		/*try {
			populate_database();
		} catch (LoginInconsistenteException | UtenteEsistenteException e) {
			e.printStackTrace();
		} */

		//Creazione di un Utente (Classe entità del package model)

		Ruolo ruolo_nuovo_utente= Amministratore.getInstance();
		DatiUtente dati_nuovo_utente = null;
		Utente nuovo_utente= new Utente(dati_nuovo_utente, ruolo_nuovo_utente, cevalloslogin);
		Utente_db_DAO nuovo_dao_utente =new Utente_db_DAO(nuovo_utente);
		
		try {
			nuovo_dao_utente.save();
		} catch (UtenteEsistenteException e1) {
			e1.printStackTrace();
		}

		try {
			lista_utenti_dao=db_utenti_helper.get_all();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}


	private static void populate_database() throws LoginInconsistenteException, UtenteEsistenteException {
		for(int i=0; i<100; i++){
			Login login = new Login("username"+i, "password"+i);

			Utente_db_DAO utente= new Utente_db_DAO("nome"+i, "cognome"+i, "citta"+i, "1991-09-30", "Uomo", "mail"+i+"@hotmail.com", (i%6), login, "12345");
			utente.save();
		}
		for(int i=100; i<200; i++){
			Login login = new Login("username"+i, "password"+i);

			Utente_db_DAO utente= new Utente_db_DAO("nome"+i, "cognome"+i, "citta"+i, "1995-09-30", "Donna", "mail"+i+"@hotmail.com", (i%6), login, "12345");
			utente.save();

		}
		for(int i=200; i<500; i++){
			Login login = new Login("username"+i, "password"+i);
			Utente_db_DAO utente= new Utente_db_DAO("nome"+i, "cognome"+i, "citta"+i, "1985-09-30", "Uomo", "mail"+i+"@hotmail.com", (i%6), login, "12345");
			utente.save();

		}
	}

	public void query_test(){
		ArrayList<Utente_db_DAO> lista1= new ArrayList<Utente_db_DAO>();
		ArrayList<Utente_db_DAO> lista2= new ArrayList<Utente_db_DAO>();
		try {
			lista1=db_utenti_helper.get_all();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		lista2=db_utenti_helper.getUsersbyName(lista1, "nome1");
		stampa_lista(lista2);

		lista2=db_utenti_helper.getUsersbyLastname(lista1, "cognome1");
		stampa_lista(lista2);

		lista2=db_utenti_helper.getUsersbyAgeUp(lista1, new GregorianCalendar(1990, 01, 01));
		stampa_lista(lista2);

		lista2=db_utenti_helper.getUsersbyAgeDown(lista1, new GregorianCalendar(1995, 01, 01));
		stampa_lista(lista2);
	}

	private static void stampa_lista(ArrayList<Utente_db_DAO> lista) {
		for(int index=0;index<(lista.size());index++){
			System.out.println(lista.get(index).getNome()+" "+lista.get(index).getNome()+" "+lista.get(index).getNascita());
			System.out.println("Mail> "+lista.get(index).getMail()+" Credenziali> usr: "+lista.get(index).getLogin().getUsername()+" pass> "+lista.get(index).getLogin().getUsername()+"");
		}

	}	

}

