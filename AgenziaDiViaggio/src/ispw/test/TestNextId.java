package ispw.test;

import ispw.dao.DAOCatalogo;
import ispw.exception.DAOException;

public class TestNextId {
	public static void main(String[] args) {
		DAOCatalogo daoCatalogo = DAOCatalogo.getInstance();
		Integer id = -1;
		try {
			id = daoCatalogo.getNextId();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Id generato: " + id);
	}
}
