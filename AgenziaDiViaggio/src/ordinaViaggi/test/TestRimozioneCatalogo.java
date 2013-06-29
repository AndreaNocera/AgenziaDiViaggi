package ordinaViaggi.test;

import java.sql.SQLException;

import ordinaViaggi.dao.DAOAmbiente;
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

public class TestRimozioneCatalogo {
	public static void main(String[] args) throws MapException, DAOException, CatalogoException, SQLException, DataException, OraException {
		Catalogo catalogo = Catalogo.getIstance();
		DAOAmbiente daoAmbiente = DAOAmbiente.getIstance();
		DAOMezzo daoMezzo = DAOMezzo.getIstance();
		DAOCitta daoCitta = DAOCitta.getIstance();
		DAOVia daoVia = DAOVia.getIstance();
		String ambiente = "Terra";
		String mezzo = "Treno";
		String cittaPartenza = "Roma";
		String cittaArrivo = "Napoli";
		String via = "Formia";

		Tratta tratta = catalogo.getTrattaByValue(
				daoAmbiente.getObjectByValue(ambiente),
				daoMezzo.getObjectByValue(mezzo),
				daoCitta.getObjectByValue(cittaPartenza),
				daoCitta.getObjectByValue(cittaArrivo),
				daoVia.getObjectByValue(via));

		System.out.println("Tratta da cancellare:");
		tratta.printTratta();
		
		System.out.println("Rimozione della tratta nel catalogo.");
		catalogo.printMapCatalogo();
		
		catalogo.rimozioneInCatalogo(tratta);
		catalogo.printMapCatalogo();
	}

}
