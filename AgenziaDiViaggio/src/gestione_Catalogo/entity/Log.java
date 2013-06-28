package gestione_Catalogo.entity;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class Log {
	
	//attributi di classe
	private static Log istanza;
	
	//costruttore
	private Log() {
		
	}
	
	// metodi
	public static Log getIstanza(){
		if (istanza == null){
			istanza = new Log();
		}
		return istanza;
	}


}
