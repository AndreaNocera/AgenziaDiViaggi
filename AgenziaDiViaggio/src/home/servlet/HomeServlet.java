/**
 * @project WebVoyager
 * 
 * @package home.servlet
 * 
 * @name HomeServlet.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package home.servlet;

import gestioneutenti.controller.ControllerAmministraUtenti;
import gestioneutenti.model.Utente;
import gestioneutenti.model.competenze.Competenza;
import home.exception.HomeException;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String JSP_AMMINISTRAZIONE_UTENTI = "/AmministraUtenti.jsp";
	private static final String JSP_LOGIN = "/SignIn.jsp";

    public HomeServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int competenza = (int) Integer.parseInt(request.getParameter("competenza"));	
		switch(competenza) {
		case Competenza.AMMINISTRAZIONE_UTENTI:
			 try {
				 ControllerAmministraUtenti controller = ControllerAmministraUtenti.getInstance();
				 Utente[] utenti = controller.getUtenti();
	             RequestDispatcher rd = getServletContext().getRequestDispatcher(JSP_AMMINISTRAZIONE_UTENTI);
	             request.setAttribute("utenti", utenti);
	             rd.forward(request, response);
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
		case Competenza.LOGIN:
			try {
                RequestDispatcher rd = getServletContext().getRequestDispatcher(JSP_LOGIN);
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
		default:
			try {
				throw new HomeException();
			} catch (HomeException exc) {
				exc.printStackTrace();
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
