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
import javax.servlet.http.HttpSession;

@WebServlet("/AmministraUtenti")
public class AmministraUtenti extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final int NUOVO_UTENTE = 1;
	private static final int MODIFICA_UTENTE = 2;
	private static final int RIMUOVI_UTENTE = 3;
	private static final int CERCA_UTENTE = 4;
	
	private ControllerAmministraUtenti controllerAmministraUtenti = null;
       
    public AmministraUtenti() {
        super();
        this.controllerAmministraUtenti = ControllerAmministraUtenti.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int action = Integer.parseInt(request.getParameter("action"));
		System.err.println("servlet: action" + action);
		
		switch(action) {
		case NUOVO_UTENTE:
			System.err.println("servletAmministra: action NUOVO UTENTE");
			response.sendRedirect("NuovoUtente.jsp");
			break;
		case MODIFICA_UTENTE:
			System.err.println("servletAmministra: action MODIFICA UTENTE");
			try {
				String username = request.getParameter("username");
				UtenteBean utenteBean = this.controllerAmministraUtenti.getUtente(username);
				System.err.println("servlet: utente " + utenteBean.getNome());
				HttpSession session = request.getSession(true);
		        session.setAttribute("utenteSelezionato", utenteBean);
		        response.sendRedirect("ModificaUtente.jsp");
		        break;
			} catch (UtenteInesistenteException e) {
				e.printStackTrace();
				System.err.println("servlet: errore");
				return;
			}
		case RIMUOVI_UTENTE:
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
		        break;
			} catch (UtenteInesistenteException e) {
				e.printStackTrace();
				System.err.println("servlet: errore");
				return;
			}
		case CERCA_UTENTE:
			System.err.println("servletAmministra: action CERCA UTENTE");
			break;
		default:
			break;
		}
		
		return;		
		
	}

}
