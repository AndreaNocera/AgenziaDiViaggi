package ordinaViaggi.test;

import ordinaViaggi.dao.DAOBiglietto;
import ordinaViaggi.entity.Biglietto;
import ordinaViaggi.entity.Traveler;
import ordinaViaggi.exception.DAOException;

public class TestBiglietto {
	public static void main(String[] args) throws DAOException {
		Traveler traveler = new Traveler(1,"Riccardo","Gambella","gambella.riccardo@gmail.com");
		traveler.save();
		Biglietto biglietto = new Biglietto(1,1,traveler);
		biglietto.print();
		biglietto.save();
		DAOBiglietto daoBiglietto = DAOBiglietto.getIstance();
		Biglietto bigliettoRead = daoBiglietto.read(1);
		bigliettoRead.print();
	}
}
