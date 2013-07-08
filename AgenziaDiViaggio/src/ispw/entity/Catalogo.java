/**
 * 
 */
package ispw.entity;

import ispw.dao.DAOBiglietto;
import ispw.dao.DAOCatalogo;
import ispw.dao.DAOOfferta;
import ispw.dao.DAOPrenotazione;
import ispw.exception.CatalogoException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.MapException;
import ispw.exception.OraException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
 * 
 * @author Gambella Riccardo
 * @generated 
 *            "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Catalogo {
	/**
	 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
	 * 
	 * @generated 
	 *            "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private static Catalogo catalogo;
	private static DAOCatalogo daoCatalogo;
	private List<Tratta> tratte;
	private List<Offerta> offerte;
	private List<Prenotazione> prenotazioni;
	private static MapCatalogo<ElementoCatalogo> mapCatalogo;

	private Catalogo() throws DAOException, MapException, SQLException,
			DataException, OraException, CatalogoException {
		/*
		 * Fetch del catalogo dal database.
		 */
		// Inizializzazione del DAOCatalogo
		daoCatalogo = DAOCatalogo.getInstance();
		// Creazione della lista synchronized
		tratte = Collections.synchronizedList(daoCatalogo.getCatalogo());
		// Creazione della mapCatalogo, con fetch iniziale dal DB.
		createMap();
	}

	public static synchronized Catalogo getInstance() throws DAOException, MapException,
			SQLException, DataException, OraException, CatalogoException {
		if (catalogo == null)
			catalogo = new Catalogo();
		return catalogo;
	}

	/**
	 * Inserimento di un elemento nel catalogo
	 * 
	 * @param listaCatalogo
	 * @param subElementsInfo
	 * @throws MapException
	 * @throws DAOException
	 */
	public synchronized void inserimentoInCatalogo(Tratta tratta) throws MapException,
			DAOException {
		// Inserimento nella lista delle tratte.
		tratte.add(tratta);
		// Inserimento nel Database
		tratta.save();
		// Inserimento nella mappa.
		inserimentoInMapTratte(tratta);
	}

	/**
	 * Rimozione di un elemento dal catalogo
	 * 
	 * @param listaCatalogo
	 * @throws DAOException
	 * @throws MapException
	 */

	public synchronized void rimozioneInCatalogo(Tratta tratta) throws CatalogoException,
			DAOException, MapException {
		// Rimozione della tratta nella lista
		tratte.remove(tratta);
		// Rimozione della tratta sul DB
		tratta.delete();
		// Rimozione della tratta nella mappa
		deleteInMapTratte(tratta);
	}

	/**
	 * Stampa del catalogo sotto forma di lista di tratte.
	 * 
	 * @throws MapException
	 */
	public List<Tratta> visualizzaCatalogo() throws CatalogoException {
		return tratte;
	}

	public void printListaOfferte() {
		synchronized(offerte){
			for (Offerta offerta : offerte)
				offerta.print();
		}
	}

	public void printListaPrenotazioni() {
		synchronized (prenotazioni) {
			for (Prenotazione prenotazione : prenotazioni)
				prenotazione.print();
		}
	}

	/**
	 * Prende l'id successivo della tratta per inserimento.
	 * 
	 * @return
	 * @throws DAOException
	 */
	public synchronized static Integer getNextIdTratta() throws DAOException {
		// TODO Auto-generated method stub
		return daoCatalogo.getNextId();
	}

	public synchronized Tratta getTrattaById(Integer idTratta) throws CatalogoException {
		for (Tratta tratta : tratte) {
			if (tratta.getId().equals(idTratta))
				return tratta;
		}
		// Se la tratta non c'è errore nell'utilizzo
		throw new CatalogoException("Errore in getTrattaById!!");
	}

	public synchronized Tratta getTrattaByValue(Ambiente ambiente, Mezzo mezzo,
			Citta cittaPartenza, Citta cittaArrivo, Via via)
			throws CatalogoException {
		for (Tratta tratta : tratte) {
			if (tratta.contains(ambiente, mezzo, cittaPartenza, cittaArrivo,
					via))
				return tratta;
		}
		// Se la tratta non c'è errore nell'utilizzo
		throw new CatalogoException("Errore in getTrattaByValue!!");
	}

	public synchronized Offerta getOffertaById(Integer idOfferta) throws CatalogoException {
		for (Offerta offerta : offerte) {
			if (offerta.getIdOfferta().equals(idOfferta))
				return offerta;
		}
		// Se l'offerta non c'è errore nell'utilizzo
		throw new CatalogoException("Errore in getOffertaById!!");
	}

	public synchronized Prenotazione getPrenotazioneById(Integer idPrenotazione)
			throws CatalogoException {
		for (Prenotazione prenotazione : prenotazioni) {
			if (prenotazione.getIdPrenotazione().equals(idPrenotazione))
				return prenotazione;
		}
		// Se l'offerta non c'ï¿½ errore nell'utilizzo
		throw new CatalogoException("Errore in getPrenotazioneById!!");
	}

	public synchronized Offerta getOffertaByValue(Integer idTratta, Integer giorno,
			Integer mese, Integer anno, Integer oraPartenza,
			Integer minutiPartenza, Integer oraArrivo, Integer minutiArrivo,
			Integer posti) throws CatalogoException {
		// TODO Auto-generated method stub
		for (Offerta offerta : offerte) {
			if (offerta.contains(idTratta, giorno, mese, anno, oraPartenza,
					minutiPartenza, oraArrivo, minutiArrivo, posti))
				return offerta;
		}
		// Se la tratta non c'ï¿½ errore nell'utilizzo
		throw new CatalogoException("Errore in getOffertaByValue!!");
	}

	/**
	 * Get degli ambienti dalla mappa.
	 * 
	 * @return
	 */
	public synchronized List<Ambiente> getAmbienti() {
		// TODO Auto-generated method stub
		List<Ambiente> listAmbienti = new ArrayList<Ambiente>();
		for (String key : mapCatalogo.keySet()) {
			listAmbienti.add((Ambiente) mapCatalogo.get(key));
		}
		return listAmbienti;
	}

	/**
	 * Get dei mezzi dalla mappa.
	 * 
	 * @param ambienteSelezionato
	 * @return
	 */
	public synchronized List<Mezzo> getMezzi(Ambiente ambiente) {
		// TODO Auto-generated method stub
		String ambienteSelezionato = ambiente.getValore();
		List<Mezzo> listaMezzi = new ArrayList<Mezzo>();
		MapCatalogo<ElementoCatalogo> mapAmbiente = ((Ambiente) mapCatalogo
				.get(ambienteSelezionato)).getMapCatalogo();

		for (String key : mapAmbiente.keySet()) {
			listaMezzi.add((Mezzo) mapAmbiente.get(key));
		}
		return listaMezzi;
	}

	/**
	 * Get delle citta di partenza dalla mappa.
	 * 
	 * @param ambienteselezionato
	 * @param mezzoselezionato
	 * @return
	 */
	public synchronized List<Citta> getCittaPartenza(Ambiente ambiente, Mezzo mezzo) {
		// TODO Auto-generated method stub
		String ambienteSelezionato = ambiente.getValore();
		String mezzoSelezionato = mezzo.getValore();
		List<Citta> listaCittaPartenza = new ArrayList<Citta>();
		MapCatalogo<ElementoCatalogo> mapAmbiente = ((Ambiente) mapCatalogo
				.get(ambienteSelezionato)).getMapCatalogo();
		MapCatalogo<ElementoCatalogo> mapMezzo = ((Mezzo) mapAmbiente
				.get(mezzoSelezionato)).getMapCatalogo();
		for (String key : mapMezzo.keySet()) {
			listaCittaPartenza.add((Citta) mapMezzo.get(key));
		}
		return listaCittaPartenza;
	}

	/**
	 * Get delle citta di arrivo della mappa
	 * 
	 * @param ambiente
	 * @param mezzo
	 * @param cittaPartenza
	 * @return
	 */
	public synchronized List<Citta> getCittaArrivo(Ambiente ambiente, Mezzo mezzo,
			Citta cittaPartenza) {
		// TODO Auto-generated method stub
		String ambienteSelezionato = ambiente.getValore();
		String mezzoSelezionato = mezzo.getValore();
		String cittaPartenzaSelezionata = cittaPartenza.getValore();
		List<Citta> listaCittaArrivo = new ArrayList<Citta>();
		MapCatalogo<ElementoCatalogo> mapAmbiente = ((Ambiente) mapCatalogo
				.get(ambienteSelezionato)).getMapCatalogo();
		MapCatalogo<ElementoCatalogo> mapMezzo = ((Mezzo) mapAmbiente
				.get(mezzoSelezionato)).getMapCatalogo();
		MapCatalogo<ElementoCatalogo> mapCittaPartenza = ((Citta) mapMezzo
				.get(cittaPartenzaSelezionata)).getMapCatalogo();
		for (String key : mapCittaPartenza.keySet()) {
			listaCittaArrivo.add((Citta) mapCittaPartenza.get(key));
		}
		return listaCittaArrivo;
	}

	/**
	 * Get delle vie della mappa.
	 * 
	 * @param ambiente
	 * @param mezzo
	 * @param cittaPartenza
	 * @param cittaArrivo
	 * @return
	 */
	public synchronized List<Via> getVia(Ambiente ambiente, Mezzo mezzo,
			Citta cittaPartenza, Citta cittaArrivo) {
		// TODO Auto-generated method stub
		String ambienteSelezionato = ambiente.getValore();
		String mezzoSelezionato = mezzo.getValore();
		String cittaPartenzaSelezionata = cittaPartenza.getValore();
		String cittaArrivoSelezionata = cittaArrivo.getValore();

		List<Via> listaVia = new ArrayList<Via>();
		MapCatalogo<ElementoCatalogo> mapAmbiente = ((Ambiente) mapCatalogo
				.get(ambienteSelezionato)).getMapCatalogo();
		MapCatalogo<ElementoCatalogo> mapMezzo = ((Mezzo) mapAmbiente
				.get(mezzoSelezionato)).getMapCatalogo();
		MapCatalogo<ElementoCatalogo> mapCittaPartenza = ((Citta) mapMezzo
				.get(cittaPartenzaSelezionata)).getMapCatalogo();
		MapCatalogo<ElementoCatalogo> mapCittaArrivo = ((Citta) mapCittaPartenza
				.get(cittaArrivoSelezionata)).getMapCatalogo();
		for (String key : mapCittaArrivo.keySet()) {
			listaVia.add((Via) mapCittaArrivo.get(key));
		}
		return listaVia;
	}

	/**
	 * Get delle offerte dalla mappa.
	 * 
	 * @param ambiente
	 * @param mezzo
	 * @param cittaPartenza
	 * @param cittaArrivo
	 * @param via
	 * @return
	 */
	public synchronized List<Offerta> getListaOfferte(Ambiente ambiente, Mezzo mezzo,
			Citta cittaPartenza, Citta cittaArrivo, Via via) {
		// TODO Auto-generated method stub
		String ambienteSelezionato = ambiente.getValore();
		String mezzoSelezionato = mezzo.getValore();
		String cittaPartenzaSelezionata = cittaPartenza.getValore();
		String cittaArrivoSelezionata = cittaArrivo.getValore();
		String viaSelezionata = via.getValore();
		List<Offerta> listaOfferta = new ArrayList<Offerta>();
		MapCatalogo<ElementoCatalogo> mapMezzo = ((Ambiente) mapCatalogo
				.get(ambienteSelezionato)).getMapCatalogo();
		MapCatalogo<ElementoCatalogo> mapCittaPartenza = ((Mezzo) mapMezzo
				.get(mezzoSelezionato)).getMapCatalogo();
		MapCatalogo<ElementoCatalogo> mapCittaArrivo = ((Citta) mapCittaPartenza
				.get(cittaPartenzaSelezionata)).getMapCatalogo();
		MapCatalogo<ElementoCatalogo> mapVia = ((Citta) mapCittaArrivo
				.get(cittaArrivoSelezionata)).getMapCatalogo();
		MapOfferta mapOfferta = ((Via) mapVia.get(viaSelezionata))
				.getMapOfferta();
		for (Integer key : mapOfferta.keySet()) {
			listaOfferta.add(mapOfferta.get(key));
		}
		return listaOfferta;
	}

	public synchronized List<Prenotazione> getListaPrenotazioni(String ambienteSelezionato,
			String mezzoSelezionato, String cittaPartenzaSelezionata,
			String cittaArrivoSelezionata, String viaSelezionata,
			Integer idOfferta) {
		// TODO Auto-generated method stub
		List<Prenotazione> listaPrenotazioni = new ArrayList<Prenotazione>();
		MapCatalogo<ElementoCatalogo> mapMezzo = ((Ambiente) mapCatalogo
				.get(ambienteSelezionato)).getMapCatalogo();
		MapCatalogo<ElementoCatalogo> mapCittaPartenza = ((Mezzo) mapMezzo
				.get(mezzoSelezionato)).getMapCatalogo();
		MapCatalogo<ElementoCatalogo> mapCittaArrivo = ((Citta) mapCittaPartenza
				.get(cittaPartenzaSelezionata)).getMapCatalogo();
		MapCatalogo<ElementoCatalogo> mapVia = ((Citta) mapCittaArrivo
				.get(cittaArrivoSelezionata)).getMapCatalogo();
		MapOfferta mapOfferta = ((Via) mapVia.get(viaSelezionata))
				.getMapOfferta();
		Offerta offerta = mapOfferta.get(idOfferta);
		MapPrenotazioni mapPrenotazioni = offerta.getMapPrenotazioni();

		for (Integer key : mapPrenotazioni.keySet()) {
			listaPrenotazioni.add(mapPrenotazioni.get(key));
		}
		return listaPrenotazioni;
	}

	public synchronized List<Prenotazione> getListaPrenotazioniByidOfferta(Integer idOfferta) {
		List<Prenotazione> listaPrenotazioni = new ArrayList<Prenotazione>();
		for (Prenotazione prenotazione : prenotazioni) {
			if (prenotazione.getIdOfferta().equals(idOfferta))
				listaPrenotazioni.add(prenotazione);
		}
		return listaPrenotazioni;
	}

	/**
	 * Metodo di inserimento di un elemento nell'offerta.
	 * 
	 * @param tratta
	 * @param offerta
	 * @throws DAOException
	 */
	public synchronized void inserimentoInOfferta(Tratta tratta, Offerta offerta)
			throws DAOException {
		// TODO Auto-generated method stub

		offerte.add(offerta);
		// Salvataggio dell'offerta sul database.
		offerta.save();
		// Salvataggio dell'offerta nella mappa
		inserimentoInMapOfferta(tratta, offerta);
	}

	/**
	 * Metodo di rimozione di un elemento dall'offerta
	 * 
	 * @param tratta
	 * @param offerta
	 * @throws DAOException
	 */
	public synchronized void rimozioneInOfferta(Tratta tratta, Offerta offerta)
			throws DAOException {
		// TODO Auto-generated method stub

		offerte.remove(offerta);
		// Rimozione dell'offerta sul database
		offerta.delete();
		// Rimozione dell'offerta dalla mappa
		deleteInMapOfferta(tratta, offerta);

	}

	/**
	 * Metodo di inserimento di un elemento nelle prenotazioni
	 * 
	 * @param tratta
	 * @param offerta
	 * @param prenotazione
	 * @throws DAOException
	 */
	public synchronized void inserimentoInPrenotazione(Tratta tratta, Offerta offerta,
			Prenotazione prenotazione) throws DAOException {
		// TODO Auto-generated method stub

		// Inserimento della prenotazione nella lista locale
		prenotazioni.add(prenotazione);
		// Inserimento prenotazione sul database
		prenotazione.save();
		// Inserimento della prenotazione nella mappa
		inserimentoInMapPrenotazione(tratta, offerta, prenotazione);
	}

	/**
	 * Metodo di rimozione di un elemento dalle prenotazioni
	 * 
	 * @param tratta
	 * @param offerta
	 * @param prenotazione
	 * @throws DAOException
	 * @throws MapException
	 */
	public synchronized void rimozioneInPrenotazione(Tratta tratta, Offerta offerta,
			Prenotazione prenotazione) throws DAOException, MapException {
		// Rimozione della prenotazione dalla lista locale
		prenotazioni.remove(prenotazione);
		// Rimozione della prenotazione dal database
		prenotazione.delete();
		// Rimozione della prenotazione dalla mappa
		deleteInMapPrenotazione(tratta, offerta, prenotazione);
	}

	/**
	 * Metodo di creazione della mappa associata al catalogo.
	 * 
	 * @throws MapException
	 * @throws OraException
	 * @throws DataException
	 * @throws SQLException
	 * @throws CatalogoException
	 * @throws DAOException
	 */
	private synchronized void createMap() throws MapException, SQLException, DataException,
			OraException, CatalogoException, DAOException {

		mapCatalogo = new MapCatalogo<ElementoCatalogo>();

		// Caricamento delle tratte nella mappa
		for (Tratta tratta : tratte)
			inserimentoInMapTratte(tratta);

		// Caricamento delle offerte nella mappa
		DAOOfferta daoOfferta = DAOOfferta.getInstance();
		offerte = Collections.synchronizedList(daoOfferta.getListaOfferta());
		for (Offerta offerta : offerte) {
			inserimentoInMapOfferta(getTrattaById(offerta.getIdTratta()),
					offerta);
		}

		// Caricamento delle prenotazioni nella mappa
		DAOPrenotazione daoPrenotazione = DAOPrenotazione.getInstance();
		prenotazioni = Collections.synchronizedList(daoPrenotazione.getListaPrenotazioni());
		for (Prenotazione prenotazione : prenotazioni) {
			Offerta offertaRelativa = getOffertaById(prenotazione
					.getIdOfferta());
			Tratta trattaRelativa = getTrattaById(offertaRelativa.getIdTratta());
			// Caricamento dei biglietti relativi alla prenotazione
			DAOBiglietto daoBiglietto = DAOBiglietto.getInstance();
			List<Biglietto> listaBiglietti = daoBiglietto
					.getListaBigliettiByIdPrenotazione(prenotazione
							.getIdPrenotazione());
			prenotazione.setListaBiglietti(listaBiglietti);
			inserimentoInMapPrenotazione(trattaRelativa, offertaRelativa,
					prenotazione);
		}
	}

	/**
	 * Inserimento di un elemento nella mappa.
	 * 
	 * @param tratta
	 * @throws MapException
	 */
	public synchronized void inserimentoInMapTratte(Tratta tratta) throws MapException {
		// Data la tratta la voglio inserire nel Catalogo.

		// Inserisco l'ambiente nella mappa
		Ambiente ambiente = tratta.getAmbiente();
		if (!mapCatalogo.verificaEsistenza(ambiente.getValore())) {
			mapCatalogo
					.insertElementoIntermedio(ambiente.getValore(), ambiente);
		}
		// Estrai l'oggetto inserito nella mappa. Potrebbe non essere quello di
		// prima se giï¿½ presente.
		ambiente = (Ambiente) mapCatalogo.getElementoIntermedio(ambiente
				.getValore());

		// Inserisco il mezzo nella mappa
		Mezzo mezzo = tratta.getMezzo();
		if (!ambiente.getMapCatalogo().verificaEsistenza(mezzo.getValore())) {
			ambiente.getMapCatalogo().insertElementoIntermedio(
					mezzo.getValore(), mezzo);
		}
		// Estrai l'oggetto inserito nella mappa. Potrebbe non essere quello di
		// prima se giï¿½ presente.
		mezzo = (Mezzo) ambiente.getMapCatalogo().getElementoIntermedio(
				mezzo.getValore());

		// Inserisco la citta di partenza nella mappa
		Citta cittaPartenza = tratta.getCittaPartenza();
		if (!mezzo.getMapCatalogo()
				.verificaEsistenza(cittaPartenza.getValore())) {
			mezzo.getMapCatalogo().insertElementoIntermedio(
					cittaPartenza.getValore(), cittaPartenza);
		}
		// Estrai l'oggetto inserito nella mappa. Potrebbe non essere quello di
		// prima se giï¿½ presente.
		cittaPartenza = (Citta) mezzo.getMapCatalogo().getElementoIntermedio(
				cittaPartenza.getValore());

		// Inserisco la citta di arrivo nella mappa
		Citta cittaArrivo = tratta.getCittaArrivo();
		if (!cittaPartenza.getMapCatalogo().verificaEsistenza(
				cittaArrivo.getValore())) {
			cittaPartenza.getMapCatalogo().insertElementoIntermedio(
					cittaArrivo.getValore(), cittaArrivo);
		}

		// Estrai l'oggetto inserito nella mappa. Potrebbe non essere quello di
		// prima se giï¿½ presente.
		cittaArrivo = (Citta) cittaPartenza.getMapCatalogo()
				.getElementoIntermedio(cittaArrivo.getValore());

		// Inserisco la via nella mappa
		Via via = tratta.getVia();
		if (!cittaArrivo.getMapCatalogo().verificaEsistenza(via.getValore())) {
			cittaArrivo.getMapCatalogo().insertElementoFinale(via.getValore(),
					via);
		}

	}

	/**
	 * Inserimento di un offerta della mappa
	 * 
	 * @param trattaById
	 * @param offerta
	 */
	public synchronized void inserimentoInMapOfferta(Tratta tratta, Offerta offerta) {
		// TODO Auto-generated method stub
		// Estrazione mapAmbiente l'ambiente nella mappa
		Ambiente ambiente = (Ambiente) mapCatalogo.getElementoIntermedio(tratta
				.getAmbiente().getValore());
		Mezzo mezzo = (Mezzo) ambiente.getMapCatalogo().getElementoIntermedio(
				tratta.getMezzo().getValore());
		Citta cittaPartenza = (Citta) mezzo.getMapCatalogo()
				.getElementoIntermedio(tratta.getCittaPartenza().getValore());
		Citta cittaArrivo = (Citta) cittaPartenza.getMapCatalogo()
				.getElementoIntermedio(tratta.getCittaArrivo().getValore());
		Via via = (Via) cittaArrivo.getMapCatalogo().getElementoFinale(
				tratta.getVia().getValore());
		MapOfferta mapOfferta = via.getMapOfferta();
		// Inserimento dell'offerta nella tratta associata
		mapOfferta.insertRecord(offerta.getIdOfferta(), offerta);

	}

	/**
	 * Inserimento di una prenotazione nella mappa
	 * 
	 * @param tratta
	 * @param offerta
	 * @param prenotazione
	 */
	public synchronized void inserimentoInMapPrenotazione(Tratta tratta,
			Offerta offertaSelezionata, Prenotazione prenotazione) {
		// TODO Auto-generated method stub
		// Estrazione degli elementi dalla mappa.
		Ambiente ambiente = (Ambiente) mapCatalogo.getElementoIntermedio(tratta
				.getAmbiente().getValore());
		Mezzo mezzo = (Mezzo) ambiente.getMapCatalogo().getElementoIntermedio(
				tratta.getMezzo().getValore());
		Citta cittaPartenza = (Citta) mezzo.getMapCatalogo()
				.getElementoIntermedio(tratta.getCittaPartenza().getValore());
		Citta cittaArrivo = (Citta) cittaPartenza.getMapCatalogo()
				.getElementoIntermedio(tratta.getCittaArrivo().getValore());
		Via via = (Via) cittaArrivo.getMapCatalogo().getElementoFinale(
				tratta.getVia().getValore());
		Offerta offerta = (via.getMapOfferta()).get(offertaSelezionata
				.getIdOfferta());
		MapPrenotazioni mapPrenotazioni = offerta.getMapPrenotazioni();

		// Inserimento della prenotazione nella mappa
		mapPrenotazioni.insertRecord(prenotazione.getIdPrenotazione(),
				prenotazione);
	}

	/**
	 * Rimozione di un elemento dalla mappa.
	 * 
	 * @param tratta
	 * @throws MapException
	 */
	public synchronized void deleteInMapTratte(Tratta tratta) throws MapException {
		// TODO Auto-generated method stub
		Ambiente ambiente = (Ambiente) mapCatalogo.getElementoIntermedio(tratta
				.getAmbiente().getValore());
		Mezzo mezzo = (Mezzo) ambiente.getMapCatalogo().getElementoIntermedio(
				tratta.getMezzo().getValore());
		Citta cittaPartenza = (Citta) mezzo.getMapCatalogo()
				.getElementoIntermedio(tratta.getCittaPartenza().getValore());
		Citta cittaArrivo = (Citta) cittaPartenza.getMapCatalogo()
				.getElementoIntermedio(tratta.getCittaArrivo().getValore());
		Via via = (Via) cittaArrivo.getMapCatalogo().getElementoFinale(
				tratta.getVia().getValore());

		// Rimozione della via. Tratta cancellata.
		cittaArrivo.getMapCatalogo().removeRecord(via.getValore());
		// Cancellazione a cascata.
		if (cittaArrivo.getMapCatalogo().isEmpty()) {
			cittaPartenza.getMapCatalogo()
					.removeRecord(cittaArrivo.getValore());
			if (cittaPartenza.getMapCatalogo().isEmpty()) {
				mezzo.getMapCatalogo().removeRecord(cittaPartenza.getValore());
				if (mezzo.getMapCatalogo().isEmpty()) {
					ambiente.getMapCatalogo().removeRecord(mezzo.getValore());
					if (ambiente.getMapCatalogo().isEmpty()) {
						mapCatalogo.removeRecord(ambiente.getValore());
					}
				}
			}
		}

	}

	private synchronized void deleteInMapOfferta(Tratta tratta, Offerta offerta) {
		// TODO Auto-generated method stub
		// Estrazione mappe
		Ambiente ambiente = (Ambiente) mapCatalogo.getElementoIntermedio(tratta
				.getAmbiente().getValore());
		Mezzo mezzo = (Mezzo) ambiente.getMapCatalogo().getElementoIntermedio(
				tratta.getMezzo().getValore());
		Citta cittaPartenza = (Citta) mezzo.getMapCatalogo()
				.getElementoIntermedio(tratta.getCittaPartenza().getValore());
		Citta cittaArrivo = (Citta) cittaPartenza.getMapCatalogo()
				.getElementoIntermedio(tratta.getCittaArrivo().getValore());
		Via via = (Via) cittaArrivo.getMapCatalogo().getElementoFinale(
				tratta.getVia().getValore());
		MapOfferta mapOfferta = via.getMapOfferta();
		// Inserimento dell'offerta nella tratta associata
		mapOfferta.remove(offerta.getIdOfferta());

	}

	private synchronized void deleteInMapPrenotazione(Tratta tratta,
			Offerta offertaSelezionata, Prenotazione prenotazione)
			throws MapException {
		// TODO Auto-generated method stub
		Ambiente ambiente = (Ambiente) mapCatalogo.getElementoIntermedio(tratta
				.getAmbiente().getValore());
		Mezzo mezzo = (Mezzo) ambiente.getMapCatalogo().getElementoIntermedio(
				tratta.getMezzo().getValore());
		Citta cittaPartenza = (Citta) mezzo.getMapCatalogo()
				.getElementoIntermedio(tratta.getCittaPartenza().getValore());
		Citta cittaArrivo = (Citta) cittaPartenza.getMapCatalogo()
				.getElementoIntermedio(tratta.getCittaArrivo().getValore());
		Via via = (Via) cittaArrivo.getMapCatalogo().getElementoFinale(
				tratta.getVia().getValore());
		Offerta offerta = (via.getMapOfferta()).get(offertaSelezionata
				.getIdOfferta());
		MapPrenotazioni mapPrenotazioni = offerta.getMapPrenotazioni();

		// Rimozione della prenotazione dalla mappa
		mapPrenotazioni.removeRecord(prenotazione.getIdPrenotazione());

	}
}