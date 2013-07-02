package ordinaViaggi.control;

import ordinaViaggi.boundaries.AABoundaryAvvio;
import ordinaViaggi.dao.DAOAmbiente;
import ordinaViaggi.dao.DAOBiglietto;
import ordinaViaggi.dao.DAOCitta;
import ordinaViaggi.dao.DAOMezzo;
import ordinaViaggi.dao.DAOPrenotazione;
import ordinaViaggi.dao.DAOTraveler;
import ordinaViaggi.dao.DAOVia;
import ordinaViaggi.entity.Ambiente;
import ordinaViaggi.entity.Biglietto;
import ordinaViaggi.entity.Catalogo;
import ordinaViaggi.entity.Citta;
import ordinaViaggi.entity.Mezzo;
import ordinaViaggi.entity.Offerta;
import ordinaViaggi.entity.Prenotazione;
import ordinaViaggi.entity.Tratta;
import ordinaViaggi.entity.Traveler;
import ordinaViaggi.entity.Via;
import ordinaViaggi.exception.CatalogoException;
import ordinaViaggi.exception.DAOException;
import ordinaViaggi.exception.DataException;
import ordinaViaggi.exception.MapException;
import ordinaViaggi.exception.OraException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * @author Gambella Riccardo Controllore Progettista.
 */

public class ControlloreAmministratore extends Controllore {
	private static ControlloreAmministratore istance = null;
	private static DAOAmbiente daoAmbiente = null;
	private static DAOMezzo daoMezzo = null;
	private static DAOCitta daoCitta = null;
	private static DAOVia daoVia = null;
	private static DAOPrenotazione daoPrenotazione = null;
	private static DAOBiglietto daoBiglietto = null;
	private static DAOTraveler daoTraveler = null;
	private static Catalogo catalogo = null;

	public ControlloreAmministratore() throws DAOException, MapException,
			SQLException, DataException, OraException, CatalogoException {
		super();
		daoAmbiente = DAOAmbiente.getIstance();
		daoMezzo = DAOMezzo.getIstance();
		daoCitta = DAOCitta.getIstance();
		daoVia = DAOVia.getIstance();
		daoPrenotazione = DAOPrenotazione.getIstance();
		daoBiglietto = DAOBiglietto.getIstance();
		daoTraveler = DAOTraveler.getIstance();
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

	/**
	 * Inserimento di una prenotazione.
	 * 
	 * @param listaCatalogo
	 * @param idOfferta
	 * @param acquirente
	 * @param nome
	 * @param cognome
	 * @param email
	 * @throws DAOException
	 * @throws CatalogoException 
	 * @throws MapException 
	 */
	public void inserimentoInPrenotazione(List<String> listaCatalogo,
			Integer idOfferta, String acquirente, String nome, String cognome,
			String email) throws DAOException, CatalogoException, MapException {
		// TODO Auto-generated method stub
		Integer idPrenotazione = daoPrenotazione.getNextId();
		Integer idBiglietto = daoBiglietto.getNextId();
		Traveler traveler = daoTraveler.getObjectByValue(nome,cognome,email);
		//Creazione del biglietto e salvataggio nel database.
		Biglietto biglietto = new Biglietto(idBiglietto, idPrenotazione, traveler);
		biglietto.save();
		
		List<Biglietto> listaBiglietti = new ArrayList<Biglietto>();
		listaBiglietti.add(biglietto);
		Prenotazione prenotazione = new Prenotazione(idPrenotazione,idOfferta, acquirente, listaBiglietti);
		System.out.println("Prenotazione da inserire:");
		prenotazione.print();
		Tratta tratta = catalogo.getTrattaByValue(
				daoAmbiente.getObjectByValue(listaCatalogo.get(0)),
				daoMezzo.getObjectByValue(listaCatalogo.get(1)),
				daoCitta.getObjectByValue(listaCatalogo.get(2)),
				daoCitta.getObjectByValue(listaCatalogo.get(3)),
				daoVia.getObjectByValue(listaCatalogo.get(4)));
		Offerta offerta = catalogo.getOffertaById(idOfferta);
		catalogo.inserimentoInPrenotazione(tratta,offerta,prenotazione);
		JOptionPane.showMessageDialog(AABoundaryAvvio.Frame, 
				"Viaggio Prenotato.\nId della prenotazione: " + prenotazione.getIdPrenotazione());
		
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

		List<Offerta> listaOfferta = catalogo.getListaOfferte(listaCatalogo.get(0),
				listaCatalogo.get(1), listaCatalogo.get(2),
				listaCatalogo.get(3), listaCatalogo.get(4));
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
	
	public List<String> visualizzaPrenotazioni(List<String> listaCatalogo, Integer idOfferta){
		List<Prenotazione> listaPrenotazioni = catalogo.getListaPrenotazioni(listaCatalogo.get(0),
				listaCatalogo.get(1), listaCatalogo.get(2),
				listaCatalogo.get(3), listaCatalogo.get(4), idOfferta);
		List<String> prenotazioni = new ArrayList<String>();
		for(Prenotazione prenotazione : listaPrenotazioni){
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

}
