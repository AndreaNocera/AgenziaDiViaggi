package ordinaViaggi.control;

import ordinaViaggi.entity.Catalogo;
import ordinaViaggi.exception.ControllerException;
import ordinaViaggi.exception.MapDAOException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
		//Richiesta inserimento di Ambiente, Mezzo, CittaPartenza, CittaArrivo da StdIn
		String ambiente = null;
		String mezzo = null;
		String cittaPartenza = null;
		String cittaArrivo = null;
		String via = null;
		
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

		/* Mockup. Data per inserite le chiavi e le info dei subElements */
		List<String> listaCatalogo = new ArrayList<String>();
		listaCatalogo.add(ambiente);
		listaCatalogo.add(mezzo);
		listaCatalogo.add(cittaPartenza);
		listaCatalogo.add(cittaArrivo);
		listaCatalogo.add(via);
		
		/* Info dei subElements */
		List<String> subElementsInfo = new ArrayList<String>();
		for(int i=0;i<5;i++)
			subElementsInfo.add("");
		
		Catalogo catalogo = Catalogo.getIstance();
		try {
			catalogo.inserimentoInCatalogo(listaCatalogo, subElementsInfo);
			System.out.println("Catalogo presente dopo l'inserimento\n");
			catalogo.printCatalogo();
		} catch (MapDAOException e) {
			// TODO Auto-generated catch block
			throw new ControllerException("Errore in inserimento catalogo!!");
		}
		
	}

	public void rimozioneInCatalogo() throws ControllerException, IOException{
		// TODO Auto-generated method stub
		System.out.println("Rimozione nel catalogo");
		
		//Richiesta rimozione di Ambiente, Mezzo, CittaPartenza, CittaArrivo da StdIn
		String ambiente = null;
		String mezzo = null;
		String cittaPartenza = null;
		String cittaArrivo = null;
		String via = null;
		
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
	
		/* Mockup. Data per inserite le chiavi e le info dei subElements */
		List<String> listaCatalogo = new ArrayList<String>();
		listaCatalogo.add(ambiente);
		listaCatalogo.add(mezzo);
		listaCatalogo.add(cittaPartenza);
		listaCatalogo.add(cittaArrivo);
		listaCatalogo.add(via);
		
		Catalogo catalogo = Catalogo.getIstance();
		try{
			catalogo.rimozioneInCatalogo(listaCatalogo);
			System.out.println("Catalogo presente dopo l'inserimento\n");
			catalogo.printCatalogo();
		}
		catch (MapDAOException e) {
			// TODO Blocco catch generato automaticamente
			throw new ControllerException("Errore in rimozione Catalogo!!");
		}
		
	}

	public void printCatalogo() throws ControllerException {
		// TODO Auto-generated method stub
		Catalogo catalogo = Catalogo.getIstance();
		try {
			catalogo.printCatalogo();
		} catch (MapDAOException e) {
			// TODO Auto-generated catch block
			throw new ControllerException("Errore in printCatalogo");
		}
	}
	
}

