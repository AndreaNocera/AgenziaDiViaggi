package gestioneutenti.servlet;

import gestioneutenti.controller.ControllerLogin;
import gestioneutenti.exception.LoginInconsistenteException;
import gestioneutenti.exception.LoginErratoException;
import gestioneutenti.model.Login;
import gestioneutenti.model.Utente;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ControllerLogin controllerLogin;

    public LoginServlet() {
        this.controllerLogin = ControllerLogin.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Login login;
		Utente utente;
		try {
			login = new Login(username, password);
			utente = this.controllerLogin.login(login);
			HttpSession session = request.getSession(true);
            session.setAttribute("utente", utente);
            
            try {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Home.jsp");
                request.setAttribute("utente", utente);
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
		} catch (LoginInconsistenteException | LoginErratoException e) {
			response.sendRedirect("Login.jsp");
			e.printStackTrace();
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
