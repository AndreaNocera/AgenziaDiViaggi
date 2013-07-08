/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.dao
 * 
 * @name UtenteDAO.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package gestioneutenti.dao;

import java.util.List;

import gestioneutenti.exception.UtenteInesistenteException;
import gestioneutenti.model.Login;
import gestioneutenti.model.Utente;
import gestioneutenti.model.bean.UtenteBean;

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
