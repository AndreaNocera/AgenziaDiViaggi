/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.controller
 * 
 * @name ControllerGestioneUtenti.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package voyager.nove.control;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import voyager.nove.exception.DatiUtenteInconsistentiException;
import voyager.nove.exception.LoginInconsistenteException;
import voyager.nove.exception.UtenteInesistenteException;
import voyager.nove.model.utente.FactoryPassword;
import voyager.nove.model.utente.Utente;
import voyager.nove.model.utente.bean.UtenteBean;
import voyager.nove.persistence.dao.UtenteDAO;
import voyager.nove.persistence.dao.UtenteJDBCDAO;
import voyager.nove.utils.mailer.Mailer;
import voyager.nove.utils.mailer.StandaloneMailer;

public class ControllerGestioneUtenti {
	
	private static ControllerGestioneUtenti singletonControllerAmministraUtenti;
	private static UtenteDAO utenteDAO;
	private FactoryPassword factoryPassword;
	private static Mailer mailer;

	private ControllerGestioneUtenti() {}

	public static synchronized ControllerGestioneUtenti getInstance() {
		if (singletonControllerAmministraUtenti == null) {
			singletonControllerAmministraUtenti = new ControllerGestioneUtenti();
		}
		
		utenteDAO = UtenteJDBCDAO.getInstance();
		mailer = StandaloneMailer.getInstance();
		
		return singletonControllerAmministraUtenti;
	}
	
	public void nuovo(UtenteBean utenteBean) throws DatiUtenteInconsistentiException, LoginInconsistenteException {
		Utente utente = new Utente().fromBean(utenteBean);
		utenteDAO.save(utente);
		inviaDatiUtente(utente);
	}	

	public void modifica(UtenteBean utenteBean) throws DatiUtenteInconsistentiException, LoginInconsistenteException {
		Utente utente = new Utente().fromBean(utenteBean);
		utenteDAO.update(utente);	
		inviaDatiUtente(utente);
	}

	public void rimuovi(UtenteBean utenteBean) throws DatiUtenteInconsistentiException, LoginInconsistenteException {
		Utente utente = new Utente().fromBean(utenteBean);
		utenteDAO.delete(utente);
		notificaRimozione(utente);
	}	
	
	public UtenteBean getUtente(String username) throws UtenteInesistenteException {
		UtenteBean utenteBean = utenteDAO.findByUsername(username);
		return utenteBean;
	}
	

	public void cerca(String query) {
		//
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
		String data = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH); 
		mailer.inviaMail(mail, "Voyager", "Ciao " + utente.getLogin().getUsername() + "!" +
				"\n\nEcco i tuoi dati di registrazione:\n" + 
				"\tNome: " + utente.getDatiUtente().getNome() + "\n" +
				"\tCognome: " + utente.getDatiUtente().getCognome() + "\n" +
				"\tCittà: " + utente.getDatiUtente().getCitta() + "\n" +
				"\tNascita: " + data + "\n" +
				"\tSesso: " + utente.getDatiUtente().getSesso() + "\n" +
				"\tMail: " + utente.getDatiUtente().getMail() + "\n" +
				"\tUsername: " + utente.getLogin().getUsername() + "\n" +
				"\tPassword: " + utente.getLogin().getPassword() + "\n" +
				"\n\nSaluti dal team di Voyager.");
	}

	private void notificaRimozione(Utente utente) {
		String mail = utente.getDatiUtente().getMail();
		mailer.inviaMail(mail, "Voyager", "Ciao " + utente.getLogin().getUsername() + "!\n\nQuesta email è per notificarti della tua eliminazione dal sistema Voyager.\n\nSaluti dal team di Voyager.");
	}

}
