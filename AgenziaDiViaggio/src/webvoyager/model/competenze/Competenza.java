/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model.competenze
 * 
 * @name Competenza.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package webvoyager.model.competenze;

import java.io.Serializable;

public interface Competenza extends Serializable {
	
	public static final int LOGIN = 0;
	public static final int GESTIONE_PROFILO = 1;
	public static final int AMMINISTRAZIONE_UTENTI = 2;
	public static final int GESTIONE_CATALOGO = 3;
	public static final int GESTIONE_OFFERTA = 4;
	
	int getId();
	
	String asString();
	
	String asCompactString();
	
}
