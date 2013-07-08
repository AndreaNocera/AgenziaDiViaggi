package ispw.control;

import ispw.entity.Ambiente;
import ispw.entity.Biglietto;
import ispw.entity.Catalogo;
import ispw.entity.Citta;
import ispw.entity.Mezzo;
import ispw.entity.Offerta;
import ispw.entity.Prenotazione;
import ispw.entity.Tratta;
import ispw.entity.Traveler;
import ispw.entity.Via;
import ispw.exception.CatalogoException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.MapException;
import ispw.exception.OraException;
import ispw.exception.PostiException;
import ispw.log.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gambella Riccardo Controllore Progettista.
 */

public class ControlloreCliente extends Controllore {
	private static ControlloreCliente instance = null;
	private static Catalogo catalogo = null;

	private ControlloreCliente() throws DAOException, MapException,
			SQLException, DataException, OraException, CatalogoException {
		super();
		catalogo = Catalogo.getInstance();

	}

	public static ControlloreCliente getInstance() throws DAOException,
			MapException, SQLException, DataException, OraException,
			CatalogoException {
		if (instance == null)
			instance = new ControlloreCliente();
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
	 * Inserimento di una prenotazione.
	 * 
	 * @param listaCatalogo
	 * @param idOfferta
	 * @param acquirente
	 * @param listaNomi
	 * @param listaCognomi
	 * @param listaEmail
	 * @return Id della prenotazione inserita
	 * @throws DAOException
	 * @throws CatalogoException
	 * @throws MapException
	 * @throws PostiException
	 */
	public Integer inserimentoInPrenotazione(List<String> listaCatalogo,
			Integer idOfferta, String acquirente, List<String> listaNomi,
			List<String> listaCognomi, List<String> listaEmail)
			throws DAOException, CatalogoException, MapException,
			PostiException {
		// TODO Auto-generated method stub
		Integer idPrenotazione = Prenotazione.getNextId();
		List<Biglietto> listaBiglietti = new ArrayList<Biglietto>();
		// Get della tratta e dell'offerta dai dati inseriti dall'utente
		Tratta tratta = catalogo.getTrattaByValue(
				Ambiente.getObjectByValue(listaCatalogo.get(0)),
				Mezzo.getObjectByValue(listaCatalogo.get(1)),
				Citta.getObjectByValue(listaCatalogo.get(2)),
				Citta.getObjectByValue(listaCatalogo.get(3)),
				Via.getObjectByValue(listaCatalogo.get(4)));
		Offerta offerta = catalogo.getOffertaById(idOfferta);
		Integer numPostiPrenotati = listaNomi.size();
		// Verifica disponibilità posti
		if (numPostiPrenotati > offerta.getPosti()) {
			throw new PostiException("Posti non disponibili");
		} else {
			while (!listaNomi.isEmpty()) {
				String nome = listaNomi.remove(0);
				String cognome = listaCognomi.remove(0);
				String email = listaEmail.remove(0);
				Integer idBiglietto = Biglietto.getNextId();

				Traveler traveler = Traveler.getObjectByValue(nome, cognome,
						email);
				// Creazione del biglietto e salvataggio nel database.
				Biglietto biglietto = new Biglietto(idBiglietto,
						idPrenotazione, traveler);
				biglietto.save();
				listaBiglietti.add(biglietto);

			}
			Prenotazione prenotazione = new Prenotazione(idPrenotazione,
					idOfferta, acquirente, listaBiglietti);
			// Inserimento della prenotazione nel catalogo
			catalogo.inserimentoInPrenotazione(tratta, offerta, prenotazione);
			// Decremento del numero dei posti nell'offerta Locale
			offerta.setPosti(offerta.getPosti() - numPostiPrenotati);
			// Update dei posti sul database
			offerta.updatePosti(offerta.getPosti());
			
			Log log = Log.getInstance();
			log.ScriviLog("Cliente", "Aggiunta prenotazione " + prenotazione.getString());
			
			return prenotazione.getIdPrenotazione();
		}

	}

	/**
	 * Rimozione di una prenotazione.
	 * 
	 * @param idPrenotazione
	 * @throws CatalogoException
	 * @throws DAOException
	 * @throws MapException
	 */
	public void rimozioneInPrenotazione(Integer idPrenotazione)
			throws CatalogoException, DAOException, MapException {
		// TODO Auto-generated method stub
		Prenotazione prenotazione = catalogo
				.getPrenotazioneById(idPrenotazione);
		Offerta offerta = catalogo.getOffertaById(prenotazione.getIdOfferta());
		Tratta tratta = catalogo.getTrattaById(offerta.getIdTratta());

		catalogo.rimozioneInPrenotazione(tratta, offerta, prenotazione);
		// Incremento dei posti liberati nell'offerta
		offerta.setPosti(offerta.getPosti()
				+ prenotazione.getListaBiglietti().size());
		// Update dei posti sul database
		offerta.updatePosti(offerta.getPosti());
		
		Log log = Log.getInstance();
		log.ScriviLog("Cliente", "Rimossa prenotazione " + prenotazione.getString());
	}

	/**
	 * Aggiunta di biglietti alla prenotazione.
	 * 
	 * @param idPrenotazione
	 * @param listaNomi
	 * @param listaCognomi
	 * @param listaEmail
	 * @throws CatalogoException
	 * @throws DAOException
	 */
	public void aggiuntaBiglietti(Integer idPrenotazione,
			List<String> listaNomi, List<String> listaCognomi,
			List<String> listaEmail) throws CatalogoException, DAOException {
		// TODO Auto-generated method stub
		Prenotazione prenotazione = catalogo
				.getPrenotazioneById(idPrenotazione);
		Integer numeroPosti = listaNomi.size();
		while (!listaNomi.isEmpty()) {
			Traveler traveler = Traveler.getObjectByValue(listaNomi.remove(0),
					listaCognomi.remove(0), listaEmail.remove(0));
			Biglietto biglietto = new Biglietto(Biglietto.getNextId(),
					prenotazione.getIdPrenotazione(), traveler);
			// Aggiunta del biglietto alla prenotazione
			prenotazione.addBiglietto(biglietto);
			// Save del biglietto
			biglietto.save();
		}
		// Update dei posti nell'offerta
		Offerta offerta = catalogo.getOffertaById(prenotazione.getIdOfferta());
		// Incremento dei posti liberati nell'offerta
		offerta.setPosti(offerta.getPosti() - numeroPosti);
		// Update dei posti sul database
		offerta.updatePosti(offerta.getPosti());
		
		Log log = Log.getInstance();
		log.ScriviLog("Cliente", "Biglietti aggiunti alla prenotazione " + prenotazione.getString());
	}

	/**
	 * Rimozione dei biglietti da modifica prenotazione
	 * 
	 * @param idPrenotazione
	 * @param listaBigliettiDaRimuovere
	 * @throws DAOException
	 * @throws CatalogoException
	 * @throws MapException
	 */
	public boolean rimozioneBiglietti(Integer idPrenotazione,
			List<String> listaBigliettiDaRimuovere) throws DAOException,
			CatalogoException, MapException {
		// TODO Auto-generated method stub
		Prenotazione prenotazione = catalogo
				.getPrenotazioneById(idPrenotazione);
		Integer numeroPosti = listaBigliettiDaRimuovere.size();
		while (!listaBigliettiDaRimuovere.isEmpty()) {
			Integer idBiglietto = new Integer(
					listaBigliettiDaRimuovere.remove(0));
			System.out.println("Biglietto di cui prendere id: " + idBiglietto);
			Biglietto biglietto = Biglietto.getObjectById(idBiglietto);
			// Rimuovi il biglietto dalla prenotazione e cancellalo dal
			// database.
			biglietto.print();
			prenotazione.removeBiglietto(biglietto);
			biglietto.delete();
		}

		// Update dei posti nell'offerta
		Offerta offerta = catalogo.getOffertaById(prenotazione.getIdOfferta());
		// Incremento dei posti liberati nell'offerta
		offerta.setPosti(offerta.getPosti() + numeroPosti);
		// Update dei posti sul database
		offerta.updatePosti(offerta.getPosti());

		// Cancello la prenotazione se non ho più biglietti associati.
		if (prenotazione.getListaBiglietti().isEmpty()) {
			Tratta tratta = catalogo.getTrattaById(offerta.getIdTratta());
			catalogo.rimozioneInPrenotazione(tratta, offerta, prenotazione);
			prenotazione.delete();
			Log log = Log.getInstance();
			log.ScriviLog("Cliente", "Rimossa prenotazione " + prenotazione.getString());
			return true;
		}
		Log log = Log.getInstance();
		log.ScriviLog("Cliente", "Rimossi biglietti da prenotazione " + prenotazione.getString());
		
		return false;
	}

	public boolean verificaData(String giorno, String mese) {
		// TODO Auto-generated method stub
		if (giorno.equals("") || mese.equals(""))
			return false;
		return true;
	}

	/**
	 * Estrazione da mapCatalogo delle offerte relative a un viaggio.
	 * 
	 * @param ambiente
	 * @param mezzo
	 * @param cittaPartenza
	 * @param cittaArrivo
	 * @param via
	 * @return Lista delle offerte
	 * @throws DAOException
	 * @throws MapException
	 * @throws CatalogoException
	 * @throws OraException
	 * @throws DataException
	 * @throws SQLException
	 */
	public List<String> visualizzaOfferta(List<String> listaCatalogo)
			throws DAOException {
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

	public boolean verificaId(String offertaInserita) {
		// TODO Auto-generated method stub
		if (offertaInserita.equals(""))
			return false;
		return true;
	}

	public boolean verificaDatiViaggiatore(String nome, String cognome,
			String email) {
		if (nome.equals("") || cognome.equals("") || email.equals(""))
			return false;
		return true;
	}

	public boolean verificaPrenotazione(String prenotazione) {
		// TODO Auto-generated method stub
		if (prenotazione.equals(""))
			return false;
		return true;
	}

	public List<String> getListaBigliettiByIdPrenotazione(Integer idPrenotazione)
			throws CatalogoException {
		// TODO Auto-generated method stub
		List<String> listaBiglietti = new ArrayList<String>();
		Prenotazione prenotazione = catalogo
				.getPrenotazioneById(idPrenotazione);
		for (Biglietto biglietto : prenotazione.getListaBiglietti()) {
			Traveler traveler = biglietto.getTraveler();
			listaBiglietti.add(biglietto.getIdBiglietto() + " "
					+ traveler.getNome() + " " + traveler.getCognome() + " "
					+ traveler.getEmail());
		}
		return listaBiglietti;
	}

}
