package gestione_Catalogo.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import gestione_Catalogo.exception.DeserializzazioneException;


//import javax.swing.JOptionPane;

public class DeserializzaOggetti{
	
	private String percorso;
	
	public DeserializzaOggetti(String percorso){
		this.percorso = percorso;
	}
	
	public Object deserializza() throws DeserializzazioneException {
		try {
			FileInputStream f =	new FileInputStream (new File(percorso));
			ObjectInputStream s = new ObjectInputStream (f);
			Object obj = (Object) s.readObject();
			s.close ();
			return obj;
		} catch (Exception e) {
			throw new DeserializzazioneException("Deserializza.deserializza(); oggetto non caricabile!");
		}
	}
}