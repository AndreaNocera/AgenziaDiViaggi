package ordinaViaggi.test;

import java.sql.SQLException;

import ordinaViaggi.dao.DAOCatalogo;
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

public class TestMappaCatalogo {
	public static void main(String[] args) throws SQLException, DataException, OraException {
		try {
			DAOCatalogo daoCatalogo = DAOCatalogo.getIstance();
			Catalogo catalogo = Catalogo.getIstance();
			// Creo una nuova tratta.
			Ambiente ambiente = new Ambiente(3, "Aria");
			Mezzo mezzo = new Mezzo(1, "Treno");
			Citta cittaPartenza = new Citta(1,"Roma");
			Citta cittaArrivo = new Citta(2,"Napoli");
			Via via = new Via(10,"Cassino");
			Integer idTratta = daoCatalogo.getNextId();
			Tratta tratta = new Tratta(idTratta, ambiente, mezzo, cittaPartenza, cittaArrivo, via);
			System.out.println("Tratta da inserire: " );
			tratta.printTratta();
			//Inserisco il catalogo -> Lista, DB, Mappa
			System.out.println("Inserimento tratta e mappa.");
			catalogo.inserimentoInCatalogo(tratta);
			System.out.println("Mappa catalogo:");
			catalogo.printMapCatalogo();
			
			
			
			
		} catch (CatalogoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MapException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
