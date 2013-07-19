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

package voyager.nove.persistence.dao;

import java.util.List;

import voyager.nove.exception.UtenteInesistenteException;
import voyager.nove.model.Login;
import voyager.nove.model.Utente;
import voyager.nove.model.bean.UtenteBean;

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
