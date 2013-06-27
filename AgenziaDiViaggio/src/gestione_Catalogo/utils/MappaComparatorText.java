package gestione_Catalogo.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MappaComparatorText {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MappaCatalogo mappa = new MappaCatalogo(new IDEsternoComparator());
		
		MioElemento me1 = new MioElemento("Zaccaria");
		MioElemento me2 = new MioElemento("Alberto");
		MioElemento me3 = new MioElemento("Jesus");
		MioElemento me4 = new MioElemento("Alan");
		MioElemento me5 = new MioElemento("Remo");
		MioElemento me6 = new MioElemento("Ivan");
		MioElemento me7 = new MioElemento("Riccardo");
		MioElemento me8 = new MioElemento("Gabriele");
		MioElemento me9 = new MioElemento("Jessica");
		MioElemento me10 = new MioElemento("Francesco");
		MioElemento me11 = new MioElemento("Luca");
		MioElemento me12 = new MioElemento("Giacomo");
		MioElemento me13 = new MioElemento("Zeno");
		
		
		mappa.put(me1.getIDEsterno(), me1);
		mappa.put(me2.getIDEsterno(), me2);
		mappa.put(me3.getIDEsterno(), me3);
		mappa.put(me4.getIDEsterno(), me4);
		mappa.put(me5.getIDEsterno(), me5);
		mappa.put(me6.getIDEsterno(), me6);
		mappa.put(me7.getIDEsterno(), me7);
		mappa.put(me8.getIDEsterno(), me8);
		mappa.put(me9.getIDEsterno(), me9);
		mappa.put(me10.getIDEsterno(), me10);
		mappa.put(me11.getIDEsterno(), me11);
		mappa.put(me12.getIDEsterno(), me12);
		mappa.put(me13.getIDEsterno(), me13);
		
		
		
		
		String s = "";
		Set<Integer> set = mappa.keySet();
		Iterator<Integer> it = set.iterator();
		
		while(it.hasNext()){
			MioElemento me = (MioElemento) mappa.get(it.next());
			s += me.getIDEsterno().getNome() + "\n";
		}
		
		System.out.println(s);

	}

}
