/**
 * @author Luca Paoli e Jessica Lucia e Gambella Riccardo
 */

package ispw.indici;

import java.util.Calendar;

import ispw.indici.DAOCalcoloIndici;

/*
 * Classe che permette di computare gli indici di gradimento dei viaggi.
 * Tale classe fa uso del DB progettato da Riccardo Gambella.
 * Per computare gli indici occorre passare alle funzioni statiche gli id di Ambiente, Mezzo, e delle Citta di partenza e destinazione.
 * Tali id possono essere ricavati mediante i DAO che accedono al DB suddetto.
 */

public class CalcoloIndici {

	private static CalcoloIndici instance;

	private CalcoloIndici() {
	}

	public static CalcoloIndici getIstance() {
		if (instance == null)
			instance = new CalcoloIndici();
		return instance;
	}

	public double computaIndiciMetodoA(Integer idAmbiente, Integer idMezzo,
			Integer idCittaPartenza, Integer idCittaArrivo) {

		// Calcolo Tratta/Mezzo
		if (idCittaPartenza == null) {
			return computaIndiciSuTuttiIViaggi(idAmbiente.toString(),
					idMezzo.toString(), null, null);
		}
		// Calcolo Mezzo/Ambiente
		return computaIndiciSuTuttiIViaggi(idAmbiente.toString(),
				idMezzo.toString(), idCittaPartenza.toString(),
				idCittaArrivo.toString());
	}

	public double computaIndiciMetodoB(Integer idAmbiente, Integer idMezzo,
			Integer idCittaPartenza, Integer idCittaArrivo) {
		// Calcolo Tratta/Mezzo
		if (idCittaPartenza == null) {
			return computaIndiciUltimoAnno(idAmbiente.toString(),
					idMezzo.toString(), null, null);
		}
		return computaIndiciUltimoAnno(idAmbiente.toString(),
				idMezzo.toString(), idCittaPartenza.toString(),
				idCittaArrivo.toString());
	}

	/*
	 * Computa gli indici in maniera gerarchica, tenendo conto delle
	 * classi/superclassi.
	 */
	private double computaIndiciGerarchici(String idAmbiente, String idMezzo,
			String idCittaSorg, String idCittaDest, String anno) {
		DAOCalcoloIndici dao = DAOCalcoloIndici.getInstance();

		int numeratore;
		int denominatore;

		/*
		 * Computa l'indice di livello più basso: biglietti dei viaggi venduti
		 * per una tratta con un dato mezzo diviso biglietti venduti per quel
		 * mezzo.
		 */
		if (idCittaSorg != null && idCittaDest != null) {
			numeratore = dao.getCountBiglietti(idAmbiente, idMezzo,
					idCittaSorg, idCittaDest, anno);
			denominatore = dao.getCountBiglietti(idAmbiente, idMezzo, null,
					null, anno);
		}
		/*
		 * Computa l'indice di livello intermedio: biglietti venduti per un
		 * ambiente e un dato mezzo diviso biglietti venduti per quell'ambiente.
		 */
		else if (idMezzo != null) {
			numeratore = dao.getCountBiglietti(idAmbiente, idMezzo, null, null,
					anno);
			denominatore = dao.getCountBiglietti(idAmbiente, null, null, null,
					anno);
		}
		/*
		 * Computa l'indice di livello piÃ¹ alto: biglietti venduti per un dato
		 * ambiente diviso il numero di biglietti totali.
		 */
		else {
			numeratore = dao.getCountBiglietti(idAmbiente, null, null, null,
					anno);
			denominatore = dao.getCountBiglietti(null, null, null, null, anno);
		}

		// Se non ci sono prenotazioni relative alla classe selezionata.
		if (denominatore == 0)
			return 0;
		return (double) numeratore / (double) denominatore;
	}

	private double computaIndiciSuTuttiIViaggi(String idAmbiente,
			String idMezzo, String idCittaSorg, String idCittaDest) {
		return computaIndiciGerarchici(idAmbiente, idMezzo, idCittaSorg,
				idCittaDest, null);
	}

	private double computaIndiciUltimoAnno(String idAmbiente, String idMezzo,
			String idCittaSorg, String idCittaDest) {
		Calendar calendar = Calendar.getInstance();
		int anno = calendar.get(Calendar.YEAR); // Setto all'anno corrente
		return computaIndiciGerarchici(idAmbiente, idMezzo, idCittaSorg,
				idCittaDest, anno + "");
	}

}






