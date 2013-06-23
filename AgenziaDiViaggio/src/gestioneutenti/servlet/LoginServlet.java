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
		Login login = new Login(username, password);
		//Utente utente = this.controllerLogin.login(login);
		
		if (utente != null) {
			HttpSession session = request.getSession(true);
            session.setAttribute("currentSessionUser", utente);
            response.sendRedirect("LoginSuccesso.jsp");
		} else {
			response.sendRedirect("Login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
