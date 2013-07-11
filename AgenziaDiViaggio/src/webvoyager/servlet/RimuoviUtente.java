package webvoyager.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webvoyager.controller.ControllerAmministraUtenti;
import webvoyager.exception.BeanInconsistenteException;
import webvoyager.exception.UtenteInesistenteException;
import webvoyager.model.bean.UtenteBean;



@WebServlet("/RimuoviUtente")
public class RimuoviUtente extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ControllerAmministraUtenti controllerAmministraUtenti;
       
    public RimuoviUtente() {
        super();
        this.controllerAmministraUtenti = ControllerAmministraUtenti.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		UtenteBean utenteBean = null;
		
		try {			
			utenteBean = this.controllerAmministraUtenti.getUtente(username);
			this.controllerAmministraUtenti.rimuovi(utenteBean);
		} catch (UtenteInesistenteException exc) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		} catch (BeanInconsistenteException exc) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}

}
