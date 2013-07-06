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
		/*
		 * Per aprire la pagina di login
		 * 1) avviare il server
		 * 2) fare il deploy del progetto sul server (senza 
		 * dimenticare di includere il driver mysql nel build
		 * path e renderlo esportabile durante il deploy).
		 * 3) visitare il seguente link (eventualmente cambiando
		 * il numero di porta dell'application server, il nome
		 * del progetto ed il nome della pagina):
		 * http://localhost:8080/LoginExample/LoginPage.jsp
		 * 
		 * Per effettuare il deploy del progetto, occorre
		 * installare un application server (e.g. Tomcat),
		 * crearne un'istanza dentro eclipse e selezionare
		 * quali progetti dovranno girare su quella istanza
		 * di server.
		 * Per rendere esportabile il driver mysql, dopo averlo
		 * aggiunto alla build path del progetto, selezionare il 
		 * tab "order and export" (sempre nella finestra del build
		 * path) e spuntare il jar del driver.
		 * 
		 * Se questo non dovesse funzionare (ovvero il caricamento
		 * della classe del driver dovesse fallire): Project -> 
		 * Properties -> Deployment Assembly -> Add -> Project e 
		 * selezionate il jar.
		 * 
		 * Non dimenticate di creare il database, la tabella e di
		 * aggiungere una entry per fare il test (nel progetto 
		 * trovate lo script sql). Verificare username, password ed
		 * URL del db.
		 * 
		 */
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
