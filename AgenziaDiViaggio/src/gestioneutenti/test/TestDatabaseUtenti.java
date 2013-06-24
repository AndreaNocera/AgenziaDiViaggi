package gestioneutenti.test;


import java.sql.*;
import java.util.ArrayList;



import gestioneutenti.exception.LoginInconsistenteException;
import gestioneutenti.exception.UtenteEsistenteException;
import gestioneutenti.model.DatiUtente;
import gestioneutenti.model.Login;
import gestioneutenti.model.Utente;

import gestioneutenti.model.Utente_db_DAO;
import gestioneutenti.model.ruoli.Amministratore;
import gestioneutenti.model.ruoli.Ruolo;
import gestioneutenti.view.Db_utenti_OpenHelper;

/**
 * @author Jesus Cevallos
 * TestDatabaseUtenti è un test per l'utilizzo delle 
 * classi Utente_db_DAO e db_utenti_helper.
 */
public class TestDatabaseUtenti {

	static Db_utenti_OpenHelper db_utenti_helper=Db_utenti_OpenHelper.getinstance();
	Connection conn;
	Statement statement;

	static ArrayList <Utente_db_DAO> lista_utenti_dao;

	public static void main(String[] args) {

		db_utenti_helper.createDb();

		lista_utenti_dao= new ArrayList<Utente_db_DAO>();
		//Per il test:
		Login marcianilogin = null;
		Login abokylogin= null;
		Login cevalloslogin = null;
		try {
			marcianilogin = new Login("Giacomo", "12345");
			abokylogin= new Login("Ylias", "12345");
			cevalloslogin= new Login("Cevallos", "12345");
		} catch (LoginInconsistenteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}


		Utente_db_DAO utente1= new Utente_db_DAO("Giacomo", "Marciani", "Roma", "1990-6-27", "Uomo", "giacomo.marciani@gmail.com", 2, marcianilogin);
		Utente_db_DAO utente2= new Utente_db_DAO("Jesus", "Cevallos", "Quito", "1991-9-30", "Uomo", "jesusfcevallos@gmail.com", 3, cevalloslogin);
		Utente_db_DAO utente3= new Utente_db_DAO("Ylias", "Aboky", "Benin", "1990-6-27", "Uomo", "ilyas.aboky@gmail.com", 0, abokylogin);

		try{
			utente1.save();
			utente2.save();
			utente3.save();
		}catch(UtenteEsistenteException uee){
			uee.printStackTrace();
		}

		try {
			lista_utenti_dao=db_utenti_helper.get_all();
		} catch (SQLException e) {
			// TODO Auto-oggettigenerated catch block
			e.printStackTrace();
		}


		for(int index=0;index<(lista_utenti_dao.size());index++){
			System.out.println(lista_utenti_dao.get(index).getNome()+"");
			System.out.println(lista_utenti_dao.get(index).getMail()+"");
		}



		DatiUtente dati_nuovo_utente= new DatiUtente("Ludovi", "William","william@gmail.com","Roma");
		Ruolo ruolo_nuovo_utente= Amministratore.getInstance();

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		for(int index=0;index<(lista_utenti_dao.size());index++){
			System.out.println(lista_utenti_dao.get(index).getNome()+"");
			System.out.println(lista_utenti_dao.get(index).getMail()+"");
		}

		for(int index=0;index<(lista_utenti_dao.size());index++){
		}

		Login login_nuevo_utente = null;
		try {
			login_nuevo_utente = new Login("Will.Ludovic", "123456789");
		} catch (LoginInconsistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		nuovo_dao_utente.setLogin(login_nuevo_utente);
		nuovo_dao_utente.update();

	}	

}

