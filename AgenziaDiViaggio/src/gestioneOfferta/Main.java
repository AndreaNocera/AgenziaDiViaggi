package gestioneOfferta;

import gestioneOfferta.dao.RawDataDAO;
import gestioneOfferta.entity.Elemento;
import gestioneOfferta.entity.IDEsterno;
import gestione_Catalogo.entity.Info;
import gestione_Catalogo.exception.DAOException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Elemento e = new Elemento(new IDEsterno("10"), "provanome" , new Info("prova"));
		try {
			RawDataDAO.insert(e);
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
