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
	
			controllore.aggiungiViaggio("Aria", "Charter", "Roma", "Milano", "Diretto", "No info");
			controllore.aggiungiViaggio("Aria", "Charter", "Roma", "Parigi", "Diretto","No info");
			controllore.aggiungiViaggio("Aria", "Charter", "Roma", "Ginevra", "Diretto", "No info");
			controllore.aggiungiViaggio("Aria", "Charter", "Napoli", "Cagliari", "Diretto", "No info");
			controllore.aggiungiViaggio("Aria", "Charter", "Napoli", "Isernia", "Diretto", "No info");
			controllore.aggiungiViaggio("Aria", "Charter", "Napoli", "Torino", "Diretto", "No info");
			controllore.aggiungiViaggio("Aria", "Boing", "Roma", "Milano", "Diretto", "No info");
			controllore.aggiungiViaggio("Aria", "Boing", "Roma", "Parigi", "Diretto", "No info");
			controllore.aggiungiViaggio("Aria", "Boing", "Roma", "Ginevra", "Diretto", "No info");
			controllore.aggiungiViaggio("Aria", "Boing", "Napoli", "Cagliari", "Diretto", "No info");
			controllore.aggiungiViaggio("Aria", "Boing", "Napoli", "Isernia", "Diretto", "No info");
			controllore.aggiungiViaggio("Aria", "Boing", "Napoli", "Torino", "Diretto", "No info");
			controllore.aggiungiViaggio("Terra", "Treno", "Roma", "Milano", "Diretto", "No info");
			controllore.aggiungiViaggio("Terra", "Treno", "Roma", "Parigi", "Diretto", "No info");
			controllore.aggiungiViaggio("Terra", "Treno", "Roma", "Ginevra", "Diretto", "No info");
			controllore.aggiungiViaggio("Terra", "Treno", "Napoli", "Cagliari", "Diretto", "No info");
			controllore.aggiungiViaggio("Terra", "Treno", "Napoli", "Isernia", "Diretto", "No info");
			controllore.aggiungiViaggio("Terra", "Treno", "Napoli", "Torino", "Diretto", "No info");
			controllore.aggiungiViaggio("Terra", "Bus", "Roma", "Milano", "Diretto", "No info");
			controllore.aggiungiViaggio("Terra", "Bus", "Roma", "Parigi", "Diretto", "No info");
			controllore.aggiungiViaggio("Terra", "Bus", "Roma", "Ginevra", "Diretto", "No info");
			controllore.aggiungiViaggio("Terra", "Bus", "Napoli", "Cagliari", "Diretto", "No info");
			controllore.aggiungiViaggio("Terra", "Bus", "Napoli", "Isernia", "Diretto", "No info");
			controllore.aggiungiViaggio("Terra", "Bus", "Napoli", "Torino", "Diretto", "No info");
			controllore.aggiungiViaggio("Mare", "Panfilo", "Roma", "Milano", "Diretto", "No info");
			controllore.aggiungiViaggio("Mare", "Panfilo", "Roma", "Parigi", "Diretto", "No info");
			controllore.aggiungiViaggio("Mare", "Panfilo", "Roma", "Ginevra", "Diretto", "No info");
			controllore.aggiungiViaggio("Mare", "Panfilo", "Napoli", "Cagliari", "Diretto", "No info");
			controllore.aggiungiViaggio("Mare", "Panfilo", "Napoli", "Isernia", "Diretto", "No info");
			controllore.aggiungiViaggio("Mare", "Panfilo", "Napoli", "Torino", "Diretto", "No info");
			controllore.aggiungiViaggio("Mare", "Traghetto", "Roma", "Milano", "Diretto", "No info");
			controllore.aggiungiViaggio("Mare", "Traghetto", "Roma", "Parigi", "Diretto", "No info");
			controllore.aggiungiViaggio("Mare", "Traghetto", "Roma", "Ginevra", "Diretto", "No info");
			controllore.aggiungiViaggio("Mare", "Traghetto", "Napoli", "Cagliari", "Diretto", "No info");
			controllore.aggiungiViaggio("Mare", "Traghetto", "Napoli", "Isernia", "Diretto", "No info");
			controllore.aggiungiViaggio("Mare", "Traghetto", "Napoli", "Torino", "Diretto", "No info");
		
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
