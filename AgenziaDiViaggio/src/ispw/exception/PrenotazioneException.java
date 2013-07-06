package ispw.exception;

public class PrenotazioneException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6018097198605476847L;

	public PrenotazioneException(){
		super();
	}
	
	public PrenotazioneException(String messaggio){
		super(messaggio);
	}
}
