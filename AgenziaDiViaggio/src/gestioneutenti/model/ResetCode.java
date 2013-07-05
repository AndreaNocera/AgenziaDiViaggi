/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model
 * 
 * @name ResetPassword.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package gestioneutenti.model;

import java.util.Date;

public class ResetCode {
	
	private int codice;
	private Date timestamp;
	
	public ResetCode(int codice, Date timestamp) {
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
