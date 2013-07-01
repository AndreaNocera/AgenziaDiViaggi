package ordinaViaggi.test;

import ordinaViaggi.dao.DAOTraveler;
import ordinaViaggi.entity.Traveler;
import ordinaViaggi.exception.DAOException;

public class TestTraveler {
	public static void main(String[] args) throws DAOException {
			Traveler traveler = new Traveler(1, "Riccardo", "Gambella", "gambella.riccardo@gmail.com");
			DAOTraveler daoTraveler = DAOTraveler.getIstance();
			daoTraveler.insert(traveler);
			Traveler tr = daoTraveler.read(1);
			tr.print();
	}
}
