package gestione_Catalogo.utils;

import java.util.Comparator;

public class IDEsternoComparator implements Comparator<MioElemento>{

	@Override
	public int compare(MioElemento o1, MioElemento o2) {
		int r = o1.getIDEsterno().toString().compareTo(o2.getIDEsterno().toString());
		
		return r;
	}

}
