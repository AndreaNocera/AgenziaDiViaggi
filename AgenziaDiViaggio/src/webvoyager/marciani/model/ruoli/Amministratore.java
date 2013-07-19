/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model.ruoli
 * 
 * @name Amministratore.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.marciani.model.ruoli;

import webvoyager.marciani.model.competenze.AmministrazioneUtenti;
import webvoyager.marciani.model.competenze.Competenza;
import webvoyager.marciani.model.competenze.GestioneCatalogo;
import webvoyager.marciani.model.competenze.GestioneOfferta;
import webvoyager.marciani.model.competenze.GestioneProfilo;
import webvoyager.marciani.model.competenze.SignIn;

public class Amministratore extends AbstractRuolo {
	
	private static final long serialVersionUID = 1L;

	private static Amministratore singletonAmministratore = null;
	
	static int ID = Ruolo.AMMINISTRATORE;
	static String STRING = "Amministratore";
	static Competenza[] COMPETENZE = {AmministrazioneUtenti.getInstance(), GestioneCatalogo.getInstance(), GestioneOfferta.getInstance(), GestioneProfilo.getInstance(), SignIn.getInstance()};

	protected Amministratore(int id, String string, Competenza[] competenze) {
		super(id, string, competenze);
	}
	
	public static synchronized Amministratore getInstance() {
		if (singletonAmministratore == null) {
			singletonAmministratore = new Amministratore(ID, STRING, COMPETENZE);
			return singletonAmministratore;
		}
		
		return singletonAmministratore;
	}

}
