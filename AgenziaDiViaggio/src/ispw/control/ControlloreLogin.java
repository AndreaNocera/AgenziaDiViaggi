package ispw.control;

import ispw.entity.Utente;
import ispw.exception.DAOException;
import ispw.exception.MoreThanOneException;

public class ControlloreLogin extends Controllore{

	private static ControlloreLogin instance;

	public static ControlloreLogin getInstance() {
		if (instance == null)
			instance = new ControlloreLogin();
		return instance;
	}

	private ControlloreLogin() {
		super();
	}
	
	public boolean verificaDati(String username, char[] cs){
		if(username.equals("") || cs.equals(""))
			return false;
		return true;
	}

	/**
	 * Restituisce il ruolo se l'utente è presente, oppure null se l'utente non è presente.
	 * @param username
	 * @param cs
	 * @return Ruolo
	 * @throws MoreThanOneException
	 * @throws DAOException
	 */
	public String login(String username, char[] passwordAsCharacters) throws MoreThanOneException, DAOException {
		// richiedendo il DAO dal controller, quest'ultimo è vincolato al
		// particolare sistema di persistenza utilizzato dal DAO. Cambiare il
		// sistema di persistenza, significa cambiare il DAO da utilizzare e
		// modificare il controllore (potenzilamente, anche molto pesantemente,
		// e.g. la gestione delle eccezioni)
		String password = new String(passwordAsCharacters);
		Utente utente = Utente.findByNameAndPassword(username, password);
		if(utente == null)
			return null;
		return utente.getRuolo();
	}
}
