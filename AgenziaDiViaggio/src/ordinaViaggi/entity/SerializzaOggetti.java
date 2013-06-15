package ordinaViaggi.entity;

import java.io.File;
//import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import ordinaViaggi.exception.SerializzazioneException;

public class SerializzaOggetti
{
	private String percorso;
	
	public SerializzaOggetti(String percorso)
	{
		this.percorso = percorso;
	}
	
	
	public void serializza(Object obj) throws SerializzazioneException
	{
		if(!(obj instanceof Serializable))
			throw new SerializzazioneException("Oggetto non serializzabile!");
		
		try 
		{
			FileOutputStream   f = new FileOutputStream (new File(percorso));
			ObjectOutputStream s = new ObjectOutputStream (f);
			s.writeObject (obj);
			s.flush();
			s.close ();
		}
		catch (IOException e) 
		{
			throw new SerializzazioneException
			("\n SerializzaOggetti. Errore file durante serializzazione. ");
		}
	}
}
