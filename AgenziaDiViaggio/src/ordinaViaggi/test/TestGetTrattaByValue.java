package ordinaViaggi.test;

import java.sql.SQLException;

import ordinaViaggi.dao.DAOAmbiente;
import ordinaViaggi.dao.DAOCitta;
import ordinaViaggi.dao.DAOMezzo;
import ordinaViaggi.dao.DAOVia;
import ordinaViaggi.entity.Ambiente;
import ordinaViaggi.entity.Catalogo;
import ordinaViaggi.entity.Citta;
import ordinaViaggi.entity.Mezzo;
import ordinaViaggi.entity.Tratta;
import ordinaViaggi.entity.Via;
import ordinaViaggi.exception.CatalogoException;
import ordinaViaggi.exception.DAOException;
import ordinaViaggi.exception.DataException;
import ordinaViaggi.exception.MapException;
import ordinaViaggi.exception.OraException;

public class TestGetTrattaByValue {
	public static void main(String[] args) throws DAOException, MapException, CatalogoException, SQLException, DataException, OraException {
		Ambiente ambiente;
		DAOAmbiente daoAmbiente = DAOAmbiente.getIstance();
		ambiente = daoAmbiente.getObjectByValue("Terra");

		Mezzo mezzo;
		DAOMezzo daoMezzo = DAOMezzo.getIstance();
		mezzo = daoMezzo.getObjectByValue("Treno");

		Citta  cittaPartenza;
		DAOCitta daoCitta = DAOCitta.getIstance();
		cittaPartenza = daoCitta.getObjectByValue("Roma");
		
		Citta cittaArrivo;
		cittaArrivo = daoCitta.getObjectByValue("Napoli");
		
		Via via;
		DAOVia daoVia = DAOVia.getIstance();
		via = daoVia.getObjectByValue("Formia");
		
		Catalogo catalogo = Catalogo.getIstance();
		Tratta tratta = catalogo.getTrattaByValue(ambiente, mezzo, cittaPartenza, cittaArrivo, via);
		
		tratta.printTratta();
		
		
		
	}
}
