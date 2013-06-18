package gestioneutenti.test;

import gestioneutenti.model.Utente;
import gestioneutenti.view.BoundaryGestisciProfilo;

import java.awt.*;

public class TestGestisciProfilo {
	
	private static final Utente SAMPLE_USER = new Utente("Giacomo", "Marciani", "Roma", "27 6 1990", "Uomo", "giacomo.marciani@gmail.com", "Amministratore", "giacomo.marciani", "password");
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				BoundaryGestisciProfilo dialog = new BoundaryGestisciProfilo(null, SAMPLE_USER);
				dialog.setVisible(true);				
			}
		});
	}
}
