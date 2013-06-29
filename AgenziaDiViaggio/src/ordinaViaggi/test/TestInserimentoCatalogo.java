package ordinaViaggi.test;

import java.sql.SQLException;

import ordinaViaggi.dao.DAOAmbiente;
import ordinaViaggi.dao.DAOCatalogo;
import ordinaViaggi.dao.DAOCitta;
import ordinaViaggi.dao.DAOMezzo;
import ordinaViaggi.dao.DAOVia;
import ordinaViaggi.entity.Catalogo;
import ordinaViaggi.entity.Tratta;
import ordinaViaggi.exception.CatalogoException;
import ordinaViaggi.exception.DAOException;
import ordinaViaggi.exception.DataException;
import ordinaViaggi.exception.MapException;
import ordinaViaggi.exception.OraException;

public class TestInserimentoCatalogo {
	public static void main(String[] args) throws MapException, DAOException, CatalogoException, SQLException, DataException, OraException {
		DAOCatalogo daoCatalogo = DAOCatalogo.getIstance();
		DAOAmbiente daoAmbiente = DAOAmbiente.getIstance();
		DAOMezzo daoMezzo = DAOMezzo.getIstance();
		DAOCitta daoCitta = DAOCitta.getIstance();
		DAOVia daoVia = DAOVia.getIstance();
		Integer id = 0;
		Tratta tratta = new Tratta();
		id = daoCatalogo.getNextId();
		System.out.println("Id generato della tratta: " + id);
		tratta.setId(id);

		String ambiente = "Terra";
		tratta.setAmbiente(daoAmbiente.getObjectByValue(ambiente));

		String mezzo = "Treno";
		tratta.setMezzo(daoMezzo.getObjectByValue(mezzo));

		String cittaPartenza = "Roma";
		tratta.setCittaPartenza(daoCitta.getObjectByValue(cittaPartenza));

		String cittaArrivo = "Napoli";
		tratta.setCittaArrivo(daoCitta.getObjectByValue(cittaArrivo));

		String via = "Formia";
		tratta.setVia(daoVia.getObjectByValue(via));

		System.out.println("Tratta finale:");
		tratta.printTratta();
		System.out.println("Inserimento della tratta nel catalogo.");
		Catalogo catalogo = Catalogo.getIstance();
		catalogo.printMapCatalogo();
		catalogo.inserimentoInCatalogo(tratta);
		catalogo.printMapCatalogo();
	}

}
