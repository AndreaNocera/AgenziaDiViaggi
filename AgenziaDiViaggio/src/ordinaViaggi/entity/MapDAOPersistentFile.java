package ordinaViaggi.entity;

import ordinaViaggi.exception.DeserializzazioneException;
import ordinaViaggi.exception.MapDAOException;
import ordinaViaggi.exception.SerializzazioneException;

import java.io.File;

/**
 * @author Gambella Riccardo
 * MapDAO PersistentFile.
 */


public class MapDAOPersistentFile implements MapDAO{

	//Variabili statiche
	public static String Dir = "Data";
	public static String percorso = Dir + "/map.dat";
	
	/* Pattern Singleton */
	private static MapDAOPersistentFile mapDAO;
	
	private MapDAOPersistentFile(){
		
	}
	
	public static MapDAOPersistentFile getIstance(){
		if(mapDAO == null)
			mapDAO = new MapDAOPersistentFile();
		return mapDAO;
	}
	
	
	/*
	 * Esegue la serializzazione sul file della mappa
	 */
	@Override
	public void save(Map map) throws MapDAOException {
		// TODO Stub di metodo generato automaticamente
		SerializzaOggetti serializzatore = new SerializzaOggetti(percorso);
		try {
			serializzatore.serializza(map);
		} catch (SerializzazioneException e) {
			// TODO Blocco catch generato automaticamente
			e.printStackTrace();
		}
		
	}

	/*
	 * Esegue la deserializzazione e ottiene la Map
	 */
	
	@Override
	public Map get() throws MapDAOException {
		Map mapRead = null;
		//Mappa non Esistente, crea una nuova mappa
		if (!new File(percorso).exists()) 
		{
			mapRead = new Map();
		}
		
		//Deserializzazione della mappa
		else{
			DeserializzaOggetti deserializzatore = new DeserializzaOggetti(percorso);
			try {
				mapRead = (Map)deserializzatore.deserializza();
			} catch (DeserializzazioneException e) {
				// Lancio una MapDaoException, così vado a gestire solo quella
				// wrappando le altre eccezioni.
				throw new MapDAOException("Errore in deserializzazione");
			}
		}
		return mapRead;
	}

}
