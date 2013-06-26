package ordinaViaggi.test;

import ordinaViaggi.dao.DAOAmbiente;
import ordinaViaggi.exception.DAOException;

public class TestGetId {
	public static void main(String[] args) {
		DAOAmbiente daoAmbiente = DAOAmbiente.getIstance();
		String ambiente = "Terra";
		Integer id = 0;
		try {
			id = daoAmbiente.getIdByValue(ambiente);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Id: " + id);
	}
}
