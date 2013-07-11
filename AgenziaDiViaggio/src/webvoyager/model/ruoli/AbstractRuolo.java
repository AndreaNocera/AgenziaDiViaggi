/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model.ruoli
 * 
 * @name AbstractRuolo.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.model.ruoli;

import webvoyager.model.competenze.Competenza;

public abstract class AbstractRuolo implements Ruolo{
	
	private static final long serialVersionUID = 1L;
	
	private int ID;
	private String STRING = new String();
	private Competenza[] COMPETENZE = null;
	
	protected AbstractRuolo(int id, String string, Competenza[] competenze) {
		ID = id;
		STRING = string;
		COMPETENZE = competenze;
	}

	@Override
	public int getId() {
		return ID;
	}

	@Override
	public String asString() {
		return STRING;
	}

	@Override
	public Competenza[] getCompetenze() {
		return COMPETENZE;
	}
	
	@Override
	public int compareTo(Ruolo other) {
		if (this.getId() < other.getId()) {
			return 1;
		} else if (this.getId() > other.getId()) {
			return -1;
		} else {
			return 0;
		}
	}

}
