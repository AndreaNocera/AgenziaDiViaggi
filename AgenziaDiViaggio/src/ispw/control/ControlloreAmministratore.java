package ispw.control;

import ispw.entity.Ambiente;
import ispw.entity.Catalogo;
import ispw.entity.Citta;
import ispw.entity.Data;
import ispw.entity.Mezzo;
import ispw.entity.Offerta;
import ispw.entity.Ora;
import ispw.entity.Prenotazione;
import ispw.entity.Utente;
import ispw.entity.Via;
import ispw.exception.CatalogoException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.MapException;
import ispw.exception.OraException;
import ispw.exception.UtenteException;
import ispw.indici.CalcoloIndici;
import ispw.indici.Indice;
import ispw.log.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Gambella Riccardo Controllore Progettista.
 */

public class ControlloreAmministratore extends Controllore {
	private static ControlloreAmministratore instance = null;
	private static Catalogo catalogo = null;

	private ControlloreAmministratore() throws DAOException, MapException,
			SQLException, DataException, OraException, CatalogoException {
		super();
		catalogo = Catalogo.getInstance();

	}

	public synchronized static ControlloreAmministratore getInstance() throws DAOException,
			MapException, SQLException, DataException, OraException,
			CatalogoException {
		if (instance == null)
			instance = new ControlloreAmministratore();
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
	public synchronized List<String> estrazioneAmbienti() throws DAOException, MapException,
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
	public synchronized List<String> estrazioneMezzi(String ambiente) throws DAOException,
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
	public synchronized List<String> estrazioneCittaPartenza(String ambiente, String mezzo)
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
	public synchronized List<String> estrazioneCittaArrivo(String ambiente, String mezzo,
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
	public synchronized List<String> estrazioneVia(String ambiente, String mezzo,
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
	 * @return
	 * @throws DAOException
	 * @throws MapException
	 * @throws CatalogoException
	 * @throws OraException
	 * @throws DataException
	 * @throws SQLException
	 */
	public synchronized List<String> visualizzaOfferta(List<String> listaCatalogo)
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

	public synchronized List<String> visualizzaOffertaByData(List<String> listaCatalogo,
			Integer giorno, Integer mese, Integer anno) throws DAOException {
		List<Offerta> listaOfferta = catalogo.getListaOfferte(
				Ambiente.getObjectByValue(listaCatalogo.get(0)),
				Mezzo.getObjectByValue(listaCatalogo.get(1)),
				Citta.getObjectByValue(listaCatalogo.get(2)),
				Citta.getObjectByValue(listaCatalogo.get(3)),
				Via.getObjectByValue(listaCatalogo.get(4)));
		List<String> lista = new ArrayList<String>();
		for (Offerta offerta : listaOfferta) {
			if ((offerta.getData()).contains(giorno, mese, anno)) {
				lista.add(offerta.getString());
			}
		}
		return lista;
	}

	public synchronized List<String> visualizzaPrenotazioni(List<String> listaCatalogo,
			Integer idOfferta) {
		List<Prenotazione> listaPrenotazioni = catalogo.getListaPrenotazioni(
				listaCatalogo.get(0), listaCatalogo.get(1),
				listaCatalogo.get(2), listaCatalogo.get(3),
				listaCatalogo.get(4), idOfferta);
		List<String> prenotazioni = new ArrayList<String>();
		for (Prenotazione prenotazione : listaPrenotazioni) {
			prenotazioni.add(prenotazione.getString());
		}
		return prenotazioni;
	}

	public synchronized boolean verificaId(String offertaInserita) {
		// TODO Auto-generated method stub
		if (offertaInserita.equals(""))
			return false;
		return true;
	}

	public synchronized boolean verificaDatiViaggiatore(String nome, String cognome,
			String email) {
		if (nome.equals("") || cognome.equals("") || email.equals(""))
			return false;
		return true;
	}

	public synchronized boolean verificaDatiUtente(String username, String password,
			String nome, String cognome, String ruolo) {
		// TODO Auto-generated method stub
		if (username.equals("") || password.equals("") || nome.equals("")
				|| cognome.equals("") || ruolo.equals("--"))
			return false;
		return true;
	}

	public synchronized void inserimentoUtente(String username, String password,
			String nome, String cognome, String ruolo) throws DAOException {
		Utente utente = new Utente(username, password, nome, cognome, ruolo);
		utente.save();
		Log log = Log.getInstance();
		log.ScriviLog("Amministratore", "Aggiunto utente " + username);
	}

	public synchronized List<String> visualizzaUtenti() throws DAOException {
		// TODO Auto-generated method stub
		List<String> lista = new ArrayList<String>();
		List<Utente> listaUtenti = Utente.getListaUtenti();
		for (Utente utente : listaUtenti) {
			lista.add(utente.getString());
		}
		return lista;
	}

	public synchronized boolean verificaUsernameUtente(String username) {
		// TODO Auto-generated method stub
		if (username.equals(""))
			return false;
		return true;
	}

	public synchronized void rimozioneUtente(String username) throws DAOException,
			SQLException, UtenteException {
		// TODO Auto-generated method stub
		Utente utente = Utente.getUtenteByUsername(username);
		utente.delete();
		Log log = Log.getInstance();
		log.ScriviLog("Amministratore", "Rimosso utente " + username);
	}

	public synchronized void calcolaIndiciTuttiViaggi() throws DataException, OraException,
			DAOException {
		// TODO Auto-generated method stub

		// Istanza di CalcoloIndici
		CalcoloIndici calcoloIndici = CalcoloIndici.getIstance();
		// Get del time corrente
		Calendar calendar = Calendar.getInstance();
		Data data = new Data(new Integer(calendar.get(Calendar.DAY_OF_MONTH)),
				new Integer(calendar.get(Calendar.MONTH) + 1));
		Ora ora = new Ora(new Integer(calendar.get(Calendar.HOUR)),
				new Integer(calendar.get(Calendar.MINUTE)));

		/* Calcolo degli indici */
		List<Ambiente> listaAmbienti = catalogo.getAmbienti();
		for (Ambiente ambiente : listaAmbienti) {
			List<Mezzo> listaMezzi = catalogo.getMezzi(ambiente);
			for (Mezzo mezzo : listaMezzi) {

				// Calcola indice mezzo/ambiente e salvataggio nel Database.
				double indiceCalcolato = calcoloIndici.computaIndiciMetodoA(
						ambiente.getId(), mezzo.getId(), null, null);
				if (indiceCalcolato != 0) {
					Indice indice = new Indice("TuttiViaggi",
							ambiente.getValore(), mezzo.getValore(),
							new Double(indiceCalcolato), data, ora);
					indice.save();
				}
				List<Citta> listaCittaPartenza = catalogo.getCittaPartenza(
						ambiente, mezzo);
				for (Citta cittaPartenza : listaCittaPartenza) {
					List<Citta> listaCittaArrivo = catalogo.getCittaArrivo(
							ambiente, mezzo, cittaPartenza);
					for (Citta cittaArrivo : listaCittaArrivo) {
						// Calcola indice tratta/mezzo e salvataggio nel
						// database
						indiceCalcolato = calcoloIndici.computaIndiciMetodoA(
								ambiente.getId(), mezzo.getId(),
								cittaPartenza.getId(), cittaArrivo.getId());

						// Indice calcolato è 0 se non ci sono prenotazioni
						// relative alla tratta.
						if (indiceCalcolato != 0) {
							Indice indice = new Indice("TuttiViaggi",
									mezzo.getValore(),
									cittaPartenza.getValore() + ","
											+ cittaArrivo.getValore(),
									new Double(indiceCalcolato), data, ora);
							indice.save();
						}
					}
				}
			}
		}
	}

	public synchronized void calcolaIndiciUltimoAnno() throws DataException, OraException,
			DAOException {
		// TODO Auto-generated method stub

		// Istanza di CalcoloIndici
		CalcoloIndici calcoloIndici = CalcoloIndici.getIstance();
		// Get del time corrente
		Calendar calendar = Calendar.getInstance();
		Data data = new Data(new Integer(calendar.get(Calendar.DAY_OF_MONTH)),
				new Integer(calendar.get(Calendar.MONTH) + 1));
		Ora ora = new Ora(new Integer(calendar.get(Calendar.HOUR)),
				new Integer(calendar.get(Calendar.MINUTE)));

		/* Calcolo degli indici */
		List<Ambiente> listaAmbienti = catalogo.getAmbienti();
		for (Ambiente ambiente : listaAmbienti) {
			List<Mezzo> listaMezzi = catalogo.getMezzi(ambiente);
			for (Mezzo mezzo : listaMezzi) {

				// Calcola indice mezzo/ambiente e salvataggio nel Database.
				double indiceCalcolato = calcoloIndici.computaIndiciMetodoB(
						ambiente.getId(), mezzo.getId(), null, null);
				if (indiceCalcolato != 0) {
					Indice indice = new Indice("UltimoAnno",
							ambiente.getValore(), mezzo.getValore(),
							new Double(indiceCalcolato), data, ora);
					indice.save();
				}

				List<Citta> listaCittaPartenza = catalogo.getCittaPartenza(
						ambiente, mezzo);
				for (Citta cittaPartenza : listaCittaPartenza) {
					List<Citta> listaCittaArrivo = catalogo.getCittaArrivo(
							ambiente, mezzo, cittaPartenza);
					for (Citta cittaArrivo : listaCittaArrivo) {
						// Calcola indice tratta/mezzo e salvataggio nel
						// database
						indiceCalcolato = calcoloIndici.computaIndiciMetodoB(
								ambiente.getId(), mezzo.getId(),
								cittaPartenza.getId(), cittaArrivo.getId());
						if (indiceCalcolato != 0) {
							Indice indice = new Indice("UltimoAnno",
									mezzo.getValore(),
									cittaPartenza.getValore() + ","
											+ cittaArrivo.getValore(),
									new Double(indiceCalcolato), data, ora);
							indice.save();
						}
					}
				}
			}
		}
	}
}
