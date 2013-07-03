package indici.exception;

import java.sql.SQLException;

/*E' public così da essere visibile anche all'esterno del package.*/
public class ConnectionException extends SQLException{

	private static final long serialVersionUID = -7439173302799557379L;

	public ConnectionException(){
		super();
	}
	
	public ConnectionException(String messaggio){
		super(messaggio);
	}
}