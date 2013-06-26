package ordinaViaggi.test;

import ordinaViaggi.dao.DAOCatalogo;
import ordinaViaggi.exception.DAOException;

public class TestNextId {
	public static void main(String[] args) {
		DAOCatalogo daoCatalogo = DAOCatalogo.getIstance();
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
