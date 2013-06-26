package gestione_Catalogo.utils;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class MappaComparator<Integer, MioElemento> extends TreeMap{

	public MappaComparator(){
		
	}
	
	
	public MappaComparator(IDEsternoComparator idEsternoComparator) {
		super(idEsternoComparator);
	}

    /* NON POSSO DEFINIRLI qui, QUESTA CLASSE ESTENDE UNA TREEMAP NON PARAMETRIZZATA!!!
     * FORSE ESTENDENDO QUESTA CLASSE LI POTRO' USARE
     * 
	public void put(MioElemento elem){
		super.put(elem.getID(), elem);
	}
	
	public MioElemento get(int id){
		Object me = super.get(id);
		return me;
	}
	
	
	public String stampaMappa(){
		String s = "";
		Set<Integer> set = super.keySet();
		Iterator<Integer> it = set.iterator();
		
		while(it.hasNext()){
			MioElemento me = (MioElemento) super.get(it.next());
			s += me.getID() + "\t" + me.getIDEsterno() + "\n";
		}
		
		
		return s;
	}*/
	

}
