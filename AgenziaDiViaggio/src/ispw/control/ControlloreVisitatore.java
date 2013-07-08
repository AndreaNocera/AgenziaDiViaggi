package ispw.control;

import ispw.entity.Ambiente;
import ispw.entity.Catalogo;
import ispw.entity.Citta;
import ispw.entity.Mezzo;
import ispw.entity.Offerta;
import ispw.entity.Via;
import ispw.exception.CatalogoException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.MapException;
import ispw.exception.OraException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gambella Riccardo Controllore Visitatore.
 */
public class ControlloreVisitatore extends Controllore {
	private static ControlloreVisitatore instance = null;
	private static Catalogo catalogo = null;

	private ControlloreVisitatore() throws DAOException, MapException,
			SQLException, DataException, OraException, CatalogoException {
		super();
		catalogo = Catalogo.getInstance();
	}

	public static ControlloreVisitatore getIstance() throws DAOException,
			MapException, SQLException, DataException, OraException,
			CatalogoException {
		if (instance == null)
			instance = new ControlloreVisitatore();
		return instance;
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
		Catalogo catalogo = Catalogo.getInstance();
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
		List<Mezzo> listaMezzi = catalogo.getMezzi(Ambiente
				.getObjectByValue(ambiente));
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
		List<Citta> listaCittaPartenza = catalogo.getCittaPartenza(
				Ambiente.getObjectByValue(ambiente),
				Mezzo.getObjectByValue(mezzo));
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
		List<Citta> listaCittaArrivo = catalogo.getCittaArrivo(
				Ambiente.getObjectByValue(ambiente),
				Mezzo.getObjectByValue(mezzo),
				Citta.getObjectByValue(cittaPartenza));
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
		List<Via> listaVia = catalogo.getVia(
				Ambiente.getObjectByValue(ambiente),
				Mezzo.getObjectByValue(mezzo),
				Citta.getObjectByValue(cittaPartenza),
				Citta.getObjectByValue(cittaArrivo));
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
	 * @return Id dell'offerta aggiunta
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

		List<Offerta> listaOfferta = catalogo.getListaOfferte(
				Ambiente.getObjectByValue(listaCatalogo.get(0)),
				Mezzo.getObjectByValue(listaCatalogo.get(1)),
				Citta.getObjectByValue(listaCatalogo.get(2)),
				Citta.getObjectByValue(listaCatalogo.get(3)),
				Via.getObjectByValue(listaCatalogo.get(4)));
		List<String> lista = new ArrayList<String>();
		for (Offerta offerta : listaOfferta)
			lista.add(offerta.getString());
		return lista;

	}
	
	/**
	 * Visualizza offerta fornita la data.
	 * @param listaCatalogo
	 * @param giorno
	 * @param mese
	 * @param anno
	 * @return
	 * @throws DAOException
	 */
	public List<String> visualizzaOffertaByData(List<String> listaCatalogo,
			Integer giorno, Integer mese, Integer anno) throws DAOException {
		List<Offerta> listaOfferta = catalogo.getListaOfferte(
				Ambiente.getObjectByValue(listaCatalogo.get(0)),
				Mezzo.getObjectByValue(listaCatalogo.get(1)),
				Citta.getObjectByValue(listaCatalogo.get(2)),
				Citta.getObjectByValue(listaCatalogo.get(3)),
				Via.getObjectByValue(listaCatalogo.get(4)));
		List<String> lista = new ArrayList<String>();
		for (Offerta offerta : listaOfferta) {
			System.out.println(offerta.getString());
			if ((offerta.getData()).contains(giorno, mese, anno)) {
				System.out.println("Offerta valida: " + offerta.getString());
				lista.add(offerta.getString());
			}
		}
		return lista;
	}

	public boolean verificaData(String giorno, String mese) {
		// TODO Auto-generated method stub
		if (giorno.equals("") || mese.equals(""))
			return false;
		return true;
	}

}
