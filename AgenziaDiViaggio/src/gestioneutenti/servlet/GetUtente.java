package gestioneutenti.servlet;

import gestioneutenti.controller.ControllerAmministraUtenti;
import gestioneutenti.exception.UtenteInesistenteException;
import gestioneutenti.model.bean.UtenteBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		System.err.println("servlet: username utente selezionato" + username);
		
		
		try {
			UtenteBean utenteBean = this.controllerAmministraUtenti.getUtente(username);
			System.err.println("servlet: nome utentebean selezionato" + utenteBean.getNome());			
			HttpSession session = request.getSession(true);
	        session.setAttribute("utenteSelezionato", utenteBean);
	        return;
		} catch (UtenteInesistenteException e) {
			response.sendRedirect("FallimentoLogin.jsp");
			return;
		}
	}

}
