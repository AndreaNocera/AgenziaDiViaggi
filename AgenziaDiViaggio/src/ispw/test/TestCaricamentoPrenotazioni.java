package ispw.test;

import java.sql.SQLException;

import ispw.entity.Catalogo;
import ispw.exception.CatalogoException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.MapException;
import ispw.exception.OraException;

public class TestCaricamentoPrenotazioni {
	public static void main(String[] args) throws DAOException, MapException, SQLException, DataException, OraException, CatalogoException {
		Catalogo catalogo = Catalogo.getInstance();
		catalogo.printListaPrenotazioni();
	}
}
