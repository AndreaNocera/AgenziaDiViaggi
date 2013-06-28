package gestione_Catalogo.control;

import gestione_Catalogo.entity.Catalogo;
import gestione_Catalogo.entity.Log;

public abstract class Controllore {

	//attributi di classe
	protected static Catalogo catalogo;
	protected static Log log;
	
	//costruttore
	public Controllore() {
		
		catalogo = Catalogo.getIstanza();
		log = Log.getIstanza();
		
	}
	
}
