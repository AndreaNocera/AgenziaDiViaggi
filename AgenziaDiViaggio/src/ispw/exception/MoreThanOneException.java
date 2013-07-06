package ispw.exception;

public class MoreThanOneException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6114132430144125968L;
	public MoreThanOneException(){
	}
	public MoreThanOneException(String m)
	{
		super(m);
	}
}
