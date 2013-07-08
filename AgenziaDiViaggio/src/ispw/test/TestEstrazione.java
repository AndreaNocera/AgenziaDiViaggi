package ispw.test;

import java.sql.SQLException;
import java.util.List;

import ispw.entity.Ambiente;
import ispw.entity.Catalogo;
import ispw.exception.CatalogoException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.MapException;
import ispw.exception.OraException;

public class TestEstrazione {
	public static void main(String[] args) throws DAOException, MapException, SQLException, DataException, OraException, CatalogoException {
		Catalogo catalogo = Catalogo.getInstance();
		List<Ambiente> listaAmbienti = catalogo.getAmbienti();
		for(Ambiente ambiente: listaAmbienti)
			ambiente.print();
	}
}
