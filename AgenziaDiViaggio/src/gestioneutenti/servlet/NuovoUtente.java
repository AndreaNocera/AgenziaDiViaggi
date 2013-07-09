package gestioneutenti.servlet;

import gestioneutenti.controller.ControllerAmministraUtenti;
import gestioneutenti.exception.DatiUtenteInconsistentiException;
import gestioneutenti.exception.LoginInconsistenteException;
import gestioneutenti.model.bean.UtenteBean;
import gestioneutenti.model.ruoli.FactoryRuoli;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DateUtils;

@WebServlet("/NuovoUtente")
public class NuovoUtente extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ControllerAmministraUtenti controllerAmministraUtenti;
       
    public NuovoUtente() {
        super();
        this.controllerAmministraUtenti = ControllerAmministraUtenti.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("servlet NUOVO UTENTE");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String citta = request.getParameter("citta");
		GregorianCalendar nascita = DateUtils.getGregorianCalendarFromString(request.getParameter("nascita"));
		String sesso = request.getParameter("sesso");
		String mail = request.getParameter("mail");
		int ruolo = (int) Integer.parseInt(request.getParameter("ruolo"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.err.println("servlet NUOVO UTENTE" + nome);
		
		UtenteBean utenteBean = new UtenteBean()
		.setNome(nome)
		.setCognome(cognome)
		.setCitta(citta)
		.setNascita(nascita)
		.setSesso(sesso)
		.setMail(mail)
		.setRuolo(FactoryRuoli.getInstance().assegnaRuolo(ruolo))
		.setUsername(username)
		.setPassword(password);
		
		System.err.println("servlet NUOVO UTENTE BEAN" + utenteBean.getNome());
		
		try {
			this.controllerAmministraUtenti.nuovo(utenteBean);
		} catch (DatiUtenteInconsistentiException | LoginInconsistenteException e) {
			System.err.println("servlet NUOVO UTENTE: ERRORE!!!");
			e.printStackTrace();
		}
	}

}
