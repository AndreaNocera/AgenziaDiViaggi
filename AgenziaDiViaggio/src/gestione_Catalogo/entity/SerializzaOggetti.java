/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestione_Catalogo.entity;
import java.io.File;
//import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import gestione_Catalogo.exception.SerializzazioneException;


public class SerializzaOggetti{
	private String percorso;
	
	public SerializzaOggetti(String percorso){
		this.percorso = percorso;
	}

	public void serializza(Object obj) throws SerializzazioneException{
		try {
			FileOutputStream   f = new FileOutputStream (new File(percorso));
			ObjectOutputStream s = new ObjectOutputStream (f);
			s.writeObject (obj);
			s.flush();
			s.close ();
		} catch (IOException e){
			throw new SerializzazioneException("\n SerializzaOggetti. Errore file durante serializzazione. ");
		}
	}
}