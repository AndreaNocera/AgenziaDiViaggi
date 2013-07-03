package gestioneutenti.servlet;

import gestioneutenti.controller.ControllerLogin;
import gestioneutenti.exception.UtenteInesistenteException;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ResetCode")
public class ResetCode extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ControllerLogin controllerLogin;
       
    public ResetCode() {
    	super();
    	this.controllerLogin = ControllerLogin.getWebInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		
		try {
			this.controllerLogin.impostaResetCode(username);
		} catch (UtenteInesistenteException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
