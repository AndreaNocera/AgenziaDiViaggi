/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model.ruoli
 * 
 * @name Promotore.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.marciani.model.ruoli;

import webvoyager.marciani.model.competenze.Competenza;
import webvoyager.marciani.model.competenze.GestioneCatalogo;
import webvoyager.marciani.model.competenze.GestioneOfferta;
import webvoyager.marciani.model.competenze.GestioneProfilo;
import webvoyager.marciani.model.competenze.SignIn;

public class Promotore extends AbstractRuolo{
	
	private static final long serialVersionUID = 1L;

	private static Promotore singletonPromotore = null;
	
	private static int ID = Ruolo.PROMOTORE;
	private static String STRING = "Promotore";
	private static Competenza[] COMPETENZE = {GestioneCatalogo.getInstance(), GestioneOfferta.getInstance(), GestioneProfilo.getInstance(), SignIn.getInstance()};

	protected Promotore(int id, String string, Competenza[] competenze) {
		super(id, string, competenze);
	}

	public static synchronized Ruolo getInstance() {
		if (singletonPromotore == null) {
			singletonPromotore = new Promotore(ID, STRING, COMPETENZE);
			return singletonPromotore;
		}
		
		return singletonPromotore;
	}

}
