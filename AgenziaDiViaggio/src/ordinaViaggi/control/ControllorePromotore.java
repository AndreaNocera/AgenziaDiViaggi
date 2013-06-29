package ordinaViaggi.control;

import ordinaViaggi.dao.DAOAmbiente;
import ordinaViaggi.dao.DAOCatalogo;
import ordinaViaggi.dao.DAOCitta;
import ordinaViaggi.dao.DAOMezzo;
import ordinaViaggi.dao.DAOVia;
import ordinaViaggi.entity.Catalogo;
import ordinaViaggi.entity.Tratta;
import ordinaViaggi.exception.CatalogoException;
import ordinaViaggi.exception.ControllerException;
import ordinaViaggi.exception.DAOException;
import ordinaViaggi.exception.DataException;
import ordinaViaggi.exception.MapException;
import ordinaViaggi.exception.OraException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gambella Riccardo Controllore Promotore.
 */

public class ControllorePromotore extends Controllore {
	private static ControllorePromotore istance = null;
	private static Catalogo catalogo = null;
	private static DAOCatalogo daoCatalogo = null;
	private static DAOAmbiente daoAmbiente = null;
	private static DAOMezzo daoMezzo = null;
	private static DAOCitta daoCitta = null;
	private static DAOVia daoVia = null;
	
	public ControllorePromotore() throws DAOException, MapException, SQLException, DataException, OraException, CatalogoException {
		super();
		catalogo = Catalogo.getIstance();
		daoCatalogo = DAOCatalogo.getIstance();
		daoAmbiente = DAOAmbiente.getIstance();
		daoMezzo = DAOMezzo.getIstance();
		daoCitta = DAOCitta.getIstance();
		daoVia = DAOVia.getIstance();
	}

	public static ControllorePromotore getIstance() throws DAOException, MapException, SQLException, DataException, OraException, CatalogoException {
		if (istance == null)
			istance = new ControllorePromotore();
		return istance;
	}

	public void inserimentoCatalogo(String ambiente, String mezzo,
			String cittaPartenza, String cittaArrivo, String via)
			throws ControllerException, IOException, DAOException,
			MapException, CatalogoException, SQLException, DataException,
			OraException {
		System.out.println("Inserimento nel catalogo");
		// Richiesta inserimento di Ambiente, Mezzo, CittaPartenza, CittaArrivo
		// da StdIn
		Tratta tratta = new Tratta();

		Integer id = null;

		// Prende un nuovo id per la costruzione della tratta.
		id = daoCatalogo.getNextId();
		tratta.setId(id);
		// Inserisce gli oggetti nella tratta, creandoli nel Db se non
		// esistenti.
		tratta.setAmbiente(daoAmbiente.getObjectByValue(ambiente));
		tratta.setMezzo(daoMezzo.getObjectByValue(mezzo));
		tratta.setCittaPartenza(daoCitta.getObjectByValue(cittaPartenza));
		tratta.setCittaArrivo(daoCitta.getObjectByValue(cittaArrivo));
		tratta.setVia(daoVia.getObjectByValue(via));

		System.out.print("Tratta da inserire: ");
		tratta.printTratta();

		catalogo.inserimentoInCatalogo(tratta);
	}

	public void rimozioneInCatalogo(String ambiente, String mezzo,
			String cittaPartenza, String cittaArrivo, String via)
			throws ControllerException, IOException, DAOException,
			MapException, CatalogoException, SQLException, DataException,
			OraException {
		
		System.out.println("Rimozione nel catalogo");

		// Ottengo la tratta dal catalogo.
		// Deve esistere oppure ci sono errori nelle comboBox.
		Tratta tratta = catalogo.getTrattaByValue(
				daoAmbiente.getObjectByValue(ambiente),
				daoMezzo.getObjectByValue(mezzo),
				daoCitta.getObjectByValue(cittaPartenza),
				daoCitta.getObjectByValue(cittaArrivo),
				daoVia.getObjectByValue(via));

		System.out.println("Tratta da rimuovere.");
		tratta.printTratta();

		catalogo.rimozioneInCatalogo(tratta);
	}

	public List<String> visualizzaCatalogo() throws ControllerException,
			DAOException, MapException, CatalogoException, SQLException,
			DataException, OraException {
		// TODO Auto-generated method stub
		List<String> listaTratte = new ArrayList<String>();
		List<Tratta> tratte = catalogo.visualizzaCatalogo();
		for (Tratta tratta : tratte) {
			listaTratte.add(tratta.getString());
		}
		return listaTratte;
	}

	public boolean verificaDati(String ambiente, String mezzo,
			String cittaPartenza, String cittaArrivo, String via) {
		if (ambiente.equals("") || mezzo.equals("") || cittaPartenza.equals("")
				|| cittaArrivo.equals("") || via.equals(""))
			return false;
		return true;

	}

}
