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
import voyager.nove.model.viaggio.Mezzo;
import voyager.nove.model.viaggio.Offerta;
import voyager.nove.model.viaggio.Tratta;
import voyager.nove.model.viaggio.Via;

public class ControllorePromotore extends Controllore {
	private static ControllorePromotore instance = null;
	private static Catalogo catalogo = null;

	private ControllorePromotore() throws DAOException, MapException,
			SQLException, DataException, OraException, CatalogoException {
		super();
		catalogo = Catalogo.getInstance();
	}

	public static ControllorePromotore getInstance() throws DAOException,
			MapException, SQLException, DataException, OraException,
			CatalogoException {
		if (instance == null)
			instance = new ControllorePromotore();
		return instance;
	}

	public synchronized Integer inserimentoCatalogo(String ambiente, String mezzo,
			String cittaPartenza, String cittaArrivo, String via)
			throws ControllerException, IOException, DAOException,
			MapException, CatalogoException, SQLException, DataException,
			OraException {
		Tratta tratta = new Tratta();

		Integer id = null;

		// Prende un nuovo id per la costruzione della tratta.
		id = Catalogo.getNextIdTratta();
		tratta.setId(id);
		// Inserisce gli oggetti nella tratta, creandoli nel Db se non
		// esistenti.
		tratta.setAmbiente(Ambiente.getObjectByValue(ambiente));
		tratta.setMezzo(Mezzo.getObjectByValue(mezzo));
		tratta.setCittaPartenza(Citta.getObjectByValue(cittaPartenza));
		tratta.setCittaArrivo(Citta.getObjectByValue(cittaArrivo));
		tratta.setVia(Via.getObjectByValue(via));

		catalogo.inserimentoInCatalogo(tratta);

		return tratta.getId();
	}

	public synchronized void rimozioneInCatalogo(Integer idTratta)
			throws ControllerException, IOException, DAOException,
			MapException, CatalogoException, SQLException, DataException,
			OraException, GestoreEccezioniException {

		System.out.println("Rimozione nel catalogo");

		// Ottengo la tratta dal catalogo.
		// Deve esistere oppure ci sono errori nelle comboBox.
		Tratta tratta = catalogo.getTrattaById(idTratta);

		List<Offerta> listaOfferte = catalogo.getListaOfferte(
				tratta.getAmbiente(), tratta.getMezzo(),
				tratta.getCittaPartenza(), tratta.getCittaArrivo(),
				tratta.getVia());

		if (!listaOfferte.isEmpty()) {
			throw new GestoreEccezioniException(
					"Impossibile rimuovere tratta.\nEsistono offerte relative alla tratta.");

		} else {
			catalogo.rimozioneInCatalogo(tratta);
		}
	}

	public synchronized List<String> visualizzaCatalogo() throws ControllerException,
			DAOException, MapException, CatalogoException, SQLException,
			DataException, OraException {
		// TODO Auto-generated method stub
		List<String> listaTratte = new ArrayList<String>();
		List<Tratta> tratte = catalogo.visualizzaCatalogo();
		for (Tratta tratta : tratte) {
			listaTratte.add(tratta.getString());
		}
		return listaTratte;
	}

	public synchronized boolean verificaDati(String ambiente, String mezzo,
			String cittaPartenza, String cittaArrivo, String via) {
		if (ambiente.equals("") || mezzo.equals("") || cittaPartenza.equals("")
				|| cittaArrivo.equals("") || via.equals(""))
			return false;
		return true;

	}

	public synchronized boolean verificaIdTratta(String idTratta) {
		// TODO Auto-generated method stub
		if (idTratta.equals(""))
			return false;
		return true;
	}

}
