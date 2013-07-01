/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */


package gestione_Catalogo.thread;

import java.lang.reflect.InvocationTargetException;

import gestione_Catalogo.control.ControlloreGestioneCatalogo;
import gestione_Catalogo.exception.IDEsternoElementoException;
import gestione_Catalogo.exception.TrattaException;


public class PromotoreThread implements Runnable {
	
	//Attributi d'istanza
	private ControlloreGestioneCatalogo controllore;
	
	
	//Costruttore
	public PromotoreThread(ControlloreGestioneCatalogo controllore){
		this.controllore = controllore;
	}

	@Override
	public void run() {

		
		try {
	
			controllore.aggiungiViaggio("Aria", "Charter", "Roma", "Milano", "", "");
			controllore.aggiungiViaggio("Aria", "Charter", "Roma", "Parigi", "","");
			controllore.aggiungiViaggio("Aria", "Charter", "Roma", "Ginevra", "", "");
			controllore.aggiungiViaggio("Aria", "Charter", "Napoli", "Cagliari", "", "");
			controllore.aggiungiViaggio("Aria", "Charter", "Napoli", "Isernia", "", "");
			controllore.aggiungiViaggio("Aria", "Charter", "Napoli", "Torino", "", "");
			controllore.aggiungiViaggio("Aria", "Boing", "Roma", "Milano", "", "");
			controllore.aggiungiViaggio("Aria", "Boing", "Roma", "Parigi", "", "");
			controllore.aggiungiViaggio("Aria", "Boing", "Roma", "Ginevra", "", "");
			controllore.aggiungiViaggio("Aria", "Boing", "Napoli", "Cagliari","", "");
			controllore.aggiungiViaggio("Aria", "Boing", "Napoli", "Isernia", "", "");
			controllore.aggiungiViaggio("Aria", "Boing", "Napoli", "Torino", "", "");
			controllore.aggiungiViaggio("Terra", "Treno", "Roma", "Milano", "", "");
			controllore.aggiungiViaggio("Terra", "Treno", "Roma", "Parigi", "", "");
			controllore.aggiungiViaggio("Terra", "Treno", "Roma", "Ginevra","", "");
			controllore.aggiungiViaggio("Terra", "Treno", "Napoli", "Cagliari","", "");
			controllore.aggiungiViaggio("Terra", "Treno", "Napoli", "Isernia","", "");
			controllore.aggiungiViaggio("Terra", "Treno", "Napoli", "Torino", "", "");
			controllore.aggiungiViaggio("Terra", "Bus", "Roma", "Milano", "", "");
			controllore.aggiungiViaggio("Terra", "Bus", "Roma", "Parigi", "", "");
			controllore.aggiungiViaggio("Terra", "Bus", "Roma", "Ginevra", "", "");
			controllore.aggiungiViaggio("Terra", "Bus", "Napoli", "Cagliari", "", "");
			controllore.aggiungiViaggio("Terra", "Bus", "Napoli", "Isernia", "", "");
			controllore.aggiungiViaggio("Terra", "Bus", "Napoli", "Torino", "", "");
			controllore.aggiungiViaggio("Mare", "Panfilo", "Roma", "Milano", "", "");
			controllore.aggiungiViaggio("Mare", "Panfilo", "Roma", "Parigi", "", "");
			controllore.aggiungiViaggio("Mare", "Panfilo", "Roma", "Ginevra", "", "");
			controllore.aggiungiViaggio("Mare", "Panfilo", "Napoli", "Cagliari", "", "");
			controllore.aggiungiViaggio("Mare", "Panfilo", "Napoli", "Isernia", "", "");
			controllore.aggiungiViaggio("Mare", "Panfilo", "Napoli", "Torino", "", "");
			controllore.aggiungiViaggio("Mare", "Traghetto", "Roma", "Milano", "", "");
			controllore.aggiungiViaggio("Mare", "Traghetto", "Roma", "Parigi", "", "");
			controllore.aggiungiViaggio("Mare", "Traghetto", "Roma", "Ginevra", "", "");
			controllore.aggiungiViaggio("Mare", "Traghetto", "Napoli", "Cagliari","", "");
			controllore.aggiungiViaggio("Mare", "Traghetto", "Napoli", "Isernia", "", "");
			controllore.aggiungiViaggio("Mare", "Traghetto", "Napoli", "Torino", "", "");
		
		} catch (IDEsternoElementoException e) {
			System.out.println(e.getMessage()+"\n");
		} catch (TrattaException e) {
			System.out.println(e.getMessage()+"\n");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
