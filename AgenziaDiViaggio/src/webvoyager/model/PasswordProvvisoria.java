/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model
 * 
 * @name ResetPassword.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.model;

import java.util.Date;

public class PasswordProvvisoria {
	
	private int codice;
	private Date timestamp;
	
	public PasswordProvvisoria(int codice, Date timestamp) {
		this.setCodice(codice);
		this.setTimestamp(timestamp);
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
