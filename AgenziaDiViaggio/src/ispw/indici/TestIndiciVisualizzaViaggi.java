package ispw.indici;

import java.util.Calendar;

import ispw.entity.Ambiente;
import ispw.entity.Data;
import ispw.entity.Mezzo;
import ispw.entity.Ora;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.OraException;

public class TestIndiciVisualizzaViaggi {
	public static void main(String[] args) throws DAOException, DataException, OraException {
		
		Integer idAmbiente = Ambiente.getObjectByValue("Terra").getId();
		Integer idMezzo = Mezzo.getObjectByValue("Treno TAV").getId();
		CalcoloIndici calcoloIndici = CalcoloIndici.getIstance();
		// Tratta rispetto a mezzo
		//double indiceCalcolato = calcoloIndici.computaIndiciMetodoA(idAmbiente, idMezzo, idCittaPartenza, idCittaArrivo);
		// Mezzo rispetto ad ambiente
		double indiceCalcolato = calcoloIndici.computaIndiciMetodoA(idAmbiente, idMezzo, null, null);
		System.out.println("Indice tutto:" + indiceCalcolato);
		/*indice = calcoloIndici.computaIndiciMetodoB(idAmbiente, idMezzo, idCittaPartenza, idCittaArrivo);
		System.out.println("Indice anno:" + indice);*/
		
		
		/*Creazione dell'indice e salvataggio*/
		Calendar calendar = Calendar.getInstance();
		Data data = new Data(new Integer(calendar.get(Calendar.DAY_OF_MONTH)),
				new Integer(calendar.get(Calendar.MONTH)+1));
		Ora ora = new Ora(new Integer(calendar.get(Calendar.HOUR)),new Integer(calendar.get(Calendar.MINUTE)));
		
		Indice indice = new Indice("TuttiViaggi","Traghetto","Amalfi,Genova",new Double(indiceCalcolato),
				data, ora);
		indice.save();
	
	}
}
