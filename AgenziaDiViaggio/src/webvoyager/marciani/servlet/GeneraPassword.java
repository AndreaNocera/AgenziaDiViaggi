package webvoyager.marciani.servlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webvoyager.marciani.model.FactoryPassword;


@WebServlet("/GeneraPassword")
public class GeneraPassword extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private FactoryPassword factoryPassword;
       
    public GeneraPassword() {
        super();
        this.factoryPassword = FactoryPassword.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = this.factoryPassword.creaPassword();
		response.getWriter().write(password);
		return;
	}

}
