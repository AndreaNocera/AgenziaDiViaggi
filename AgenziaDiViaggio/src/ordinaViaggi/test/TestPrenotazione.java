package ordinaViaggi.test;

import ordinaViaggi.entity.Biglietto;
import ordinaViaggi.entity.Prenotazione;
import ordinaViaggi.entity.Traveler;
import ordinaViaggi.exception.DAOException;
import ordinaViaggi.exception.PrenotazioneException;

public class TestPrenotazione {
	public static void main(String[] args) throws DAOException, PrenotazioneException{
		Traveler traveler = new Traveler(1,"Riccardo","Gambella","gambella.riccardo@gmail.com");
		traveler.save();
		Biglietto biglietto = new Biglietto(1,1,traveler);
		biglietto.save();
		Prenotazione prenotazione = new Prenotazione(1, 1, "cliente");
		prenotazione.addBiglietto(biglietto);
		prenotazione.save();
		Biglietto bigliettoRead = prenotazione.getBigliettoById(1);
		bigliettoRead.print();
		bigliettoRead = prenotazione.getBigliettoByValue(traveler);
		bigliettoRead.print();
		
		
	}
}
