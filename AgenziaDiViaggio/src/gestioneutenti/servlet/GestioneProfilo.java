/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.servlet
 * 
 * @name GestioneProfilo.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package gestioneutenti.servlet;

import gestioneutenti.controller.ControllerGestisciProfilo;
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
import javax.servlet.http.HttpSession;

import utils.DateUtils;

@WebServlet("/GestioneProfilo")
public class GestioneProfilo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ControllerGestisciProfilo controllerGestisciProfilo;

    public GestioneProfilo() {
        super();
        this.controllerGestisciProfilo = ControllerGestisciProfilo.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String citta = request.getParameter("citta");
		GregorianCalendar nascita = DateUtils.getGregorianCalendarFromString(request.getParameter("nascita"));
		String sesso = request.getParameter("sesso");
		String mail = request.getParameter("mail");
		int ruolo = (int) Integer.parseInt(request.getParameter("ruolo"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.err.println("servlet GESTIONE PROFILO: " + nome + cognome + citta + nascita + sesso + mail + ruolo + username + password);
		
		UtenteBean nUtenteBean = new UtenteBean()
		.setNome(nome)
		.setCognome(cognome)
		.setCitta(citta)
		.setNascita(nascita)
		.setSesso(sesso)
		.setMail(mail)
		.setRuolo(FactoryRuoli.getInstance().assegnaRuolo(ruolo))
		.setUsername(username)
		.setPassword(password);		
		
		try {
			this.controllerGestisciProfilo.aggiornaProfilo(nUtenteBean);
			HttpSession session = request.getSession(true);
			session.setAttribute("utente", nUtenteBean);
			return;
		} catch (LoginInconsistenteException | DatiUtenteInconsistentiException e) {
			System.err.println("servlet GESTIONE PROFILO: ERRORE!!!");
			response.sendRedirect("FallimentoGestioneProfilo.jsp");
			return;
		}
	}

}
