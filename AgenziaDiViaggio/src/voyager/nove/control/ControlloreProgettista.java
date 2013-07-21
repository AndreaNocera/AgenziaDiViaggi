package voyager.nove.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import voyager.nove.exception.CatalogoException;
import voyager.nove.exception.ControllerException;
import voyager.nove.exception.DAOException;
import voyager.nove.exception.DataException;
import voyager.nove.exception.GestoreEccezioniException;
import voyager.nove.exception.MapException;
import voyager.nove.exception.OraException;
import voyager.nove.model.viaggio.Ambiente;
import voyager.nove.model.viaggio.Catalogo;
import voyager.nove.model.viaggio.Citta;
import voyager.nove.model.viaggio.Data;
import voyager.nove.model.viaggio.Mezzo;
import voyager.nove.model.viaggio.Offerta;
import voyager.nove.model.viaggio.Ora;
import voyager.nove.model.viaggio.Prenotazione;
import voyager.nove.model.viaggio.Tratta;
import voyager.nove.model.viaggio.Via;

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

	public List<String> estrazioneAmbienti() throws DAOException, MapException,
			SQLException, DataException, OraException, CatalogoException {
		Catalogo catalogo = Catalogo.getInstance();
		List<Ambiente> listaAmbienti = catalogo.getAmbienti();
		List<String> lista = new ArrayList<String>();
		for (Ambiente ambiente : listaAmbienti)
			lista.add(ambiente.getValore());
		return lista;
	}

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

	public List<String> visualizzaOfferta(List<String> listaCatalogo)
			throws DAOException, MapException, SQLException, DataException,
			OraException, CatalogoException {

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
