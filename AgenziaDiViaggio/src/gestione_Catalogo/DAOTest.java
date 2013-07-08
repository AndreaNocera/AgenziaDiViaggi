/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestione_Catalogo;

import java.util.Iterator;
import java.util.Vector;

import gestione_Catalogo.entity.IDEsternoElemento;
import gestione_Catalogo.entity.Mezzo;



public class DAOTest {

	public static void main(String[] args) {
		System.out.println("Test DAO.");
		Mezzo mezzo = new Mezzo(new IDEsternoElemento("prova"));
		mezzo.print();
		//mezzo.cancellaPersistenza();
		//mezzo.equals(mezzo);	
		
	    Vector<String> v = new Vector<String>();
	    v.add("A");
	    v.add("B");

	    Iterator<String> i = v.listIterator();
	    while (i.hasNext()) {
	      System.out.println(i.next());
	    }
	}

}
