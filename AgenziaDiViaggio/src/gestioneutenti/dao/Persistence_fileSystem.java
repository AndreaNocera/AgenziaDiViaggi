package gestioneutenti.dao;

import gestioneutenti.exception.UtenteEsistenteException;
import gestioneutenti.exception.UtenteNonEsistenteException;
import gestioneutenti.model.Utente;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 * Persistence_fileSystem è il gestore di 
 * serializzazione/deserializzazione dal file contenente gli utenti.
 */

public  class Persistence_fileSystem{
	File file = null; 
	private String percorso;

	private ObjectInputStream OIS=null;
	private ObjectOutputStream OOS=null;

	private FileInputStream FIS=null;
	private FileOutputStream FOS=null;



	public Persistence_fileSystem(String percorso){
		this.percorso = percorso;
		file =new File(percorso);

	}

	public void SaveNewUser(Utente utente) throws UtenteEsistenteException{
		if(!isthereAnyUser(utente)){	
			try {
				FOS = new FileOutputStream(percorso);
				OOS = new ObjectOutputStream(FOS);
				OOS.writeObject(utente);

				OOS.flush();
				OOS.close();

			}catch ( IOException e) {
				e.printStackTrace();
			}
		}else throw new UtenteEsistenteException("L'username '"+utente.getLogin().getUsername()+"' è gia stato usato");
	}

	
	public void updateUser(Utente utente) throws UtenteNonEsistenteException {
		Utente vecchio_utente;
		try{
			FOS = new FileOutputStream(percorso);
			OOS = new ObjectOutputStream(FOS);

			FIS= new FileInputStream(percorso);
			OIS= new ObjectInputStream(FIS);


			if(isthereAnyUser(utente)){
				do{
					vecchio_utente= (Utente)OIS.readObject();
					if(( vecchio_utente.getLogin().getUsername()).equals( utente.getLogin().getUsername() ) ){
						OOS.writeObject(utente);
						break;
					}
				}while(vecchio_utente!=null);
			}else throw new UtenteNonEsistenteException("Utente non ancora esistence sul file");

		}catch ( IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	public boolean isthereAnyUser( Utente utente_verifica) {
		Utente utente1 = null   ;
		ArrayList<Utente> ListaUtente=null;

		try{
			FIS= new FileInputStream(percorso);
			OIS= new ObjectInputStream(FIS);

			FOS = new FileOutputStream(percorso);
			OOS = new ObjectOutputStream(FOS);

			do{
				try{
					utente1= (Utente)OIS.readObject();}catch(EOFException eofe){
					}
				if(utente1!=null){
					ListaUtente= new ArrayList<>();
					ListaUtente.add(utente1);

					if( ( utente_verifica.getLogin()).equals( utente1.getLogin() ) ){

						for(int index=0;index<(ListaUtente.size());index++){
							try {
								OOS.writeObject(ListaUtente.get(index));
							} catch (IOException e) {
								e.printStackTrace();
							}
						}

						return true;}	
				}
			}while(utente1!=null);


		}catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return false;
	}


	public ArrayList<Utente> getAll(){
		ArrayList<Utente> ListaUtente=null;
		Utente utente;

		try {
			FIS = new FileInputStream(percorso);
			OIS= new ObjectInputStream(FIS);
			FOS= new FileOutputStream(percorso);
			OOS= new ObjectOutputStream(FOS);
			ListaUtente = new ArrayList<Utente>();

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		try {

			do{
				try{
					utente= (Utente)OIS.readObject();}catch(EOFException eofe){
						break;
					}finally{
						try {
							OIS.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				ListaUtente.add(utente);
			}while(true);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		for(int index=0;index<(ListaUtente.size());index++){
			try {
				OOS.writeObject(ListaUtente.get(index));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ListaUtente;
	}

}




