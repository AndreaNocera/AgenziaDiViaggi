package ordinaViaggi.test;

import java.sql.SQLException;

import ordinaViaggi.entity.Catalogo;
import ordinaViaggi.exception.CatalogoException;
import ordinaViaggi.exception.DAOException;
import ordinaViaggi.exception.DataException;
import ordinaViaggi.exception.MapException;
import ordinaViaggi.exception.OraException;

public class TestInizializzazioneMappa {
	public static void main(String[] args) throws DAOException, MapException, SQLException, DataException, OraException, CatalogoException {
		Catalogo catalogo = Catalogo.getIstance();
		try {
			catalogo.printMapOfferta();
		} catch (MapException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
