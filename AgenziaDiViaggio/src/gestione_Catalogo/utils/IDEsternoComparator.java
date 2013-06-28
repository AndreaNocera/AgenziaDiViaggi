package gestione_Catalogo.utils;

import java.util.Comparator;

import java.util.Map.Entry;

public class IDEsternoComparator implements Comparator<IDEsternoElemento>{
	

    public int compare(IDEsternoElemento o1, IDEsternoElemento o2) {
		
		return (o1.getNome().compareTo(o2.getNome()));
	}



	

}
