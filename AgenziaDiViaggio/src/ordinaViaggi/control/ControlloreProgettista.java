package ordinaViaggi.control;

import ordinaViaggi.entity.Ambiente;
import ordinaViaggi.entity.Catalogo;
import ordinaViaggi.entity.Citta;
import ordinaViaggi.entity.Data;
import ordinaViaggi.entity.Mezzo;
import ordinaViaggi.entity.Offerta;
import ordinaViaggi.entity.Ora;
import ordinaViaggi.entity.Prenotazione;
import ordinaViaggi.entity.Tratta;
import ordinaViaggi.entity.Via;
import ordinaViaggi.exception.CatalogoException;
import ordinaViaggi.exception.ControllerException;
import ordinaViaggi.exception.DAOException;
import ordinaViaggi.exception.DataException;
import ordinaViaggi.exception.GestoreEccezioniException;
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

	public ControlloreProgettista() throws DAOException, MapException,
			SQLException, DataException, OraException, CatalogoException {
		super();
		catalogo = Catalogo.getIstance();
	}

	public static ControlloreProgettista getIstance() throws DAOException,
			MapException, SQLException, DataException, OraException,
			CatalogoException {
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
				listaCatalogo.get(0), listaCatalogo.get(1),
				listaCatalogo.get(2), listaCatalogo.get(3),
				listaCatalogo.get(4));
		List<String> lista = new ArrayList<String>();
		for (Offerta offerta : listaOfferta)
			lista.add(offerta.getString());
		return lista;

	}

	public Integer inserimentoInOfferta(List<String> listaCatalogo,
			Integer giorno, Integer mese, Integer anno, Integer ora,
			Integer minuto, Integer posti) throws ControllerException,
			IOException, DAOException, MapException, CatalogoException,
			DataException, OraException, SQLException {
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
				new Data(giorno, mese, anno), new Ora(ora, minuto), posti);

		catalogo.inserimentoInOfferta(tratta, offerta);
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

		// Se ci sono prenotazioni relative all'offerta la rimozione non �
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
		}
	}

	public boolean verificaDati(String giorno, String mese, String ora,
			String minuti, String posti) {
		if (giorno.equals("") || mese.equals("") || ora.equals("")
				|| minuti.equals("") || posti.equals(""))
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
