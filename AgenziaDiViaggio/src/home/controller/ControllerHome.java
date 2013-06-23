package home.controller;

import javax.swing.JPanel;

import gestioneutenti.model.Utente;
import gestioneutenti.model.competenze.Competenza;
import gestioneutenti.view.BoundaryAmministraUtenti;
import gestioneutenti.view.BoundaryGestisciProfilo;

public class ControllerHome {
	
	public JPanel getBoundary(int competenza, Utente utente) {
		switch(competenza) {
		case Competenza.GESTIONE_PROFILO:
			return new BoundaryGestisciProfilo(utente);
		case Competenza.AMMINISTRAZIONE_UTENTI:
			return new BoundaryAmministraUtenti();
		default:
			//da cambiare
			return new BoundaryAmministraUtenti();
		}
	}

}
