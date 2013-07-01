package ordinaViaggi.control;

import ordinaViaggi.boundaries.AABoundaryAvvio;
import ordinaViaggi.entity.Ambiente;
import ordinaViaggi.entity.Catalogo;
import ordinaViaggi.entity.Citta;
import ordinaViaggi.entity.Mezzo;
import ordinaViaggi.entity.Offerta;
import ordinaViaggi.entity.Tratta;
import ordinaViaggi.entity.Via;
import ordinaViaggi.exception.CatalogoException;
import ordinaViaggi.exception.ControllerException;
import ordinaViaggi.exception.DAOException;
import ordinaViaggi.exception.DataException;
import ordinaViaggi.exception.GestoreEccezioniException;
import ordinaViaggi.exception.MapException;
import ordinaViaggi.exception.OraException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * @author Gambella Riccardo Controllore Promotore.
 */

public class ControllorePromotore extends Controllore {
	private static ControllorePromotore istance = null;
	private static Catalogo catalogo = null;

	public ControllorePromotore() throws DAOException, MapException,
			SQLException, DataException, OraException, CatalogoException {
		super();
		catalogo = Catalogo.getIstance();
	}

	public static ControllorePromotore getIstance() throws DAOException,
			MapException, SQLException, DataException, OraException,
			CatalogoException {
		if (istance == null)
			istance = new ControllorePromotore();
		return istance;
	}

	public Integer inserimentoCatalogo(String ambiente, String mezzo,
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

	public void rimozioneInCatalogo(Integer idTratta)
			throws ControllerException, IOException, DAOException,
			MapException, CatalogoException, SQLException, DataException,
			OraException, GestoreEccezioniException {

		System.out.println("Rimozione nel catalogo");

		// Ottengo la tratta dal catalogo.
		// Deve esistere oppure ci sono errori nelle comboBox.
		Tratta tratta = catalogo.getTrattaById(idTratta);

		List<Offerta> listaOfferte = catalogo.getListaOfferte(tratta
				.getAmbiente().getValore(), tratta.getMezzo().getValore(),
				tratta.getCittaPartenza().getValore(), tratta.getCittaArrivo()
						.getValore(), tratta.getVia().getValore());

		if (!listaOfferte.isEmpty()) {
			throw new GestoreEccezioniException(
					"Impossibile rimuovere tratta.\nEsistono offerte relative alla tratta.");

		} else {
			catalogo.rimozioneInCatalogo(tratta);
			// Devo rimuovere le prenotazioni relative all'offerta considerata.
			JOptionPane.showMessageDialog(AABoundaryAvvio.Frame,
					"Tratta rimossa.");
		}
	}

	public List<String> visualizzaCatalogo() throws ControllerException,
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

	public boolean verificaDati(String ambiente, String mezzo,
			String cittaPartenza, String cittaArrivo, String via) {
		if (ambiente.equals("") || mezzo.equals("") || cittaPartenza.equals("")
				|| cittaArrivo.equals("") || via.equals(""))
			return false;
		return true;

	}

	public boolean verificaIdTratta(String idTratta) {
		// TODO Auto-generated method stub
		if (idTratta.equals(""))
			return false;
		return true;
	}

}
