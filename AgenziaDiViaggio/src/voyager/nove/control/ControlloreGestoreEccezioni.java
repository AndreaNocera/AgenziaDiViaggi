package voyager.nove.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import voyager.nove.exception.CatalogoException;
import voyager.nove.exception.ControllerException;
import voyager.nove.exception.DAOException;
import voyager.nove.exception.DataException;
import voyager.nove.exception.GestoreEccezioniException;
import voyager.nove.exception.MapException;
import voyager.nove.exception.OraException;
import voyager.nove.model.viaggio.Biglietto;
import voyager.nove.model.viaggio.Catalogo;
import voyager.nove.model.viaggio.Offerta;
import voyager.nove.model.viaggio.Prenotazione;
import voyager.nove.model.viaggio.Tratta;

public class ControlloreGestoreEccezioni extends Controllore {
	private static ControlloreGestoreEccezioni instance = null;
	private static Catalogo catalogo = null;

	private ControlloreGestoreEccezioni() throws DAOException, MapException,
			SQLException, DataException, OraException, CatalogoException {
		super();
		catalogo = Catalogo.getInstance();
	}

	public static ControlloreGestoreEccezioni getInstance() throws DAOException,
			MapException, SQLException, DataException, OraException,
			CatalogoException {
		if (instance == null)
			instance = new ControlloreGestoreEccezioni();
		return instance;
	}

	public void rimozioneInOfferta(Integer idOfferta)
			throws ControllerException, IOException, DAOException,
			MapException, CatalogoException, DataException, OraException,
			SQLException, GestoreEccezioniException {

		Offerta offerta = catalogo.getOffertaById(idOfferta);
		Tratta tratta = catalogo.getTrattaById(offerta.getIdTratta());
		List<Prenotazione> listaPrenotazioni = catalogo.getListaPrenotazioniByidOfferta(idOfferta);

		if (listaPrenotazioni.isEmpty()) {
			throw new GestoreEccezioniException(
					"Non ci sono prenotazioni associate. Il compito va assegnato al progettista.");
		} else {
			//Rimozione delle prenotazioni
			for(Prenotazione prenotazione : listaPrenotazioni){
				prenotazione.delete();
				/*
				 * Avviso ai traveler della rimozione della prenotazione.
				 * Problema: Bisogna spostare alla prossima offerta con data pi� vicina?
				 * Oppure basta cancellare la prenotazione e avvertire.
				 */
				
				List<Biglietto> biglietti = prenotazione.getListaBiglietti();
				
				//invia mail per ogni biglietto prenotato ****************************************************
			}
			
			catalogo.rimozioneInOfferta(tratta, offerta);
		}
	}

	public boolean verificaDati(String offerta) {
		if (offerta.equals(""))
			return false;
		return true;
	}

}
