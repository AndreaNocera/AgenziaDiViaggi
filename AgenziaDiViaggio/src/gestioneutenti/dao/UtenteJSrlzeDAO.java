/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.dao
 * 
 * @name UtenteSrlzeDAO.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */
package gestioneutenti.dao;


import gestioneutenti.exception.UtenteEsistenteException;
import gestioneutenti.exception.UtenteInesistenteException;
import gestioneutenti.model.Login;
import gestioneutenti.model.Utente;
import gestioneutenti.model.bean.UtenteBean;
import gestioneutenti.srlize.SerializationManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UtenteJSrlzeDAO implements UtenteDAO {

	private static UtenteJSrlzeDAO singletonUtenteDAO = null; 
	private static SerializationManager serializationManager = null;

	private  UtenteJSrlzeDAO() {}

	public static synchronized UtenteDAO getInstance() throws IOException {
		if(singletonUtenteDAO == null) 
			singletonUtenteDAO = new UtenteJSrlzeDAO();

		serializationManager = SerializationManager.getInstance();	
		return singletonUtenteDAO;
	}


	@Override
	public synchronized boolean save(Utente utente){	
		
		List<UtenteBean> lista= null;
		boolean salvataggioEffettuato = false;
		UtenteBean ub = null;
		
		try {ub = findByUsername( utente.getLogin().getUsername());} catch (UtenteInesistenteException e) {e.printStackTrace();}
		
		if(ub==null){
			ub= toUtente(utente);
			
			try {
				lista=findAll();
				lista.add(ub);
				salvataggioEffettuato=serializationManager.serializza_utenti((ArrayList<UtenteBean>) lista);
			}catch (IOException e) {e.printStackTrace();}
			
		} else
			try {
				throw new UtenteEsistenteException("L'username '"+utente.getLogin().getUsername()+"' è gia stato usato");
			} catch (UtenteEsistenteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return salvataggioEffettuato;
	}

	@Override
	public synchronized boolean update(Utente utente){
		
		ArrayList<UtenteBean> lista= null;
		boolean aggionamentoEffettuato=false;

		try {
			if(findByUsername(utente.getLogin().getUsername())!=null){ 
				
				delete(utente);
				UtenteBean ub = toUtente(utente);
				try {
					lista=(ArrayList<UtenteBean>) findAll();
					lista.add(ub);
					aggionamentoEffettuato=serializationManager.serializza_utenti(lista);
				} catch (IOException e) {e.printStackTrace();}
				
			}
			else throw new UtenteInesistenteException();
		} catch (UtenteInesistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aggionamentoEffettuato;
	}

	@Override
	public synchronized boolean delete(Utente utente) {

		List<UtenteBean> lista= null;
		List<UtenteBean> nuova_lista = null;
		
		UtenteBean ub = toUtente(utente);
		
		boolean cancellazioneEffettuata=false;
		
		try {ub=findByUsername( utente.getLogin().getUsername() );} catch (UtenteInesistenteException e) {e.printStackTrace();}

		if(ub!=null){
			
			nuova_lista= new ArrayList<>();
			lista=findAll();
			
			for(int i=0;i<lista.size();i++)
				if(!(utente.getLogin().getUsername()).equals(lista.get(i).getUsername())) nuova_lista.add(lista.get(i));
							
		}
		try {
			if(serializationManager.svuotaFile()&&serializationManager.serializza_utenti((ArrayList<UtenteBean>)nuova_lista))cancellazioneEffettuata=true;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cancellazioneEffettuata;
	}

	@Override
	public synchronized List<UtenteBean> findAll() {
		
		ArrayList<UtenteBean> lista= null;
		
		try {lista= serializationManager.deserealizza_utenti();} catch (IOException e) {e.printStackTrace();}
		
		return lista;
	}

	@Override
	public synchronized UtenteBean findByUsername(String username) throws UtenteInesistenteException {

		List<UtenteBean> lista = findAll();
		UtenteBean result=null;

		for(int i=0;i<lista.size();i++)
			if((lista.get(i).getUsername()).equals(username)){result=lista.get(i);break;}
		
		return result;
	}

	@Override
	public synchronized UtenteBean findByLogin(Login login) throws UtenteInesistenteException {
		
		List<UtenteBean> lista = findAll();
		UtenteBean result=null;

		for(int i=0;i<lista.size();i++){
			if((lista.get(i).getUsername()).equals(login.getUsername())&& (lista.get(i).getPassword()).equals(login.getPassword())){
				result=lista.get(i);
				break;
			}
		}

		return result;
	}

	@Override
	public boolean verifyLogin(Login login)	throws UtenteInesistenteException {
		return (findByLogin(login)!=null);
	}

	public UtenteBean toUtente(Utente utente){
		UtenteBean ub = new UtenteBean();
		 
		ub.setUsername(utente.getLogin().getUsername());
		ub.setPassword(utente.getLogin().getPassword());
		ub.setNome(utente.getDatiUtente().getNome());
		ub.setCognome(utente.getDatiUtente().getCognome()); 
		ub.setCitta(utente.getDatiUtente().getCitta()); 
		ub.setMail(utente.getDatiUtente().getMail());
		ub.setSesso(utente.getDatiUtente().getSesso());
		ub.setNascita(utente.getDatiUtente().getNascita());
		ub.setRuolo(utente.getRuolo()); 
		return ub;
	}

	@Override
	public boolean deleteByKey(String username) {
		// TODO Auto-generated method stub
		return false;
	}
}