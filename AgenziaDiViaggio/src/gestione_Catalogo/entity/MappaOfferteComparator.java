/**
 * 
 */
package gestione_Catalogo.entity;

import java.util.Comparator;

/**
 * @authors
 * Remo Sperlongano
 * Ivan Torre
 */
public class MappaOfferteComparator implements Comparator<Data>{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Data data1, Data data2) {
		
		//prima l'anno
		if (data1.getAnno() > data2.getAnno()){
			return 1;
		} else if (data1.getAnno() < data2.getAnno()){
			return -1;
		}
		
		//se sono qui, l'anno è uguale, ordino per mese
		if (data1.getMese() > data2.getMese()){
			return 1;
		} else if (data1.getMese() < data2.getMese()){
			return -1;
		}
		
		//se sono qui, anche il mese è uguale, ordino per giorno
		if (data1.getGiorno() > data2.getGiorno()){
			return 1;
		} else if (data1.getGiorno() < data2.getGiorno()){
			return -1;
		}
		
		//se sono qui la data è perfettamente uguale, ordino per ora
		if (data1.getOra() > data2.getOra()){
			return 1;
		} else if (data1.getOra() < data2.getOra()){
			return -1;
		}
		
		//se sono qui anche l'ora è esattamente uguale, ordino per minuto
		if (data1.getMinuto() > data2.getMinuto()){
			return 1;
		} else if (data1.getMinuto() < data2.getMinuto()){
			return -1;
		}
		
		//Se sono qui, sono esattamente identici.
		return 0;
	}

}
