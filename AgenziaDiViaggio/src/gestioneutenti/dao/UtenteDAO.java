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
import gestioneutenti.model.bean.LoginBean;
import gestioneutenti.model.bean.UtenteBean;

public interface UtenteDAO {
	
	public boolean save(UtenteBean utenteBean);
	
	public boolean update(UtenteBean utenteBean);
	
	public boolean delete(UtenteBean utenteBean);
	
	public List<UtenteBean> findAll();
	
	public UtenteBean findByLogin(LoginBean login) throws UtenteInesistenteException;
	
	public UtenteBean findByUsername(String username) throws UtenteInesistenteException;

}
