package ispw.test;

import ispw.dao.DAOTraveler;
import ispw.entity.Traveler;
import ispw.exception.DAOException;

public class TestTraveler {
	public static void main(String[] args) throws DAOException {
			Traveler traveler = new Traveler(1, "Riccardo", "Gambella", "gambella.riccardo@gmail.com");
			DAOTraveler daoTraveler = DAOTraveler.getInstance();
			daoTraveler.insert(traveler);
			Traveler tr = daoTraveler.read(1);
			tr.print();
	}
}
