/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.dao
 * 
 * @name UtenteDAO.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.marciani.dao;

import java.util.List;

import webvoyager.marciani.exception.UtenteEsistenteException;
import webvoyager.marciani.exception.UtenteInesistenteException;
import webvoyager.marciani.model.Login;
import webvoyager.marciani.model.Utente;
import webvoyager.marciani.model.bean.UtenteBean;

public interface UtenteDAO {
	
	public boolean save(Utente utente) throws UtenteEsistenteException;
	
	public boolean update(Utente utente) throws UtenteEsistenteException;
	
	public boolean delete(Utente utente);
	
	public boolean deleteByKey(String username);
	
	public List<UtenteBean> findAll();
	
	public List<UtenteBean> find(String query);
	
	public UtenteBean findByLogin(Login login) throws UtenteInesistenteException;
	
	public UtenteBean findByUsername(String username) throws UtenteInesistenteException;
	
	public boolean verifyLogin(Login login) throws UtenteInesistenteException;

}
