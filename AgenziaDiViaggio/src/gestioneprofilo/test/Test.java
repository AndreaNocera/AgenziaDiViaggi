package gestioneprofilo.test;

import gestioneprofilo.model.Utente;
import gestioneprofilo.view.BoundaryGestioneProfilo;

import java.awt.*;

public class Test {
	
	private static final Utente SAMPLE_USER = new Utente("Giacomo", "Marciani", "Roma", "27 6 1990", "Uomo", "giacomo.marciani@gmail.com", "Amministratore", "giacomo.marciani", "password");
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				BoundaryGestioneProfilo dialog = new BoundaryGestioneProfilo(null, SAMPLE_USER);
				dialog.setVisible(true);				
			}
		});
	}
}
