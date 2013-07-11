/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model.ruoli
 * 
 * @name Venditore.java
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

public class Venditore extends AbstractRuolo{
	
	private static final long serialVersionUID = 1L;

	private static Venditore singletonVenditore = null;
	
	private static int ID = Ruolo.VENDITORE;
	private static String STRING = "Venditore";
	private static Competenza[] COMPETENZE = {GestioneProfilo.getInstance(), SignIn.getInstance()};

	protected Venditore(int id, String string, Competenza[] competenze) {
		super(id, string, competenze);
	}

	public static synchronized Ruolo getInstance() {
		if (singletonVenditore == null) {
			singletonVenditore = new Venditore(ID, STRING, COMPETENZE);
			return singletonVenditore;
		}
		
		return singletonVenditore;
	}

}
