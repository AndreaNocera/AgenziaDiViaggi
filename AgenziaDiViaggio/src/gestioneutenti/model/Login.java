package gestioneutenti.model;

/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model
 * 
 * @name SignIn.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

import gestioneutenti.exception.LoginInconsistenteException;

public class Login {
	
	private String username;
	private String password;
	
	public Login(String username, String password) throws LoginInconsistenteException {
		this.setUsername(username);
		this.setPassword(password);
	}
	
	public boolean equals(Login other) {
		return (this.getUsername().equals(other.getUsername()) && this.getPassword().equals(other.getPassword()));
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) throws LoginInconsistenteException {
		if (username.isEmpty()) {
			throw new LoginInconsistenteException();
		}
		
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws LoginInconsistenteException {
		if (password.isEmpty()) {
			throw new LoginInconsistenteException();
		}
		
		this.password = password;
	}
}
