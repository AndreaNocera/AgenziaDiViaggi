package webvoyager.marciani.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webvoyager.marciani.controller.ControllerAmministraUtenti;
import webvoyager.marciani.exception.UtenteInesistenteException;
import webvoyager.marciani.model.bean.UtenteBean;



@WebServlet("/GetUtente")
public class GetUtente extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ControllerAmministraUtenti controllerAmministraUtenti;
       
    public GetUtente() {
        super();
        this.controllerAmministraUtenti = ControllerAmministraUtenti.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");		
		
		try {
			UtenteBean utenteBean = this.controllerAmministraUtenti.getUtente(username);	
			response.getWriter().write(utenteBean.asJson());
	        return;
		} catch (UtenteInesistenteException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
	}

}
