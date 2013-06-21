/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */


package gestione_Catalogo.thread;

import java.lang.reflect.InvocationTargetException;

import gestione_Catalogo.control.ControlloreGestioneCatalogo;
import gestione_Catalogo.exception.IDEsternoException;


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
	
			controllore.aggiungiViaggio("Aria", "Charter", "Roma", "Milano", "Diretto");
			controllore.aggiungiViaggio("Aria", "Charter", "Roma", "Parigi", "Diretto");
			controllore.aggiungiViaggio("Aria", "Charter", "Roma", "Ginevra", "Diretto");
			controllore.aggiungiViaggio("Aria", "Charter", "Napoli", "Cagliari", "Diretto");
			controllore.aggiungiViaggio("Aria", "Charter", "Napoli", "Isernia", "Diretto");
			controllore.aggiungiViaggio("Aria", "Charter", "Napoli", "Torino", "Diretto");
			controllore.aggiungiViaggio("Aria", "Boing", "Roma", "Milano", "Diretto");
			controllore.aggiungiViaggio("Aria", "Boing", "Roma", "Parigi", "Diretto");
			controllore.aggiungiViaggio("Aria", "Boing", "Roma", "Ginevra", "Diretto");
			controllore.aggiungiViaggio("Aria", "Boing", "Napoli", "Cagliari", "Diretto");
			controllore.aggiungiViaggio("Aria", "Boing", "Napoli", "Isernia", "Diretto");
			controllore.aggiungiViaggio("Aria", "Boing", "Napoli", "Torino", "Diretto");
			controllore.aggiungiViaggio("Terra", "Treno", "Roma", "Milano", "Diretto");
			controllore.aggiungiViaggio("Terra", "Treno", "Roma", "Parigi", "Diretto");
			controllore.aggiungiViaggio("Terra", "Treno", "Roma", "Ginevra", "Diretto");
			controllore.aggiungiViaggio("Terra", "Treno", "Napoli", "Cagliari", "Diretto");
			controllore.aggiungiViaggio("Terra", "Treno", "Napoli", "Isernia", "Diretto");
			controllore.aggiungiViaggio("Terra", "Treno", "Napoli", "Torino", "Diretto");
			controllore.aggiungiViaggio("Terra", "Bus", "Roma", "Milano", "Diretto");
			controllore.aggiungiViaggio("Terra", "Bus", "Roma", "Parigi", "Diretto");
			controllore.aggiungiViaggio("Terra", "Bus", "Roma", "Ginevra", "Diretto");
			controllore.aggiungiViaggio("Terra", "Bus", "Napoli", "Cagliari", "Diretto");
			controllore.aggiungiViaggio("Terra", "Bus", "Napoli", "Isernia", "Diretto");
			controllore.aggiungiViaggio("Terra", "Bus", "Napoli", "Torino", "Diretto");
			controllore.aggiungiViaggio("Mare", "Panfilo", "Roma", "Milano", "Diretto");
			controllore.aggiungiViaggio("Mare", "Panfilo", "Roma", "Parigi", "Diretto");
			controllore.aggiungiViaggio("Mare", "Panfilo", "Roma", "Ginevra", "Diretto");
			controllore.aggiungiViaggio("Mare", "Panfilo", "Napoli", "Cagliari", "Diretto");
			controllore.aggiungiViaggio("Mare", "Panfilo", "Napoli", "Isernia", "Diretto");
			controllore.aggiungiViaggio("Mare", "Panfilo", "Napoli", "Torino", "Diretto");
			controllore.aggiungiViaggio("Mare", "Traghetto", "Roma", "Milano", "Diretto");
			controllore.aggiungiViaggio("Mare", "Traghetto", "Roma", "Parigi", "Diretto");
			controllore.aggiungiViaggio("Mare", "Traghetto", "Roma", "Ginevra", "Diretto");
			controllore.aggiungiViaggio("Mare", "Traghetto", "Napoli", "Cagliari", "Diretto");
			controllore.aggiungiViaggio("Mare", "Traghetto", "Napoli", "Isernia", "Diretto");
			controllore.aggiungiViaggio("Mare", "Traghetto", "Napoli", "Torino", "Diretto");
		
		} catch (IDEsternoException e) {
			// TODO Auto-generated catch block
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
