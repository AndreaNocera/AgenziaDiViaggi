package ordinaViaggi.control;

import ordinaViaggi.entity.Ambiente;
import ordinaViaggi.entity.Catalogo;
import ordinaViaggi.entity.Citta;
import ordinaViaggi.entity.Mezzo;
import ordinaViaggi.entity.Offerta;
import ordinaViaggi.entity.Prenotazione;
import ordinaViaggi.entity.Utente;
import ordinaViaggi.entity.Via;
import ordinaViaggi.exception.CatalogoException;
import ordinaViaggi.exception.DAOException;
import ordinaViaggi.exception.DataException;
import ordinaViaggi.exception.MapException;
import ordinaViaggi.exception.OraException;
import ordinaViaggi.exception.UtenteException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gambella Riccardo Controllore Progettista.
 */

public class ControlloreAmministratore extends Controllore {
	private static ControlloreAmministratore istance = null;
	private static Catalogo catalogo = null;

	public ControlloreAmministratore() throws DAOException, MapException,
			SQLException, DataException, OraException, CatalogoException {
		super();
		catalogo = Catalogo.getIstance();

	}

	public static ControlloreAmministratore getIstance() throws DAOException,
			MapException, SQLException, DataException, OraException,
			CatalogoException {
		if (istance == null)
			istance = new ControlloreAmministratore();
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
	public List<String> visualizzaOfferta(List<String> listaCatalogo) {
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

	public List<String> visualizzaOffertaByData(List<String> listaCatalogo,
			Integer giorno, Integer mese, Integer anno) {
		List<Offerta> listaOfferta = catalogo.getListaOfferte(
				listaCatalogo.get(0), listaCatalogo.get(1),
				listaCatalogo.get(2), listaCatalogo.get(3),
				listaCatalogo.get(4));
		List<String> lista = new ArrayList<String>();
		for (Offerta offerta : listaOfferta) {
			if ((offerta.getData()).contains(giorno, mese, anno)) {
				lista.add(offerta.getString());
			}
		}
		return lista;
	}

	public List<String> visualizzaPrenotazioni(List<String> listaCatalogo,
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

	public boolean verificaDatiUtente(String username, String password,
			String nome, String cognome, String ruolo) {
		// TODO Auto-generated method stub
		if (username.equals("") || password.equals("") || nome.equals("")
				|| cognome.equals("") || ruolo.equals(""))
			return false;
		return true;
	}

	public void inserimentoUtente(String username, String password,
			String nome, String cognome, String ruolo) throws DAOException {
		Utente utente = new Utente(username, password, nome, cognome, ruolo);
		utente.save();
	}

	public List<String> visualizzaUtenti() throws DAOException {
		// TODO Auto-generated method stub
		List<String> lista = new ArrayList<String>();
		List<Utente> listaUtenti = Utente.getListaUtenti();
		for (Utente utente : listaUtenti) {
			lista.add(utente.getString());
		}
		return lista;
	}

	public boolean verificaUsernameUtente(String username) {
		// TODO Auto-generated method stub
		if (username.equals(""))
			return false;
		return true;
	}

	public void rimozioneUtente(String username) throws DAOException, SQLException, UtenteException {
		// TODO Auto-generated method stub
		Utente utente = Utente.getUtenteByUsername(username);
		utente.delete();
	}

}
