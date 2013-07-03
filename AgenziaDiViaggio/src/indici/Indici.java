/*
 * Authors: Luca Paoli e Jessica Lucia
 */

package indici;

import java.util.Calendar;
import java.util.GregorianCalendar;

import IndiciDAO.IndiciDAO;

/*
 * Classe che permette di computare gli indici di gradimento dei viaggi.
 * Tale classe fa uso del DB progettato da Riccardo Gambella.
 * Per computare gli indici occorre passare alle funzioni statiche gli id di Ambiente, Mezzo, e delle Citta di partenza e destinazione.
 * Tali id possono essere ricavai mediante i DAO che accedono al DB suddetto.
 */

public class Indici{
	private Indici(){}
	
	public static double computaIndiciMetodoA(String idAmbiente, String idMezzo, String idCittaSorg, String idCittaDest){
		return computaIndiciSuTuttiIViaggi(idAmbiente, idMezzo, idCittaSorg, idCittaDest);
	}

	public static double computaIndiciMetodoB(String idAmbiente, String idMezzo, String idCittaSorg, String idCittaDest){
		return computaIndiciUltimoAnno(idAmbiente, idMezzo, idCittaSorg, idCittaDest);
	}
	
	public static double computaIndiciMetodoC(String idAmbiente, String idMezzo, String idCittaSorg, String idCittaDest, String anno){
		return computaIndiciUltimaModifica(idAmbiente, idMezzo, idCittaSorg, idCittaDest, anno);
	}
	
	/*Computa gli indici in maniera gerarchica, tenendo conto delle classi/superclassi.*/
	private static double computaIndiciGercarchici(String idAmbiente, String idMezzo, String idCittaSorg, String idCittaDest, String anno){
		IndiciDAO dao = IndiciDAO.getInstance();
		
		int numeratore;
		int denominatore;
		
		/*
		 * Computa l'indice di livello più basso:
		 * biglietti dei viaggi venduti per una tratta con un dato mezzo
		 * diviso biglietti venduti per quel mezzo.
		 */
		if(idCittaSorg != null && idCittaDest != null){
			numeratore = dao.getCountBiglietti(idAmbiente, idMezzo, idCittaSorg, idCittaDest, anno);
			denominatore = dao.getCountBiglietti(idAmbiente, idMezzo, null, null, anno);
		}
		/*
		 * Computa l'indice di livello intermedio:
		 * biglietti venduti per un ambiente e un dato mezzo
		 * diviso biglietti venduti per quell'ambiente.
		 */
		else if(idMezzo != null){
			numeratore = dao.getCountBiglietti(idAmbiente, idMezzo, null, null, anno);
			denominatore = dao.getCountBiglietti(idAmbiente, null, null, null, anno);
		}
		/*
		 * Computa l'indice di livello più alto:
		 * biglietti venduti per un dato ambiente
		 * diviso il numero di biglietti totali. 
		 */
		else{
			numeratore = dao.getCountBiglietti(idAmbiente, null, null, null, anno);
			denominatore = dao.getCountBiglietti(null, null, null, null, anno);
		}
		
		return (double)numeratore/(double)denominatore;	
	}
	
	private static double computaIndiciSuTuttiIViaggi(String idAmbiente, String idMezzo, String idCittaSorg, String idCittaDest){
		return computaIndiciGercarchici(idAmbiente, idMezzo, idCittaSorg, idCittaDest, null);
	}

	private static double computaIndiciUltimoAnno(String idAmbiente, String idMezzo, String idCittaSorg, String idCittaDest){
		GregorianCalendar calendario = new GregorianCalendar();
		int anno = calendario.get(Calendar.YEAR);		//ottiene l'anno corrente
		
		return computaIndiciGercarchici(idAmbiente, idMezzo, idCittaSorg, idCittaDest, anno + "");
	}

	private static double computaIndiciUltimaModifica(String idAmbiente, String idMezzo, String idCittaSorg, String idCittaDest, String anno){
		return computaIndiciGercarchici(idAmbiente, idMezzo, idCittaSorg, idCittaDest, anno);
	}
}