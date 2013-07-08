package ispw.control;

import ispw.entity.Ambiente;
import ispw.entity.Catalogo;
import ispw.entity.Citta;
import ispw.entity.Data;
import ispw.entity.Mezzo;
import ispw.entity.Offerta;
import ispw.entity.Ora;
import ispw.entity.Prenotazione;
import ispw.entity.Tratta;
import ispw.entity.Via;
import ispw.exception.CatalogoException;
import ispw.exception.ControllerException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.GestoreEccezioniException;
import ispw.exception.MapException;
import ispw.exception.OraException;
import ispw.log.Log;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gambella Riccardo Controllore Progettista.
 */
public class ControlloreProgettista extends Controllore {
	private static ControlloreProgettista instance = null;
	private static Catalogo catalogo = null;

	private ControlloreProgettista() throws DAOException, MapException,
			SQLException, DataException, OraException, CatalogoException {
		super();
		catalogo = Catalogo.getInstance();
	}

	public static ControlloreProgettista getInstance() throws DAOException,
			MapException, SQLException, DataException, OraException,
			CatalogoException {
		if (instance == null)
			instance = new ControlloreProgettista();
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

	public Integer inserimentoInOfferta(List<String> listaCatalogo,
			Integer giorno, Integer mese, Integer anno, Integer oraPartenza,
			Integer minutoPartenza, Integer oraArrivo, Integer minutoArrivo,
			Integer posti) throws ControllerException, IOException,
			DAOException, MapException, CatalogoException, DataException,
			OraException, SQLException {
		// TODO Auto-generated method stub

		// Ottengo la tratta dal catalogo.
		// Deve esistere oppure ci sono errori nelle comboBox.
		Tratta tratta = catalogo.getTrattaByValue(
				Ambiente.getObjectByValue(listaCatalogo.get(0)),
				Mezzo.getObjectByValue(listaCatalogo.get(1)),
				Citta.getObjectByValue(listaCatalogo.get(2)),
				Citta.getObjectByValue(listaCatalogo.get(3)),
				Via.getObjectByValue(listaCatalogo.get(4)));

		Offerta offerta = new Offerta(Offerta.getNextId(), tratta.getId(),
				new Data(giorno, mese, anno), new Ora(oraPartenza,
						minutoPartenza), new Ora(oraArrivo, minutoArrivo),
				posti);

		catalogo.inserimentoInOfferta(tratta, offerta);
		
		Log log = Log.getInstance();
		log.ScriviLog("Progettista", "Aggiunta offerta " + offerta.getString());
		
		return offerta.getIdOfferta();
	}

	public void rimozioneInOfferta(List<String> listaCatalogo, Integer idOfferta)
			throws ControllerException, IOException, DAOException,
			MapException, CatalogoException, DataException, OraException,
			SQLException, GestoreEccezioniException {
		// TODO Auto-generated method stub

		// Ottengo la tratta dal catalogo.
		// Deve esistere oppure ci sono errori nelle comboBox.
		Tratta tratta = catalogo.getTrattaByValue(
				Ambiente.getObjectByValue(listaCatalogo.get(0)),
				Mezzo.getObjectByValue(listaCatalogo.get(1)),
				Citta.getObjectByValue(listaCatalogo.get(2)),
				Citta.getObjectByValue(listaCatalogo.get(3)),
				Via.getObjectByValue(listaCatalogo.get(4)));

		Offerta offerta = catalogo.getOffertaById(idOfferta);

		// Se ci sono prenotazioni relative all'offerta la rimozione non è
		// consentita
		List<Prenotazione> listaPrenotazioni = catalogo.getListaPrenotazioni(
				tratta.getAmbiente().getValore(),
				tratta.getMezzo().getValore(), tratta.getCittaPartenza()
						.getValore(), tratta.getCittaArrivo().getValore(),
				tratta.getVia().getValore(), offerta.getIdOfferta());

		if (!listaPrenotazioni.isEmpty()) {
			throw new GestoreEccezioniException(
					"Impossibile rimuovere offerta.\nEsistono prenotazioni relative all'offerta.");
		} else {
			catalogo.rimozioneInOfferta(tratta, offerta);
			Log log = Log.getInstance();
			log.ScriviLog("Progettista", "Rimossa offerta " + offerta.getString());
		}
	}

	public boolean verificaDati(String giorno, String mese, String oraPartenza,
			String minutiPartenza, String oraArrivo, String minutiArrivo,
			String posti) {
		if (giorno.equals("--") || mese.equals("--")
				|| oraPartenza.equals("--") || minutiPartenza.equals("--")
				|| oraArrivo.equals("--") || minutiArrivo.equals("--")
				|| posti.equals(""))
			return false;
		return true;

	}

	public boolean verificaDati(String offerta) {
		// TODO Auto-generated method stub
		if (offerta.equals(""))
			return false;
		return true;
	}

}
