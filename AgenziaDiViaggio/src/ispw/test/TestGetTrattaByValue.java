package ispw.test;

import java.sql.SQLException;

import ispw.dao.DAOAmbiente;
import ispw.dao.DAOCitta;
import ispw.dao.DAOMezzo;
import ispw.dao.DAOVia;
import ispw.entity.Ambiente;
import ispw.entity.Catalogo;
import ispw.entity.Citta;
import ispw.entity.Mezzo;
import ispw.entity.Tratta;
import ispw.entity.Via;
import ispw.exception.CatalogoException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.MapException;
import ispw.exception.OraException;

public class TestGetTrattaByValue {
	public static void main(String[] args) throws DAOException, MapException, CatalogoException, SQLException, DataException, OraException {
		Ambiente ambiente;
		DAOAmbiente daoAmbiente = DAOAmbiente.getInstance();
		ambiente = daoAmbiente.getObjectByValue("Terra");

		Mezzo mezzo;
		DAOMezzo daoMezzo = DAOMezzo.getInstance();
		mezzo = daoMezzo.getObjectByValue("Treno");

		Citta  cittaPartenza;
		DAOCitta daoCitta = DAOCitta.getInstance();
		cittaPartenza = daoCitta.getObjectByValue("Roma");
		
		Citta cittaArrivo;
		cittaArrivo = daoCitta.getObjectByValue("Napoli");
		
		Via via;
		DAOVia daoVia = DAOVia.getInstance();
		via = daoVia.getObjectByValue("Formia");
		
		Catalogo catalogo = Catalogo.getInstance();
		Tratta tratta = catalogo.getTrattaByValue(ambiente, mezzo, cittaPartenza, cittaArrivo, via);
		
		tratta.printTratta();
		
		
		
	}
}
