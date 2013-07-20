package voyager.nove.model.viaggio.map;

import java.util.Comparator;

import voyager.nove.model.viaggio.basic.Data;

public class MappaOfferteComparator implements Comparator<Data>{

	@Override
	public int compare(Data data1, Data data2) {
		
		if (data1.getAnno() > data2.getAnno()){
			return 1;
		} else if (data1.getAnno() < data2.getAnno()){
			return -1;
		}
		
		if (data1.getMese() > data2.getMese()){
			return 1;
		} else if (data1.getMese() < data2.getMese()){
			return -1;
		}
		
		if (data1.getGiorno() > data2.getGiorno()){
			return 1;
		} else if (data1.getGiorno() < data2.getGiorno()){
			return -1;
		}
		
		if (data1.getOra() > data2.getOra()){
			return 1;
		} else if (data1.getOra() < data2.getOra()){
			return -1;
		}
		
		if (data1.getMinuto() > data2.getMinuto()){
			return 1;
		} else if (data1.getMinuto() < data2.getMinuto()){
			return -1;
		}
		
		return 0;
	}

}
