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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gambella Riccardo Controllore Progettista.
 */
public class ControlloreProgettista extends Controllore {
	private static ControlloreProgettista istance = null;

	public ControlloreProgettista() {
		super();
	}

	public static ControlloreProgettista getIstance() {
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
	 */
	public List<String> estrazioneAmbienti() throws DAOException, MapException {
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
	 */
	public List<String> estrazioneMezzi(String ambiente) throws DAOException,
			MapException {
		Catalogo catalogo = Catalogo.getIstance();
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
	 */
	public List<String> estrazioneCittaPartenza(String ambiente, String mezzo)
			throws DAOException, MapException {
		Catalogo catalogo = Catalogo.getIstance();
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
	 */
	public List<String> estrazioneCittaArrivo(String ambiente, String mezzo,
			String cittaPartenza) throws DAOException, MapException {
		Catalogo catalogo = Catalogo.getIstance();
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
	 */
	public List<String> estrazioneVia(String ambiente, String mezzo,
			String cittaPartenza, String cittaArrivo) throws DAOException,
			MapException {
		Catalogo catalogo = Catalogo.getIstance();
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
	 * @throws DAOException
	 * @throws MapException
	 */
	public void visualizzaOfferta(String ambiente, String mezzo,
			String cittaPartenza, String cittaArrivo, String via)
			throws DAOException, MapException {
		// TODO Auto-generated method stub
		Catalogo catalogo = Catalogo.getIstance();
		List<Offerta> listaOfferta = catalogo.getOfferta(ambiente, mezzo,
				cittaPartenza, cittaArrivo, via);
		for (Offerta offerta : listaOfferta) {
			offerta.print();
		}
	}

	public void inserimentoInOfferta(String ambiente, String mezzo,
			String cittaPartenza, String cittaArrivo, String via)
			throws ControllerException, IOException, DAOException,
			MapException, CatalogoException, DataException, OraException {
		// TODO Auto-generated method stub

		Integer giorno;
		Integer mese;
		Integer ora;
		Integer minuto;
		Integer posti;

		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
		System.out.print("Inserire il giorno: ");
		giorno = input.read();
		System.out.print("Inserire il mese: ");
		mese = input.read();
		System.out.print("Inserire l'ora: ");
		ora = input.read();
		System.out.println("Inserire il minuto: ");
		minuto = input.read();
		System.out.println("Inserire i posti disponibili iniziali.");
		posti = input.read();

		DAOOfferta daoOfferta = DAOOfferta.getIstance();
		DAOAmbiente daoAmbiente = DAOAmbiente.getIstance();
		DAOMezzo daoMezzo = DAOMezzo.getIstance();
		DAOCitta daoCitta = DAOCitta.getIstance();
		DAOVia daoVia = DAOVia.getIstance();

		// Ottengo la tratta dal catalogo.
		// Deve esistere oppure ci sono errori nelle comboBox.
		Catalogo catalogo = Catalogo.getIstance();
		Tratta tratta = catalogo.getTrattaByValue(
				daoAmbiente.getObjectByValue(ambiente),
				daoMezzo.getObjectByValue(mezzo),
				daoCitta.getObjectByValue(cittaPartenza),
				daoCitta.getObjectByValue(cittaArrivo),
				daoVia.getObjectByValue(via));

		Offerta offerta = new Offerta(daoOfferta.getNextId(), tratta.getId(),
				new Data(giorno, mese), new Ora(ora, minuto), posti);

		System.out.println("Offerta da inserire.");
		offerta.print();

		catalogo.inserimentoInOfferta(tratta, offerta);
	}

	/*
	 * public void rimozioneInOfferta(List<String> listaCatalogo) throws
	 * IOException, ControllerException { // TODO Auto-generated method stub
	 * String data = null; String ora = null;
	 * 
	 * BufferedReader input = new BufferedReader( new
	 * InputStreamReader(System.in)); System.out.print("Inserire la data.\r\n");
	 * data = input.readLine(); System.out.print("Inserire l'ora.\r\n"); ora =
	 * input.readLine(); ListaOfferta offerta = ListaOfferta.getIstance(); try {
	 * offerta.rimozioneInOfferta(listaCatalogo, data, ora);
	 * System.out.println("Stampa mappa dopo inserimento in offerta.");
	 * offerta.printOfferta(); } catch (MapDAOException e) { // TODO
	 * Auto-generated catch block throw new
	 * ControllerException("Errore in rimozione offerta!!"); }
	 * 
	 * }
	 */

}
