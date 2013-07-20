package voyager.nove.persistence.dao;

import java.util.List;

import voyager.nove.exception.UtenteInesistenteException;
import voyager.nove.model.utente.Login;
import voyager.nove.model.utente.Utente;
import voyager.nove.model.utente.bean.UtenteBean;

public interface UtenteDAO {
	
	public boolean save(Utente utente);
	
	public boolean update(Utente utente);
	
	public boolean delete(Utente utente);
	
	public boolean deleteByKey(String username);
	
	public List<UtenteBean> findAll();
	
	public UtenteBean findByLogin(Login login) throws UtenteInesistenteException;
	
	public UtenteBean findByUsername(String username) throws UtenteInesistenteException;
	
	public boolean verifyLogin(Login login) throws UtenteInesistenteException;

}
