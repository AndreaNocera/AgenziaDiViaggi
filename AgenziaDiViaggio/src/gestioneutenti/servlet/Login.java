/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.servlet
 * 
 * @name Login.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package gestioneutenti.servlet;

import gestioneutenti.controller.ControllerLogin;
import gestioneutenti.exception.LoginErratoException;
import gestioneutenti.model.Utente;
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
        this.controllerLogin = ControllerLogin.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			Utente utente = this.controllerLogin.login(new UtenteBean().setUsername(username).setPassword(password));
			
			HttpSession session = request.getSession(true);
            session.setAttribute("utente", utente);
            
            try {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Home.jsp");
                request.setAttribute("utente", utente);
                rd.forward(request, response);
            } catch (Exception exc) {
            	exc.printStackTrace();
            }
		} catch (LoginErratoException exc) {
			response.sendRedirect("Login.jsp");
			exc.printStackTrace();
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
