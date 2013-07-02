package gestioneutenti.controller;

/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.controller
 * 
 * @name ControllerAmministraUtenti.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import utils.mailer.Mailer;

import gestioneutenti.dao.UtenteDAO;
import gestioneutenti.dao.UtenteJdbcDAO;
import gestioneutenti.model.FactoryPassword;
import gestioneutenti.model.bean.UtenteBean;

public class ControllerAmministraUtenti {
	
	private static ControllerAmministraUtenti singletonControllerAmministraUtenti = null;
	private UtenteDAO utenteDAO;
	private FactoryPassword factoryPassword;

	private ControllerAmministraUtenti() {
		this.utenteDAO = UtenteJdbcDAO.getInstance();
		this.factoryPassword = FactoryPassword.getInstance();
	}

	public static synchronized ControllerAmministraUtenti getInstance() {
		if (singletonControllerAmministraUtenti == null) {
			singletonControllerAmministraUtenti = new ControllerAmministraUtenti();
			return singletonControllerAmministraUtenti;
		}
		
		return singletonControllerAmministraUtenti;
	}
	
	public void nuovo(UtenteBean utenteBean) {
		this.utenteDAO.save(utenteBean);
		inviaDatiUtente(utenteBean);
	}
	
	

	public void modifica(UtenteBean utenteBean) {
		this.utenteDAO.update(utenteBean);	
		inviaDatiUtente(utenteBean);
	}

	public void rimuovi(UtenteBean utenteBean) {
		this.utenteDAO.delete(utenteBean);
		notificaRimozione(utenteBean);
	}	
	
	private void inviaDatiUtente(UtenteBean utenteBean) {
		String mail = utenteBean.getMail();
		GregorianCalendar cal = utenteBean.getNascita();
		String data = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH); 
		Mailer mailer = Mailer.getInstance();
		mailer.inviaMail(mail, "Voyager", "Ciao " + utenteBean.getUsername() + "!" +
				"\n\nEcco i tuoi dati di registrazione:\n" + 
				"\tNome: " + utenteBean.getNome() + "\n" +
				"\tCognome: " + utenteBean.getCognome() + "\n" +
				"\tCittà: " + utenteBean.getCitta() + "\n" +
				"\tNascita: " + data + "\n" +
				"\tSesso: " + utenteBean.getSesso() + "\n" +
				"\tMail: " + utenteBean.getMail() + "\n" +
				"\tUsername: " + utenteBean.getUsername() + "\n" +
				"\tPassword: " + utenteBean.getPassword() + "\n" +
				"\n\nSaluti dal team di Voyager.");
	}

	private void notificaRimozione(UtenteBean utenteBean) {
		String mail = utenteBean.getMail();
		Mailer mailer = Mailer.getInstance();
		mailer.inviaMail(mail, "Voyager", "Ciao " + utenteBean.getUsername() + "!\n\nQuesta email è per notificarti della tua eliminazione dal sistema Voyager.\n\nSaluti dal team di Voyager.");
	}

	public void cerca(String query) {
		//
	}	

	public List<UtenteBean> getUtenti() {
		return this.utenteDAO.findAll();
	}

	public String generaPassword() {
		return this.factoryPassword.creaPassword();
	}

}
