package gestioneutenti.model;

import gestioneutenti.exception.LoginException;

public class Login {
	
	private String username;
	private String password;
	
	public Login(String username, String password) throws LoginException {
		this.setUsername(username);
		this.setPassword(password);
	}
	
	public boolean equals(Login other) {
		return (this.getUsername().equals(other.getUsername()) && this.getPassword().equals(other.getPassword()));
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) throws LoginException {
		if (username.isEmpty()) {
			throw new LoginException();
		}
		
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws LoginException {
		if (password.isEmpty()) {
			throw new LoginException();
		}
		
		this.password = password;
	}
}
