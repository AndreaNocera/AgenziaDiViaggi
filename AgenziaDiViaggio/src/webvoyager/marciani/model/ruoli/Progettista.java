/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model.ruoli
 * 
 * @name Progettista.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.marciani.model.ruoli;

import webvoyager.marciani.model.competenze.Competenza;
import webvoyager.marciani.model.competenze.GestioneProfilo;
import webvoyager.marciani.model.competenze.SignIn;

public class Progettista extends AbstractRuolo{
	
	private static final long serialVersionUID = 1L;

	private static Progettista singletonProgettista = null;
	
	private static int ID = Ruolo.PROGETTISTA;
	private static String STRING = "Progettista";
	private static Competenza[] COMPETENZE = {GestioneProfilo.getInstance(), SignIn.getInstance()};

	protected Progettista(int id, String string, Competenza[] competenze) {
		super(id, string, competenze);
	}

	public static synchronized Ruolo getInstance() {
		if (singletonProgettista == null) {
			singletonProgettista = new Progettista(ID, STRING, COMPETENZE);
			return singletonProgettista;
		}
		
		return singletonProgettista;
	}

}
