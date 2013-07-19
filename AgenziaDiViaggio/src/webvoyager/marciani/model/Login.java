/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model
 * 
 * @name Login.java
 *
 * @description
 *Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.marciani.model;

import java.io.Serializable;

import webvoyager.marciani.exception.BeanInconsistenteException;
import webvoyager.marciani.exception.LoginInconsistenteException;
import webvoyager.marciani.model.bean.LoginBean;

public class Login implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
	public Login(String username, String password) throws LoginInconsistenteException {
		this.setUsername(username);
		this.setPassword(password);
	}
	
	public Login() {
		
	}
	
	public boolean equals(Login other) {
		return (this.getUsername().equals(other.getUsername()) && this.getPassword().equals(other.getPassword()));
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) throws LoginInconsistenteException {
		if (username.isEmpty() || username == null) {
			throw new LoginInconsistenteException();
		}
		
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws LoginInconsistenteException {
		if (password.isEmpty() || password == null) {
			throw new LoginInconsistenteException();
		}
		
		this.password = password;
	}
	
	public Login fromBean(LoginBean bean) throws BeanInconsistenteException  {
		try {
			return new Login(bean.getUsername(), bean.getPassword());
		} catch (LoginInconsistenteException exc) {
			throw new BeanInconsistenteException();
		}
		
	}
}
