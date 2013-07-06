package ispw.exception;

public class CatalogoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6018097198605476847L;

	public CatalogoException(){
		super();
	}
	
	public CatalogoException(String messaggio){
		super(messaggio);
	}
}
