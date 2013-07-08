package ispw.control;

import ispw.MailSender.MailSender;
import ispw.SMSSender.SMSSender;
import ispw.entity.Biglietto;
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

import javax.mail.MessagingException;

import com.oe.sdk.exceptions.SMSCException;


/**
 * @author Gambella Riccardo Controllore Progettista.
 */
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
				prenotazione.delete();
				/*
				 * Avviso ai traveler della rimozione della prenotazione.
				 * Problema: Bisogna spostare alla prossima offerta con data piï¿½ vicina?
				 * Oppure basta cancellare la prenotazione e avvertire.
				 */
				
				List<Biglietto> biglietti = prenotazione.getListaBiglietti();
				
				for(Biglietto temp : biglietti){
					try {
						MailSender.inviaMail(temp.getTraveler().getEmail(), "La sua prenotazione è stata cancellata!");
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						System.out.println("Mail inviata correttamente!");
					}
					
					try {
						SMSSender.inviaSMS("", "La sua prenotazione è stata cancellata!");
					} catch (SMSCException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						System.out.println("SMS inviato correttamente!");
					}
				}
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
