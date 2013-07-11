package webvoyager.servlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webvoyager.controller.ControllerLogin;
import webvoyager.exception.BeanInconsistenteException;
import webvoyager.exception.UtenteEsistenteException;
import webvoyager.exception.UtenteInesistenteException;


@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ControllerLogin controllerLogin;
       
    public ResetPassword() {
    	super();
    	this.controllerLogin = ControllerLogin.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String username = request.getParameter("username");
		
		try {
			this.controllerLogin.impostaResetCode(username);
			return;
		} catch (UtenteInesistenteException | UtenteEsistenteException exc) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		} catch (BeanInconsistenteException exc) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}

}
