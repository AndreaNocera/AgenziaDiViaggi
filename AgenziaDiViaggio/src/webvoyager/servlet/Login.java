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

package webvoyager.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webvoyager.controller.ControllerLogin;
import webvoyager.exception.BeanInconsistenteException;
import webvoyager.exception.UtenteInesistenteException;
import webvoyager.model.bean.LoginBean;
import webvoyager.model.bean.UtenteBean;



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
			Cookie cookieUsername = new Cookie("VoyagerUsername", loginBean.getUsername());
			Cookie cookiePassword = new Cookie("VoyagerPassword", loginBean.getPassword());
			cookieUsername.setMaxAge(60*60*24);
			cookiePassword.setMaxAge(60*60*24);
			response.addCookie(cookieUsername);
			response.addCookie(cookiePassword);
			HttpSession session = request.getSession(true);
	        session.setAttribute("utente", utenteBean);	        
	        return;
		} catch (UtenteInesistenteException exc) {
			HttpSession session = request.getSession(true);
	        session.setAttribute("utente", null);
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		} catch (BeanInconsistenteException exc) {
			HttpSession session = request.getSession(true);
	        session.setAttribute("utente", null);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		} 
	}

}
