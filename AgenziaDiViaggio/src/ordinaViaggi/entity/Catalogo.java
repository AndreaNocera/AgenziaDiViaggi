/**
 * 
 */
package ordinaViaggi.entity;

import ordinaViaggi.dao.DAOCatalogo;
import ordinaViaggi.dao.DAOOfferta;
import ordinaViaggi.exception.CatalogoException;
import ordinaViaggi.exception.DAOException;
import ordinaViaggi.exception.DataException;
import ordinaViaggi.exception.MapException;
import ordinaViaggi.exception.OraException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
 * 
 * @author Gambella
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
	private List<Tratta> tratte;
	private List<Offerta> offerte;
	private static MapCatalogo<ElementoCatalogo> mapCatalogo;

	private Catalogo() throws DAOException, MapException, SQLException,
			DataException, OraException, CatalogoException {
		/*
		 * Fetch del catalogo dal database.
		 */
		DAOCatalogo daoCatalogo = DAOCatalogo.getIstance();
		// Creazione della lista
		tratte = daoCatalogo.getCatalogo();
		// Creazione della mapCatalogo, con fetch iniziale dal DB.
		createMap();
	}

	public static Catalogo getIstance() throws DAOException, MapException,
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
	public void inserimentoInCatalogo(Tratta tratta) throws MapException,
			DAOException {
		// Inserimento nella lista delle tratte.
		tratte.add(tratta);
		// Inserimento nel Database
		tratta.save();
		// Inserimento nella mappa.
		inserimentoInMap(tratta);
	}

	/**
	 * Rimozione di un elemento dal catalogo
	 * 
	 * @param listaCatalogo
	 * @throws DAOException
	 * @throws MapException
	 */

	public void rimozioneInCatalogo(Tratta tratta) throws CatalogoException,
			DAOException, MapException {
		// Rimozione della tratta nella lista
		tratte.remove(tratta);
		// Rimozione della tratta sul DB
		tratta.delete();
		// Rimozione della tratta nella mappa
		deleteInMap(tratta);
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
		for (Offerta offerta : offerte)
			offerta.print();
	}

	public Tratta getTrattaById(Integer idTratta) throws CatalogoException {
		for (Tratta tratta : tratte) {
			if (tratta.getId().equals(idTratta))
				return tratta;
		}
		// Se la tratta non c'è errore nell'utilizzo
		throw new CatalogoException("Errore in getTrattaById!!");
	}

	public Tratta getTrattaByValue(Ambiente ambiente, Mezzo mezzo,
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

	public Offerta getOffertaByValue(Integer idTratta, Integer giorno,
			Integer mese, Integer anno, Integer ora, Integer minuti,
			Integer posti) throws CatalogoException {
		// TODO Auto-generated method stub
		for (Offerta offerta : offerte) {
			if (offerta.contains(idTratta, giorno, mese, anno, ora, minuti,
					posti))
				return offerta;
		}
		// Se la tratta non c'è errore nell'utilizzo
		throw new CatalogoException("Errore in getOffertaByValue!!");
	}

	/**
	 * Get degli ambienti dalla mappa.
	 * 
	 * @return
	 */
	public List<Ambiente> getAmbienti() {
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
	public List<Mezzo> getMezzi(String ambienteSelezionato) {
		// TODO Auto-generated method stub
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
	public List<Citta> getCittaPartenza(String ambienteSelezionato,
			String mezzoSelezionato) {
		// TODO Auto-generated method stub
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
	public List<Citta> getCittaArrivo(String ambienteSelezionato,
			String mezzoSelezionato, String cittaPartenzaSelezionata) {
		// TODO Auto-generated method stub
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
	public List<Via> getVia(String ambienteSelezionato,
			String mezzoSelezionato, String cittaPartenzaSelezionata,
			String cittaArrivoSelezionata) {
		// TODO Auto-generated method stub
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
	public List<Offerta> getListaOfferte(String ambienteSelezionato,
			String mezzoSelezionato, String cittaPartenzaSelezionata,
			String cittaArrivoSelezionata, String viaSelezionata) {
		// TODO Auto-generated method stub
		List<Offerta> listaOfferta = new ArrayList<Offerta>();
		MapCatalogo<ElementoCatalogo> mapAmbiente = ((Ambiente) mapCatalogo
				.get(ambienteSelezionato)).getMapCatalogo();
		MapCatalogo<ElementoCatalogo> mapMezzo = ((Mezzo) mapAmbiente
				.get(mezzoSelezionato)).getMapCatalogo();
		MapCatalogo<ElementoCatalogo> mapCittaPartenza = ((Citta) mapMezzo
				.get(cittaPartenzaSelezionata)).getMapCatalogo();
		MapCatalogo<ElementoCatalogo> mapCittaArrivo = ((Citta) mapCittaPartenza
				.get(cittaArrivoSelezionata)).getMapCatalogo();
		MapOfferta mapVia = ((Via) mapCittaArrivo.get(viaSelezionata))
				.getMapOfferta();
		for (Integer key : mapVia.keySet()) {
			listaOfferta.add(mapVia.get(key));
		}
		return listaOfferta;
	}

	/**
	 * Metodo di inserimento di un elemento nell'offerta.
	 * 
	 * @param tratta
	 * @param offerta
	 * @throws DAOException
	 */
	public void inserimentoInOfferta(Tratta tratta, Offerta offerta)
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
	public void rimozioneInOfferta(Tratta tratta, Offerta offerta)
			throws DAOException {
		// TODO Auto-generated method stub

		/*
		 * if(catalogo.verificaEsistenzaPrenotazioni(offerta)) throw new
		 * GestoreEccezioniException();
		 */

		offerte.remove(offerta);
		// Rimozione dell'offerta sul database
		offerta.delete();
		// Rimozione dell'offerta dalla mappa
		deleteInMapOfferta(tratta, offerta);

	}

	/**
	 * Metodo di creazione della mappa associata al catalogo.
	 * 
	 * @throws MapException
	 * @throws OraException
	 * @throws DataException
	 * @throws SQLException
	 * @throws CatalogoException
	 */
	private void createMap() throws MapException, SQLException, DataException,
			OraException, CatalogoException {

		mapCatalogo = new MapCatalogo<ElementoCatalogo>();

		for (Tratta tratta : tratte)
			inserimentoInMap(tratta);
		DAOOfferta daoOfferta = DAOOfferta.getIstance();
		offerte = daoOfferta.getListaOfferta();
		for (Offerta offerta : offerte) {
			inserimentoInMapOfferta(getTrattaById(offerta.getIdTratta()),
					offerta);
		}
		System.out.println("Caricamento iniziale della mappa completato!!");

	}

	/**
	 * Inserimento di un elemento nella mappa.
	 * 
	 * @param tratta
	 * @throws MapException
	 */
	private void inserimentoInMap(Tratta tratta) throws MapException {
		// Data la tratta la voglio inserire nel Catalogo.

		// Inserisco l'ambiente nella mappa
		Ambiente ambiente = tratta.getAmbiente();

		mapCatalogo.insertElementoIntermedio(ambiente.getValore(), ambiente);
		// Estrai l'oggetto inserito nella mappa. Potrebbe non essere quello di
		// prima se già presente.
		ambiente = (Ambiente) mapCatalogo.getElementoIntermedio(ambiente
				.getValore());

		// Inserisco il mezzo nella mappa
		Mezzo mezzo = tratta.getMezzo();
		ambiente.getMapCatalogo().insertElementoIntermedio(mezzo.getValore(),
				mezzo);
		// Estrai l'oggetto inserito nella mappa. Potrebbe non essere quello di
		// prima se già presente.
		mezzo = (Mezzo) ambiente.getMapCatalogo().getElementoIntermedio(
				mezzo.getValore());

		// Inserisco la citta di partenza nella mappa
		Citta cittaPartenza = tratta.getCittaPartenza();
		mezzo.getMapCatalogo().insertElementoIntermedio(
				cittaPartenza.getValore(), cittaPartenza);
		// Estrai l'oggetto inserito nella mappa. Potrebbe non essere quello di
		// prima se già presente.
		cittaPartenza = (Citta) mezzo.getMapCatalogo().getElementoIntermedio(
				cittaPartenza.getValore());

		// Inserisco la citta di arrivo nella mappa
		Citta cittaArrivo = tratta.getCittaArrivo();
		cittaPartenza.getMapCatalogo().insertElementoIntermedio(
				cittaArrivo.getValore(), cittaArrivo);
		// Estrai l'oggetto inserito nella mappa. Potrebbe non essere quello di
		// prima se già presente.
		cittaArrivo = (Citta) cittaPartenza.getMapCatalogo()
				.getElementoIntermedio(cittaArrivo.getValore());

		// Inserisco la via nella mappa
		Via via = tratta.getVia();
		cittaArrivo.getMapCatalogo().insertElementoFinale(via.getValore(), via);

	}

	/**
	 * Inserimento di un offerta della mappa
	 * 
	 * @param trattaById
	 * @param offerta
	 */
	private void inserimentoInMapOfferta(Tratta tratta, Offerta offerta) {
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
	 * Rimozione di un elemento dalla mappa.
	 * 
	 * @param tratta
	 * @throws MapException
	 */
	private void deleteInMap(Tratta tratta) throws MapException {
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

		System.out.println("Oggetto da cancellare: ");
		System.out.println("Id: " + via.getId() + " valore : "
				+ via.getValore());

	}

	private void deleteInMapOfferta(Tratta tratta, Offerta offerta) {
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
		mapOfferta.remove(offerta.getIdOfferta());

	}

	/**
	 * Stampa del catalogo sotto forma di mappa.
	 * 
	 * @throws MapException
	 */
	public void printMapCatalogo() throws MapException {
		System.out.println("Stampa mappa.");
		// Stampa Ambienti e mezzi
		Set<String> collection = mapCatalogo.keySet();
		for (String ambiente : collection) {
			System.out.println(ambiente);
			MapCatalogo<ElementoCatalogo> mappaMezzi = ((ElementoIntermedio) mapCatalogo
					.get(ambiente)).getMapCatalogo();
			for (String mezzo : mappaMezzi.keySet()) {
				System.out.println("  " + mezzo);
				MapCatalogo<ElementoCatalogo> mappaCittaPartenza = ((ElementoIntermedio) mappaMezzi
						.get(mezzo)).getMapCatalogo();
				for (String cittaPartenza : mappaCittaPartenza.keySet()) {
					System.out.println("    " + cittaPartenza);
					MapCatalogo<ElementoCatalogo> mappaCittaArrivo = ((ElementoIntermedio) mappaCittaPartenza
							.get(cittaPartenza)).getMapCatalogo();
					for (String cittaArrivo : mappaCittaArrivo.keySet()) {
						System.out.println("      " + cittaArrivo);
						MapCatalogo<ElementoCatalogo> mappaVia = ((ElementoIntermedio) mappaCittaArrivo
								.get(cittaArrivo)).getMapCatalogo();
						for (String via : mappaVia.keySet()) {
							System.out.println("        " + via);
						}
					}
				}
			}
		}
	}

	public void printMapOfferta() throws MapException {
		System.out.println("Stampa mappa offerta.");
		// Stampa Ambienti e mezzi
		Set<String> collection = mapCatalogo.keySet();
		for (String ambiente : collection) {
			System.out.println(ambiente);
			MapCatalogo<ElementoCatalogo> mappaMezzi = ((ElementoIntermedio) mapCatalogo
					.get(ambiente)).getMapCatalogo();
			for (String mezzo : mappaMezzi.keySet()) {
				System.out.println("  " + mezzo);
				MapCatalogo<ElementoCatalogo> mappaCittaPartenza = ((ElementoIntermedio) mappaMezzi
						.get(mezzo)).getMapCatalogo();
				for (String cittaPartenza : mappaCittaPartenza.keySet()) {
					System.out.println("    " + cittaPartenza);
					MapCatalogo<ElementoCatalogo> mappaCittaArrivo = ((ElementoIntermedio) mappaCittaPartenza
							.get(cittaPartenza)).getMapCatalogo();
					for (String cittaArrivo : mappaCittaArrivo.keySet()) {
						System.out.println("      " + cittaArrivo);
						MapCatalogo<ElementoCatalogo> mappaVia = ((ElementoIntermedio) mappaCittaArrivo
								.get(cittaArrivo)).getMapCatalogo();
						for (String via : mappaVia.keySet()) {
							System.out.println("        " + via);
							MapOfferta mappaOfferta = ((ElementoFinale) mappaVia
									.get(via)).getMapOfferta();
							for (Integer idOfferta : mappaOfferta.keySet()) {
								mappaOfferta.get(idOfferta).print();
							}
						}
					}
				}
			}
		}
	}

}