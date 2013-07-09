/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.servlet
 * 
 * @name Login.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package gestioneutenti.servlet;

import gestioneutenti.controller.ControllerLogin;
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

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ControllerLogin controllerLogin;

    public Login() {
    	super();
        this.controllerLogin = ControllerLogin.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub      
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		LoginBean loginBean = new LoginBean().setUsername(username).setPassword(password);
		
		try {
			UtenteBean utenteBean = this.controllerLogin.login(loginBean);
			HttpSession session = request.getSession(true);
	        session.setAttribute("utente", utenteBean);
            //response.sendRedirect("Home.jsp");
	        return;
		} catch (UtenteInesistenteException | LoginInconsistenteException e) {
			HttpSession session = request.getSession(true);
	        session.setAttribute("utente", null);
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	        //response.sendRedirect("FallimentoLogin.jsp");
			return;
		} 
	}

}
