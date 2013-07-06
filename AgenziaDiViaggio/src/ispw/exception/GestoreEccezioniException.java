package ispw.exception;

public class GestoreEccezioniException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6018097198605476847L;

	public GestoreEccezioniException(){
		super();
	}
	
	public GestoreEccezioniException(String messaggio){
		super(messaggio);
	}
}
