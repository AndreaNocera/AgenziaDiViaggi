package ispw.control;

import ispw.entity.Catalogo;
import ispw.entity.Offerta;
import ispw.entity.Prenotazione;
import ispw.entity.Tratta;
import ispw.exception.CatalogoException;
import ispw.exception.ControllerException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.GestoreEccezioniException;
import ispw.exception.MapException;
import ispw.exception.OraException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


/**
 * @author Gambella Riccardo Controllore Progettista.
 */
public class ControlloreGestoreEccezioni extends Controllore {
	private static ControlloreGestoreEccezioni istance = null;
	private static Catalogo catalogo = null;

	private ControlloreGestoreEccezioni() throws DAOException, MapException,
			SQLException, DataException, OraException, CatalogoException {
		super();
		catalogo = Catalogo.getIstance();
	}

	public static ControlloreGestoreEccezioni getIstance() throws DAOException,
			MapException, SQLException, DataException, OraException,
			CatalogoException {
		if (istance == null)
			istance = new ControlloreGestoreEccezioni();
		return istance;
	}

	public void rimozioneInOfferta(Integer idOfferta)
			throws ControllerException, IOException, DAOException,
			MapException, CatalogoException, DataException, OraException,
			SQLException, GestoreEccezioniException {
		// TODO Auto-generated method stub

		Offerta offerta = catalogo.getOffertaById(idOfferta);
		Tratta tratta = catalogo.getTrattaById(offerta.getIdTratta());
		List<Prenotazione> listaPrenotazioni = catalogo.getListaPrenotazioniByidOfferta(idOfferta);

		if (listaPrenotazioni.isEmpty()) {
			throw new GestoreEccezioniException(
					"Non ci sono prenotazioni associate. Il compito va assegnato al progettista.");
		} else {
			//Rimozione delle prenotazioni
			for(Prenotazione prenotazione : listaPrenotazioni){
				System.out.println("Rimozione delle prenotazioni.");
				prenotazione.delete();
				/*
				 * Avviso ai traveler della rimozione della prenotazione.
				 * Problema: Bisogna spostare alla prossima offerta con data più vicina?
				 * Oppure basta cancellare la prenotazione e avvertire.
				 */
			}
			
			//Rimozione dell'offerta
			catalogo.rimozioneInOfferta(tratta, offerta);
		}
	}

	public boolean verificaDati(String offerta) {
		// TODO Auto-generated method stub
		if (offerta.equals(""))
			return false;
		return true;
	}

}
