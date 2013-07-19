package webvoyager.marciani.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webvoyager.marciani.controller.ControllerAmministraUtenti;
import webvoyager.marciani.model.bean.UtenteBean;



@WebServlet("/GetUtenti")
public class GetUtenti extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ControllerAmministraUtenti controllerAmministraUtenti;
	
    public GetUtenti() {
        super();
        this.controllerAmministraUtenti = ControllerAmministraUtenti.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("query");
		
		List<UtenteBean> listaUtenti = this.controllerAmministraUtenti.cerca(query);
		String jsonUsername = new String();
		
		for (int index = 0 ; index < listaUtenti.size() ; index ++) {
			jsonUsername += listaUtenti.get(index).getUsername();
			if (index + 1 < listaUtenti.size()) jsonUsername += ":";
		}
		
		response.getWriter().write(jsonUsername);
		return;
	}

}
