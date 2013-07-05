/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.servlet
 * 
 * @name GestioneProfilo.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package gestioneutenti.servlet;

import gestioneutenti.controller.ControllerGestisciProfilo;
import gestioneutenti.model.bean.UtenteBean;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GestioneProfilo")
public class GestioneProfilo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ControllerGestisciProfilo controllerGestisciProfilo;

    public GestioneProfilo() {
        super();
        this.controllerGestisciProfilo = ControllerGestisciProfilo.getWebInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
