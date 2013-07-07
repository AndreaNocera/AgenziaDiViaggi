/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model.ruoli
 * 
 * @name Ruolo.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package gestioneutenti.model.ruoli;

import java.io.Serializable;

import gestioneutenti.model.competenze.Competenza;

public interface Ruolo extends Comparable<Ruolo>, Serializable {
	
	int NONE = -1;
	int AMMINISTRATORE = 0;
	int PROMOTORE = 1;
	int PROGETTISTA = 2;
	int VENDITORE = 3;
	int CLIENTE = 4;
	int VISITATORE = 5;	
	
	int getId();
	
	String asString();
	
	Competenza[] getCompetenze();

}
