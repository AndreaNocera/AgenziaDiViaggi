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

package webvoyager.marciani.servlet;

import java.io.IOException;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webvoyager.marciani.controller.ControllerGestisciProfilo;
import webvoyager.marciani.exception.BeanInconsistenteException;
import webvoyager.marciani.exception.UtenteInesistenteException;
import webvoyager.marciani.model.bean.LoginBean;
import webvoyager.marciani.model.bean.UtenteBean;
import webvoyager.marciani.model.ruoli.Ruolo;
import webvoyager.marciani.utils.DateUtils;

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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginBean loginBean = new LoginBean().setUsername(username).setPassword(password);
		
		UtenteBean utenteBean = null;
		Ruolo ruolo = null;
		
		try {
			utenteBean = this.controllerGestisciProfilo.getUtente(loginBean);
		} catch (UtenteInesistenteException | BeanInconsistenteException exc) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		ruolo = utenteBean.getRuolo();	
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String citta = request.getParameter("citta");
		GregorianCalendar nascita = DateUtils.getGregorianCalendarFromString(request.getParameter("nascita"));
		String sesso = request.getParameter("sesso");
		String mail = request.getParameter("mail");		
		String nPassword = request.getParameter("nPassword");
		
		UtenteBean nUtenteBean = new UtenteBean()
		.setNome(nome)
		.setCognome(cognome)
		.setCitta(citta)
		.setNascita(nascita)
		.setSesso(sesso)
		.setMail(mail)
		.setRuolo(ruolo)
		.setUsername(username)
		.setPassword(nPassword);
		
		try {
			this.controllerGestisciProfilo.aggiornaProfilo(nUtenteBean);
			HttpSession session = request.getSession(true);
			session.setAttribute("utente", nUtenteBean);
			return;
		} catch (BeanInconsistenteException exc) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		
	}

}
