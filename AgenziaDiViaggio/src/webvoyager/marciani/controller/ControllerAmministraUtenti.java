/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.controller
 * 
 * @name ControllerAmministraUtenti.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.marciani.controller;

import java.util.GregorianCalendar;
import java.util.List;


import webvoyager.marciani.dao.UtenteDAO;
import webvoyager.marciani.dao.UtenteJdbcDAO;
import webvoyager.marciani.exception.BeanInconsistenteException;
import webvoyager.marciani.exception.UtenteEsistenteException;
import webvoyager.marciani.exception.UtenteInesistenteException;
import webvoyager.marciani.model.FactoryPassword;
import webvoyager.marciani.model.Utente;
import webvoyager.marciani.model.bean.UtenteBean;
import webvoyager.marciani.utils.DateUtils;
import webvoyager.marciani.utils.mailer.Mailer;
import webvoyager.marciani.utils.mailer.WebMailer;

public class ControllerAmministraUtenti {
	
	private static ControllerAmministraUtenti singletonControllerAmministraUtenti;
	private static UtenteDAO utenteDAO;
	private FactoryPassword factoryPassword;
	private static Mailer mailer;

	private ControllerAmministraUtenti() {}
	
	public static synchronized ControllerAmministraUtenti getInstance() {
		if (singletonControllerAmministraUtenti == null) {
			singletonControllerAmministraUtenti = new ControllerAmministraUtenti();
		}
		
		utenteDAO = UtenteJdbcDAO.getInstance();
		mailer = WebMailer.getInstance();
		
		return singletonControllerAmministraUtenti;
	}
	
	public void nuovo(UtenteBean utenteBean) throws BeanInconsistenteException, UtenteEsistenteException {
		Utente utente = new Utente().fromBean(utenteBean);
		utenteDAO.save(utente);
		inviaDatiUtente(utente);
	}	

	public void modifica(UtenteBean utenteBean) throws BeanInconsistenteException, UtenteEsistenteException {
		Utente utente = new Utente().fromBean(utenteBean);
		utenteDAO.update(utente);	
		inviaDatiUtente(utente);
	}

	public void rimuovi(UtenteBean utenteBean) throws BeanInconsistenteException {
		Utente utente = new Utente().fromBean(utenteBean);
		utenteDAO.delete(utente);
		notificaRimozione(utente);
	}	
	
	public UtenteBean getUtente(String username) throws UtenteInesistenteException {
		UtenteBean utenteBean = utenteDAO.findByUsername(username);
		return utenteBean;
	}
	

	public List<UtenteBean> cerca(String query) {
		if (query == null || query.isEmpty()) {
			return utenteDAO.findAll();
		} else {
			return utenteDAO.find(query);
		}		
	}	

	public List<UtenteBean> getUtenti() {
		return utenteDAO.findAll();
	}

	public String generaPassword() {
		this.factoryPassword = FactoryPassword.getInstance();
		return this.factoryPassword.creaPassword();
	}
	
	private void inviaDatiUtente(Utente utente) {
		String mail = utente.getDatiUtente().getMail();
		GregorianCalendar cal = utente.getDatiUtente().getNascita();
		String data = DateUtils.getStringFromGregorianCalendar(cal) ;
		mailer.inviaMail(mail, "Voyager", "Ciao " + utente.getLogin().getUsername() + "!" +
				"\n\nEcco i tuoi dati di registrazione:\n" + 
				"\tNome: " + utente.getDatiUtente().getNome() + "\n" +
				"\tCognome: " + utente.getDatiUtente().getCognome() + "\n" +
				"\tCitt�: " + utente.getDatiUtente().getCitta() + "\n" +
				"\tNascita: " + data + "\n" +
				"\tSesso: " + utente.getDatiUtente().getSesso() + "\n" +
				"\tMail: " + utente.getDatiUtente().getMail() + "\n" +
				"\tUsername: " + utente.getLogin().getUsername() + "\n" +
				"\tPassword: " + utente.getLogin().getPassword() + "\n" +
				"\n\nSaluti dal team di Voyager.");
	}

	private void notificaRimozione(Utente utente) {
		String mail = utente.getDatiUtente().getMail();
		mailer.inviaMail(mail, "Voyager", "Ciao " + utente.getLogin().getUsername() + "!\n\nQuesta email � per notificarti della tua eliminazione dal sistema Voyager.\n\nSaluti dal team di Voyager.");
	}

}
