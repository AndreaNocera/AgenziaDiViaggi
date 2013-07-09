package gestioneutenti.servlet;

import gestioneutenti.controller.ControllerAmministraUtenti;
import gestioneutenti.exception.DatiUtenteInconsistentiException;
import gestioneutenti.exception.LoginInconsistenteException;
import gestioneutenti.exception.UtenteInesistenteException;
import gestioneutenti.model.bean.UtenteBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RimuoviUtente")
public class RimuoviUtente extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ControllerAmministraUtenti controllerAmministraUtenti;
       
    public RimuoviUtente() {
        super();
        this.controllerAmministraUtenti = ControllerAmministraUtenti.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("servletAmministra: action RIMUOVI UTENTE");
		try {
			String username = request.getParameter("username");
			UtenteBean utenteBean = this.controllerAmministraUtenti.getUtente(username);
			try {
				this.controllerAmministraUtenti.rimuovi(utenteBean);
			} catch (DatiUtenteInconsistentiException | LoginInconsistenteException e) {
				System.err.println("servlet: errore");
				e.printStackTrace();
			}
		} catch (UtenteInesistenteException e) {
			e.printStackTrace();
			System.err.println("servlet: errore");
			return;
		}
	}

}
