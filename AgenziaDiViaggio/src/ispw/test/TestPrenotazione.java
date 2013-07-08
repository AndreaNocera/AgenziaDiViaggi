package ispw.test;

import ispw.entity.Biglietto;
import ispw.entity.Prenotazione;
import ispw.entity.Traveler;
import ispw.exception.DAOException;
import ispw.exception.PrenotazioneException;

public class TestPrenotazione {
	public static void main(String[] args) throws DAOException, PrenotazioneException{
		Traveler traveler = new Traveler(1,"Riccardo","Gambella","gambella.riccardo@gmail.com");
		traveler.save();
		Biglietto biglietto = new Biglietto(1,1,traveler);
		biglietto.save();
		Prenotazione prenotazione = new Prenotazione(1, 1, "cliente");
		prenotazione.addBiglietto(biglietto);
		prenotazione.print();
		prenotazione.removeBiglietto(biglietto);
		prenotazione.print();
		
	}
}
