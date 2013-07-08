package ispw.test;

import ispw.entity.Utente;
import ispw.exception.DAOException;
import ispw.exception.MoreThanOneException;

public class TestUtente {
	public static void main(String[] args) throws DAOException, MoreThanOneException {
		Utente utente = new Utente("gamby4ever", "lol", "Riccardo", "Gambella", "Amministratore");
		utente.save();
		utente = Utente.findByNameAndPassword("gamby4ever", "lol");
		utente.print();
	}
}
