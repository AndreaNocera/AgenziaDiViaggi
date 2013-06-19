package gestioneutenti.servlet;

import gestioneutenti.controller.ControllerLogin;
import gestioneutenti.model.Login;
import gestioneutenti.model.Utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Login login = new Login();
		login.setUsername(request.getParameter("username"));
		login.setPassword(request.getParameter("password"));
		Utente utente = ControllerLogin.login(login);
		
		if (utente != null) {
			HttpSession session = request.getSession(true);
            session.setAttribute("currentSessionUser", utente);
            response.sendRedirect("LoginSuccesso.jsp");
		} else {
			response.sendRedirect("Login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
