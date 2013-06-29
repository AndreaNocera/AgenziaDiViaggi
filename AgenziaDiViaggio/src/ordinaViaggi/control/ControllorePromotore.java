package ordinaViaggi.control;

import ordinaViaggi.dao.DAOAmbiente;
import ordinaViaggi.dao.DAOCatalogo;
import ordinaViaggi.dao.DAOCitta;
import ordinaViaggi.dao.DAOMezzo;
import ordinaViaggi.dao.DAOVia;
import ordinaViaggi.entity.Catalogo;
import ordinaViaggi.entity.Tratta;
import ordinaViaggi.exception.CatalogoException;
import ordinaViaggi.exception.ControllerException;
import ordinaViaggi.exception.DAOException;
import ordinaViaggi.exception.MapException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Gambella Riccardo Controllore Promotore.
 */

public class ControllorePromotore extends Controllore {
	private static ControllorePromotore istance = null;

	public ControllorePromotore() {
		super();
	}

	public static ControllorePromotore getIstance() {
		if (istance == null)
			istance = new ControllorePromotore();
		return istance;
	}

	public void inserimentoCatalogo() throws ControllerException, IOException,
			DAOException, MapException, CatalogoException {
		System.out.println("Inserimento nel catalogo");
		// Richiesta inserimento di Ambiente, Mezzo, CittaPartenza, CittaArrivo
		// da StdIn
		Tratta tratta = new Tratta();
		String ambiente = null;
		String mezzo = null;
		String cittaPartenza = null;
		String cittaArrivo = null;
		String via = null;

		Integer id = null;

		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
		System.out.print("Inserire l'ambiente.\r\n");
		ambiente = input.readLine();
		System.out.print("Inserire il mezzo.\r\n");
		mezzo = input.readLine();
		System.out.print("Inserire la città di partenza.\r\n");
		cittaPartenza = input.readLine();
		System.out.print("Inserire la città di arrivo.\r\n");
		cittaArrivo = input.readLine();
		System.out.println("Inserire la via (Tratte intermedie)");
		via = input.readLine();

		DAOCatalogo daoCatalogo = DAOCatalogo.getIstance();
		DAOAmbiente daoAmbiente = DAOAmbiente.getIstance();
		DAOMezzo daoMezzo = DAOMezzo.getIstance();
		DAOCitta daoCitta = DAOCitta.getIstance();
		DAOVia daoVia = DAOVia.getIstance();

		// Prende un nuovo id per la costruzione della tratta.
		id = daoCatalogo.getNextId();
		tratta.setId(id);
		// Inserisce gli oggetti nella tratta, creandoli nel Db se non
		// esistenti.
		tratta.setAmbiente(daoAmbiente.getObjectByValue(ambiente));
		tratta.setMezzo(daoMezzo.getObjectByValue(mezzo));
		tratta.setCittaPartenza(daoCitta.getObjectByValue(cittaPartenza));
		tratta.setCittaArrivo(daoCitta.getObjectByValue(cittaArrivo));
		tratta.setVia(daoVia.getObjectByValue(via));

		System.out.print("Tratta da inserire: ");
		tratta.printTratta();

		Catalogo catalogo = Catalogo.getIstance();
		catalogo.inserimentoInCatalogo(tratta);
		System.out.println("Catalogo presente dopo l'inserimento\n");
		catalogo.printCatalogo();
		System.out.println("Mappa presente dopo l'inserimento.");
		catalogo.printMapCatalogo();

	}

	public void rimozioneInCatalogo() throws ControllerException, IOException,
			DAOException, MapException, CatalogoException {
		System.out.println("Inserimento nel catalogo");
		// Richiesta inserimento di Ambiente, Mezzo, CittaPartenza, CittaArrivo
		// da StdIn
		String ambiente = null;
		String mezzo = null;
		String cittaPartenza = null;
		String cittaArrivo = null;
		String via = null;

		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
		System.out.print("Inserire l'ambiente.\r\n");
		ambiente = input.readLine();
		System.out.print("Inserire il mezzo.\r\n");
		mezzo = input.readLine();
		System.out.print("Inserire la città di partenza.\r\n");
		cittaPartenza = input.readLine();
		System.out.print("Inserire la città di arrivo.\r\n");
		cittaArrivo = input.readLine();
		System.out.println("Inserire la via (Tratte intermedie)");
		via = input.readLine();

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

		System.out.println("Tratta da rimuovere.");
		tratta.printTratta();
		
		catalogo.rimozioneInCatalogo(tratta);
		System.out.println("Catalogo presente dopo la rimozione\n");
		catalogo.printCatalogo();

	}

	public void printCatalogo() throws ControllerException, DAOException,
			MapException, CatalogoException {
		// TODO Auto-generated method stub
		Catalogo catalogo = Catalogo.getIstance();
		System.out.println("Lista catalogo.");
		catalogo.printCatalogo();
		System.out.println("Mappa presente.");
		catalogo.printMapCatalogo();
	}

}
