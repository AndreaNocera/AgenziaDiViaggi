package webvoyager.marciani.servlet;

import java.io.IOException;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import webvoyager.marciani.controller.ControllerAmministraUtenti;
import webvoyager.marciani.exception.BeanInconsistenteException;
import webvoyager.marciani.exception.UtenteEsistenteException;
import webvoyager.marciani.model.bean.UtenteBean;
import webvoyager.marciani.model.ruoli.FactoryRuoli;
import webvoyager.marciani.utils.DateUtils;

@WebServlet("/ModificaUtente")
public class ModificaUtente extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ControllerAmministraUtenti controllerAmministraUtenti;
       
    public ModificaUtente() {
        super();
        this.controllerAmministraUtenti = ControllerAmministraUtenti.getInstance();
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
		
		try {
			this.controllerAmministraUtenti.modifica(utenteBean);
		} catch (UtenteEsistenteException exc) {
			response.sendError(HttpServletResponse.SC_CONFLICT);
			return;
		} catch (BeanInconsistenteException exc) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}

}
