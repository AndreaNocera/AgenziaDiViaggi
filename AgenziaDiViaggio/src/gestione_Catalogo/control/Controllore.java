package gestione_Catalogo.control;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
import gestione_Catalogo.entity.Catalogo;
import gestione_Catalogo.entity.Log;

public abstract class Controllore {

	//attributi di classe
	protected static Catalogo catalogo;
	protected Log log;
	
	//costruttore
	public Controllore() {
		
		catalogo = Catalogo.getIstanza();
		log = new Log();
		
	}
	
}
