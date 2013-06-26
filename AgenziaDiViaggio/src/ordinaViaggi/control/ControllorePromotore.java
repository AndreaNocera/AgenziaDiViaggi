package ordinaViaggi.control;

import ordinaViaggi.dao.DAOAmbiente;
import ordinaViaggi.dao.DAOCatalogo;
import ordinaViaggi.dao.DAOCitta;
import ordinaViaggi.dao.DAOMezzo;
import ordinaViaggi.dao.DAOVia;
import ordinaViaggi.entity.Ambiente;
import ordinaViaggi.entity.Catalogo;
import ordinaViaggi.entity.Citta;
import ordinaViaggi.entity.Mezzo;
import ordinaViaggi.entity.Tratta;
import ordinaViaggi.entity.Via;
import ordinaViaggi.exception.CatalogoException;
import ordinaViaggi.exception.ControllerException;
import ordinaViaggi.exception.DAOException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Gambella Riccardo
 * Controllore Promotore.
 */

public class ControllorePromotore extends Controllore
{
	private static ControllorePromotore istance = null;   

	public ControllorePromotore()
	{
		super();
	}
	
	public static ControllorePromotore getIstance(){
		if(istance == null)
			istance = new ControllorePromotore();
		return istance;
	}
	
	public void inserimentoCatalogo() throws ControllerException, IOException{
		System.out.println("Inserimento nel catalogo");
		//Richiesta inserimento di Ambiente, Mezzo, CittaPartenza, CittaArrivo da StdIn
		Tratta tratta = new Tratta();
		String ambiente = null;
		String mezzo = null;
		String cittaPartenza = null;
		String cittaArrivo = null;
		String via = null;
		
		Integer id = null;
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
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
		
		try {
			id = daoCatalogo.getNextId();
			tratta.setId(id);
			id = daoAmbiente.getIdByValue(ambiente);
			tratta.setAmbiente(new Ambiente(id, ambiente));
			id = daoMezzo.getIdByValue(mezzo);
			tratta.setMezzo(new Mezzo(id, mezzo));
			id = daoCitta.getIdByValue(cittaPartenza);
			System.out.println("Id citta " + cittaPartenza + " nel controllore: " + id);
			tratta.setCittaPartenza(new Citta(id, cittaPartenza));
			
			/*Restituisce lo stesso id di prima se la citta di partenza e quella di arrivo sono nuove.
			 * Dovrebbe restituire quello di quella di partenza nuova + 1.
			 */
			id = daoCitta.getIdByValue(cittaArrivo);
			System.out.println("Id citta " + cittaArrivo + " nel controllore: " + id);
			tratta.setCittaArrivo(new Citta(id, cittaArrivo));
			id = daoVia.getIdByValue(via);
			tratta.setVia(new Via(id, via));
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print("Tratta da inserire: ");
		tratta.printTratta();
		
		Catalogo catalogo = Catalogo.getIstance();
		try {
			catalogo.inserimentoInCatalogo(tratta);
			System.out.println("Catalogo presente dopo l'inserimento\n");
			catalogo.printCatalogo();
		} catch (CatalogoException e) {
			// TODO Auto-generated catch block
			throw new ControllerException("Errore in inserimento catalogo!!");
		}
		
	}

	public void rimozioneInCatalogo() throws ControllerException, IOException{
		// TODO Auto-generated method stub
		System.out.println("Rimozione nel catalogo");
		
		
		Tratta tratta = new Tratta();
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Inserire l'id della tratta.\r\n");
		tratta.setId(input.read());
		System.out.print("Inserire id ambiente e valore dell'ambiente.\r\n");
		tratta.setAmbiente(new Ambiente(input.read(), input.readLine()));
		System.out.print("Inserire il mezzo.\r\n");
		tratta.setMezzo(new Mezzo(input.read(), input.readLine()));	
		System.out.print("Inserire la città di partenza.\r\n");
		tratta.setCittaPartenza(new Citta(input.read(), input.readLine()));
		System.out.print("Inserire la città di arrivo.\r\n");
		tratta.setCittaArrivo(new Citta(input.read(), input.readLine()));
		System.out.println("Inserire la via (Tratte intermedie)");
		tratta.setVia(new Via(input.read(), input.readLine()));
		
		System.out.print("Tratta da cancellare: ");
		tratta.printTratta();
		
		
		Catalogo catalogo = Catalogo.getIstance();
		try{
			catalogo.rimozioneInCatalogo(tratta);
			System.out.println("Catalogo presente dopo l'inserimento\n");
			catalogo.printCatalogo();
		}
		catch (CatalogoException e) {
			// TODO Blocco catch generato automaticamente
			throw new ControllerException("Errore in rimozione Catalogo!!");
		}
		
	}

	public void printCatalogo() throws ControllerException {
		// TODO Auto-generated method stub
		Catalogo catalogo = Catalogo.getIstance();
		try {
			catalogo.printCatalogo();
		} catch (CatalogoException e) {
			// TODO Auto-generated catch block
			throw new ControllerException("Errore in printCatalogo");
		}
	}
	
}

