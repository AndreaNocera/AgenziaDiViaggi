package gestione_Catalogo.utils;

import java.util.Iterator;
import java.util.Set;

public class MappaComparatorText {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MappaComparator<Integer, MioElemento> mappa = new MappaComparator(new IDEsternoComparator());
		
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
		
		
		mappa.put(me1.getID(), me1);
		mappa.put(me2.getID(), me2);
		mappa.put(me3.getID(), me3);
		mappa.put(me4.getID(), me4);
		mappa.put(me5.getID(), me5);
		mappa.put(me6.getID(), me6);
		mappa.put(me7.getID(), me7);
		mappa.put(me8.getID(), me8);
		mappa.put(me9.getID(), me9);
		mappa.put(me10.getID(), me10);
		mappa.put(me11.getID(), me11);
		mappa.put(me12.getID(), me12);
		mappa.put(me13.getID(), me13);
		
		
		
		
		String s = "";
		Set<Integer> set = mappa.keySet();
		Iterator<Integer> it = set.iterator();
		
		while(it.hasNext()){
			MioElemento me = (MioElemento) mappa.get(it.next());
			s += me.getID().toString() + "\t" + me.getIDEsterno() + "\n";
		}
		
		System.out.println(s);

	}

}
