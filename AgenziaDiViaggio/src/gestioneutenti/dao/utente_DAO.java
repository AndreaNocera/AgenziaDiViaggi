package gestioneutenti.dao;
import gestioneutenti.exception.*;
import gestioneutenti.model.Login;

/**
 * @author <GRUPPO 9>
 * L'interfaccia utente_DAO sarà implementata dai DataAccesObjects specifici per ciascun tipo/tecnologia di persistenza.
 */
public interface utente_DAO {
	 
	void get(Login login);
	public void update();
	void save() throws UtenteEsistenteException;
	
	
}
