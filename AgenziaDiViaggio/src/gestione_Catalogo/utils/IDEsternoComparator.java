package gestione_Catalogo.utils;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class IDEsternoComparator implements Comparator<IDEsterno>{

	
	
	 public int compare(Entry<Integer,MioElemento> o1, Entry<Integer,MioElemento> o2) {
		 
		 
	        return (o1.getValue().getIDEsterno().getNome().compareTo(o2.getValue().getIDEsterno().getNome()));
	          
	}

	@Override
	public int compare(IDEsterno o1, IDEsterno o2) {
		
		return (o1.getNome().compareTo(o2.getNome()));
	}



	

}
