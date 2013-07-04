/**
 * Per il test della data...
 */
package gestione_Catalogo;

import java.util.Iterator;
import java.util.Set;

import gestione_Catalogo.entity.Data;
import gestione_Catalogo.entity.MappaOfferte;
import gestione_Catalogo.entity.MappaOfferteComparator;
import gestione_Catalogo.entity.Offerta;

/**
 * @authors
 * Remo Sperlongano
 * Ivan Torre
 */
public class DataTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		MappaOfferte mappa = new MappaOfferte(new MappaOfferteComparator());
		
		
		Offerta offerta1 = new Offerta(1, new Data(10,10,2013,10,10), 10,100);
		Offerta offerta2 = new Offerta(2, new Data(9,10,2013,10,10), 20,200);
		Offerta offerta3 = new Offerta(3, new Data(10,9,2013,10,10), 30,300);
		Offerta offerta4 = new Offerta(4, new Data(10,10,2012,10,10), 40,400);
		Offerta offerta5 = new Offerta(5, new Data(10,10,2013,10,9), 50,500);
		
		
		mappa.aggiungiOfferta(offerta1.getData(), offerta1);
		mappa.aggiungiOfferta(offerta2.getData(), offerta2);
		mappa.aggiungiOfferta(offerta3.getData(), offerta3);
		mappa.aggiungiOfferta(offerta4.getData(), offerta4);
		mappa.aggiungiOfferta(offerta5.getData(), offerta5);
		
		
		Set<Data> set = mappa.keySet();
		Iterator<Data> it = set.iterator();
		
		while(it.hasNext()){
			System.out.println(it.next().stampaData());
		}
		
		
		

	}

}
