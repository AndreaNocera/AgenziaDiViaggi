/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */


package voyager.nove.exception;

public class MappaException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public MappaException(){
		
	}
	
	public MappaException(String m){
		super(m);
	}

	public String toString(){
		return "Mappa vuota.";
	}
	

}
