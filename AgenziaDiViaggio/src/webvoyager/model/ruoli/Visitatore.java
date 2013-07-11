/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model.ruoli
 * 
 * @name Visitatore.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.model.ruoli;

import webvoyager.model.competenze.Competenza;
import webvoyager.model.competenze.GestioneProfilo;
import webvoyager.model.competenze.SignIn;

public class Visitatore extends AbstractRuolo {
	
	private static final long serialVersionUID = 1L;

	private static Visitatore singletonVisitatore = null;
	
	private static int ID = Ruolo.VISITATORE;
	private static String STRING = "Visitatore";
	private static Competenza[] COMPETENZE = {GestioneProfilo.getInstance(), SignIn.getInstance()};	

	protected Visitatore(int id, String string, Competenza[] competenze) {
		super(id, string, competenze);
	}

	public static synchronized Ruolo getInstance() {
		if (singletonVisitatore == null) {
			singletonVisitatore = new Visitatore(ID, STRING, COMPETENZE);
			return singletonVisitatore;
		}
		
		return singletonVisitatore;
	}
	
}
