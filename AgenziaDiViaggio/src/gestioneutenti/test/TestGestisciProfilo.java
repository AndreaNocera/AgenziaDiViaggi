package gestioneutenti.test;

import gestioneutenti.model.DatiUtente;
import gestioneutenti.model.Login;
import gestioneutenti.model.Utente;
import gestioneutenti.model.ruoli.Amministratore;
import gestioneutenti.view.BoundaryGestisciProfilo;

import java.awt.*;
import java.util.GregorianCalendar;

public class TestGestisciProfilo {
	
	private static final Utente SAMPLE_USER = new Utente(new DatiUtente("Giacomo", "Marciani", "Roma", new GregorianCalendar(1990, 06, 27), "M"), "giacomo.marciani@gmail.com", Amministratore.getInstance(), new Login("giacomo.marciani", "password"));
	
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
