package ispw.test;

import java.sql.SQLException;

import ispw.dao.DAOTratta;
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

public class TestDataGenerataTratta {
	public static void main(String[] args) throws DAOException, MapException,
			SQLException, DataException, OraException, CatalogoException {
		DAOTratta daoTratta = DAOTratta.getInstance();
		Ambiente ambiente;
		Mezzo mezzo;
		Citta cittaPartenza;
		Citta cittaArrivo;
		Via via;
		Tratta tratta;
		ambiente = Ambiente.getObjectByValue("Terra");
		mezzo = Mezzo.getObjectByValue("Treno TAV");
		cittaPartenza = Citta.getObjectByValue("Roma");
		cittaArrivo = Citta.getObjectByValue("Napoli");
		via = Via.getObjectByValue("Formia");
		tratta = new Tratta(Catalogo.getNextIdTratta(), ambiente, mezzo, cittaPartenza, cittaArrivo, via);
		tratta.printTratta();
		daoTratta.insert(tratta);
	}
}
