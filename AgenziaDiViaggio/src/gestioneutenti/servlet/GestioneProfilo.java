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
import gestioneutenti.exception.UtenteInesistenteException;
import gestioneutenti.model.bean.LoginBean;
import gestioneutenti.model.bean.UtenteBean;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/GestioneProfilo")
public class GestioneProfilo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ControllerGestisciProfilo controllerGestisciProfilo;

    public GestioneProfilo() {
        super();
        this.controllerGestisciProfilo = ControllerGestisciProfilo.getWebInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UtenteBean utenteBean = (UtenteBean) session.getAttribute("utente");
		UtenteBean nUtenteBean = (UtenteBean) session.getAttribute("nUtente");	
		
		LoginBean loginBean = new LoginBean().setUsername(utenteBean.getUsername()).setPassword(utenteBean.getPassword());
		
		try {
			this.controllerGestisciProfilo.aggiornaProfilo(loginBean, nUtenteBean);
			session.setAttribute("nUtente", null);
			session.setAttribute("utente", nUtenteBean);
			return;
		} catch (UtenteInesistenteException | LoginInconsistenteException | DatiUtenteInconsistentiException e) {
			response.sendRedirect("FallimentoGestioneProfilo.jsp");
			return;
		}
	}

}
