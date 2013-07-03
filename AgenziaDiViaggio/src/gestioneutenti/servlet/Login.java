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
import gestioneutenti.exception.UtenteInesistenteException;
import gestioneutenti.model.bean.LoginBean;
import gestioneutenti.model.bean.UtenteBean;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
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
        this.controllerLogin = ControllerLogin.getWebInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		LoginBean loginBean = new LoginBean().setUsername(username).setPassword(password);
		
		UtenteBean utenteBean;
		
		try {
			utenteBean = this.controllerLogin.login(loginBean);
			HttpSession session = request.getSession(true);
	        session.setAttribute("utente", utenteBean);
	        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Home.jsp");
            request.setAttribute("utente", utenteBean);
            rd.forward(request, response);
		} catch (UtenteInesistenteException e) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
            request.setAttribute("status", "failed");
            rd.forward(request, response);
		}        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
