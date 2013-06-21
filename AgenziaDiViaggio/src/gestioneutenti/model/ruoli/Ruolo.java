package gestioneutenti.model.ruoli;

import gestioneutenti.model.Competenza;

public interface Ruolo extends Comparable<Ruolo> {
	
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
