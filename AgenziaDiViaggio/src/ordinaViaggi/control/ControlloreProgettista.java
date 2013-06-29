package ordinaViaggi.control;

import ordinaViaggi.dao.DAOAmbiente;
import ordinaViaggi.dao.DAOCitta;
import ordinaViaggi.dao.DAOMezzo;
import ordinaViaggi.dao.DAOOfferta;
import ordinaViaggi.dao.DAOVia;
import ordinaViaggi.entity.Ambiente;
import ordinaViaggi.entity.Catalogo;
import ordinaViaggi.entity.Citta;
import ordinaViaggi.entity.Data;
import ordinaViaggi.entity.Mezzo;
import ordinaViaggi.entity.Offerta;
import ordinaViaggi.entity.Ora;
import ordinaViaggi.entity.Tratta;
import ordinaViaggi.entity.Via;
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
 * @author Gambella Riccardo Controllore Progettista.
 */
public class ControlloreProgettista extends Controllore {
	private static ControlloreProgettista istance = null;
	private static Catalogo catalogo = null;
	private static DAOAmbiente daoAmbiente = null;
	private static DAOMezzo daoMezzo = null;
	private static DAOCitta daoCitta = null;
	private static DAOVia daoVia = null;
	private static DAOOfferta daoOfferta = null;
	
	
	public ControlloreProgettista() throws DAOException, MapException, SQLException, DataException, OraException, CatalogoException {
		super();
		catalogo = Catalogo.getIstance();
		daoAmbiente = DAOAmbiente.getIstance();
		daoMezzo = DAOMezzo.getIstance();
		daoCitta = DAOCitta.getIstance();
		daoVia = DAOVia.getIstance();
		daoOfferta = DAOOfferta.getIstance();
		
	}

	public static ControlloreProgettista getIstance() throws DAOException, MapException, SQLException, DataException, OraException, CatalogoException {
		if (istance == null)
			istance = new ControlloreProgettista();
		return istance;
	}

	/**
	 * Estrazione da mapCatalogo degli ambienti
	 * 
	 * @return
	 * @throws DAOException
	 * @throws MapException
	 * @throws CatalogoException
	 * @throws OraException
	 * @throws DataException
	 * @throws SQLException
	 */
	public List<String> estrazioneAmbienti() throws DAOException, MapException,
			SQLException, DataException, OraException, CatalogoException {
		Catalogo catalogo = Catalogo.getIstance();
		List<Ambiente> listaAmbienti = catalogo.getAmbienti();
		List<String> lista = new ArrayList<String>();
		for (Ambiente ambiente : listaAmbienti)
			lista.add(ambiente.getValore());
		return lista;
	}

	/**
	 * Estrazione da mapCatalogo dei mezzi
	 * 
	 * @param ambiente
	 * @return
	 * @throws DAOException
	 * @throws MapException
	 * @throws CatalogoException
	 * @throws OraException
	 * @throws DataException
	 * @throws SQLException
	 */
	public List<String> estrazioneMezzi(String ambiente) throws DAOException,
			MapException, SQLException, DataException, OraException,
			CatalogoException {
		List<Mezzo> listaMezzi = catalogo.getMezzi(ambiente);
		List<String> lista = new ArrayList<String>();
		for (Mezzo mezzo : listaMezzi)
			lista.add(mezzo.getValore());
		return lista;
	}

	/**
	 * Estrazione da mapCatalogo delle citta di partenza
	 * 
	 * @param ambiente
	 * @return
	 * @throws DAOException
	 * @throws MapException
	 * @throws CatalogoException
	 * @throws OraException
	 * @throws DataException
	 * @throws SQLException
	 */
	public List<String> estrazioneCittaPartenza(String ambiente, String mezzo)
			throws DAOException, MapException, SQLException, DataException,
			OraException, CatalogoException {
		List<Citta> listaCittaPartenza = catalogo.getCittaPartenza(ambiente,
				mezzo);
		List<String> lista = new ArrayList<String>();
		for (Citta cittaPartenza : listaCittaPartenza)
			lista.add(cittaPartenza.getValore());
		return lista;
	}

	/**
	 * Estrazione da mapCatalogo delle citta di arrivo
	 * 
	 * @param ambiente
	 * @param mezzo
	 * @param cittaPartenza
	 * @return
	 * @throws MapException
	 * @throws DAOException
	 * @throws CatalogoException
	 * @throws OraException
	 * @throws DataException
	 * @throws SQLException
	 */
	public List<String> estrazioneCittaArrivo(String ambiente, String mezzo,
			String cittaPartenza) throws DAOException, MapException,
			SQLException, DataException, OraException, CatalogoException {
		List<Citta> listaCittaArrivo = catalogo.getCittaArrivo(ambiente, mezzo,
				cittaPartenza);
		List<String> lista = new ArrayList<String>();
		for (Citta cittaArrivo : listaCittaArrivo)
			lista.add(cittaArrivo.getValore());
		return lista;
	}

	/**
	 * Estrazione da mapCatalogo delle vie
	 * 
	 * @param ambiente
	 * @param mezzo
	 * @param cittaPartenza
	 * @param cittaArrivo
	 * @return
	 * @throws MapException
	 * @throws DAOException
	 * @throws CatalogoException
	 * @throws OraException
	 * @throws DataException
	 * @throws SQLException
	 */
	public List<String> estrazioneVia(String ambiente, String mezzo,
			String cittaPartenza, String cittaArrivo) throws DAOException,
			MapException, SQLException, DataException, OraException,
			CatalogoException {
		List<Via> listaVia = catalogo.getVia(ambiente, mezzo, cittaPartenza,
				cittaArrivo);
		List<String> lista = new ArrayList<String>();
		for (Via via : listaVia)
			lista.add(via.getValore());
		return lista;
	}

	/**
	 * Estrazione da mapCatalogo delle offerte relative a un viaggio.
	 * 
	 * @param ambiente
	 * @param mezzo
	 * @param cittaPartenza
	 * @param cittaArrivo
	 * @param via
	 * @return
	 * @throws DAOException
	 * @throws MapException
	 * @throws CatalogoException
	 * @throws OraException
	 * @throws DataException
	 * @throws SQLException
	 */
	public List<String> visualizzaOfferta(List<String> listaCatalogo)
			throws DAOException, MapException, SQLException, DataException,
			OraException, CatalogoException {
		// TODO Auto-generated method stub

		List<Offerta> listaOfferta = catalogo.getListaOfferte(listaCatalogo.get(0),
				listaCatalogo.get(1), listaCatalogo.get(2),
				listaCatalogo.get(3), listaCatalogo.get(4));
		List<String> lista = new ArrayList<String>();
		for (Offerta offerta : listaOfferta)
			lista.add(offerta.getString());
		return lista;

	}

	public void inserimentoInOfferta(List<String> listaCatalogo,
			Integer giorno, Integer mese, Integer anno, Integer ora, Integer minuto,
			Integer posti) throws ControllerException, IOException,
			DAOException, MapException, CatalogoException, DataException,
			OraException, SQLException {
		// TODO Auto-generated method stub

		// Ottengo la tratta dal catalogo.
		// Deve esistere oppure ci sono errori nelle comboBox.
		Tratta tratta = catalogo.getTrattaByValue(
				daoAmbiente.getObjectByValue(listaCatalogo.get(0)),
				daoMezzo.getObjectByValue(listaCatalogo.get(1)),
				daoCitta.getObjectByValue(listaCatalogo.get(2)),
				daoCitta.getObjectByValue(listaCatalogo.get(3)),
				daoVia.getObjectByValue(listaCatalogo.get(4)));

		Offerta offerta = new Offerta(daoOfferta.getNextId(), tratta.getId(),
				new Data(giorno, mese, anno), new Ora(ora, minuto), posti);

		System.out.println("Offerta da inserire.");
		offerta.print();

		catalogo.inserimentoInOfferta(tratta, offerta);
	}
	
	
	

	public void rimozioneInOfferta(List<String> listaCatalogo,
			Integer giorno, Integer mese, Integer anno, Integer ora, Integer minuti,
			Integer posti) throws ControllerException, IOException,
			DAOException, MapException, CatalogoException, DataException,
			OraException, SQLException {
		// TODO Auto-generated method stub

		// Ottengo la tratta dal catalogo.
		// Deve esistere oppure ci sono errori nelle comboBox.
		Tratta tratta = catalogo.getTrattaByValue(
				daoAmbiente.getObjectByValue(listaCatalogo.get(0)),
				daoMezzo.getObjectByValue(listaCatalogo.get(1)),
				daoCitta.getObjectByValue(listaCatalogo.get(2)),
				daoCitta.getObjectByValue(listaCatalogo.get(3)),
				daoVia.getObjectByValue(listaCatalogo.get(4)));

		Offerta offerta = catalogo.getOffertaByValue(tratta.getId(), giorno, mese, anno , ora, minuti, posti);
		
		System.out.println("Offerta da rimuovere.");
		offerta.print();

		catalogo.rimozioneInOfferta(tratta, offerta);
	}

	
	public boolean verificaDati(String giorno, String mese,
			String ora, String minuti, String posti) {
		if (giorno.equals("") || mese.equals("") || ora.equals("")
				|| minuti.equals("") || posti.equals(""))
			return false;
		return true;

	}

}
