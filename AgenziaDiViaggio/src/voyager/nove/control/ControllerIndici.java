package voyager.nove.control;

import java.util.Calendar;
import java.util.GregorianCalendar;

import voyager.nove.persistence.dao.IndiciDAO;

public class ControllerIndici{
	
	private ControllerIndici(){}
	
	public static double computaIndiciMetodoA(String idAmbiente, String idMezzo, String idCittaSorg, String idCittaDest){
		return computaIndiciSuTuttiIViaggi(idAmbiente, idMezzo, idCittaSorg, idCittaDest);
	}

	public static double computaIndiciMetodoB(String idAmbiente, String idMezzo, String idCittaSorg, String idCittaDest){
		return computaIndiciUltimoAnno(idAmbiente, idMezzo, idCittaSorg, idCittaDest);
	}
	
	public static double computaIndiciMetodoC(String idAmbiente, String idMezzo, String idCittaSorg, String idCittaDest, String anno){
		return computaIndiciUltimaModifica(idAmbiente, idMezzo, idCittaSorg, idCittaDest, anno);
	}
	
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
		int anno = calendario.get(Calendar.YEAR);
		
		return computaIndiciGercarchici(idAmbiente, idMezzo, idCittaSorg, idCittaDest, anno + "");
	}

	private static double computaIndiciUltimaModifica(String idAmbiente, String idMezzo, String idCittaSorg, String idCittaDest, String anno){
		return computaIndiciGercarchici(idAmbiente, idMezzo, idCittaSorg, idCittaDest, anno);
	}
}