/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model.bean
 * 
 * @name LoginBean.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.model.bean;

import java.io.Serializable;

public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;

	public LoginBean() {}

	public String getUsername() {
		return this.username;
	}

	public LoginBean setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return this.password;
	}

	public LoginBean setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public String asJson() {
		String str = new String();
		
		str += this.getUsername() + ":" + this.getPassword();
		
		return str;
	}	

}
