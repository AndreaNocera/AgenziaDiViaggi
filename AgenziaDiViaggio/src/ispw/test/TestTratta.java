package ispw.test;

import ispw.dao.DAOTratta;
import ispw.entity.Ambiente;
import ispw.entity.Citta;
import ispw.entity.Mezzo;
import ispw.entity.Tratta;
import ispw.entity.Via;
import ispw.exception.DAOException;
import ispw.exception.DataException;

public class TestTratta {
	public static void main(String[] args) throws DataException {
		DAOTratta daoTratta = DAOTratta.getInstance();
		Ambiente ambiente;
		Mezzo mezzo;
		Citta cittaPartenza;
		Citta cittaArrivo;
		Via via;
		Tratta tratta;
		try {
			ambiente = new Ambiente(1, "Terra");
			mezzo = new Mezzo(1, "Treno");
			cittaPartenza = new Citta(1,"Roma");
			cittaArrivo = new Citta(2,"Napoli");
			via = new Via(1,"Formia");
			tratta = new Tratta(1, ambiente, mezzo, cittaPartenza, cittaArrivo, via);
			daoTratta.insert(tratta);
			//Lettura della tratta con id 1
			/*trattaLetta = daoTratta.read(1);
			System.out.println(trattaLetta.getId() + " " + 
			trattaLetta.getAmbiente().getValore() + " " + 
			trattaLetta.getMezzo().getValore() + " " +		
			trattaLetta.getCittaPartenza().getValore() + " " +
			trattaLetta.getCittaArrivo().getValore() + " " +
			trattaLetta.getVia().getValore() + ".");
			*/
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
