package ordinaViaggi.test;

import java.sql.SQLException;
import java.util.List;

import ordinaViaggi.entity.Ambiente;
import ordinaViaggi.entity.Catalogo;
import ordinaViaggi.exception.CatalogoException;
import ordinaViaggi.exception.DAOException;
import ordinaViaggi.exception.DataException;
import ordinaViaggi.exception.MapException;
import ordinaViaggi.exception.OraException;

public class TestEstrazione {
	public static void main(String[] args) throws DAOException, MapException, SQLException, DataException, OraException, CatalogoException {
		Catalogo catalogo = Catalogo.getIstance();
		List<Ambiente> listaAmbienti = catalogo.getAmbienti();
		for(Ambiente ambiente: listaAmbienti)
			ambiente.print();
	}
}
