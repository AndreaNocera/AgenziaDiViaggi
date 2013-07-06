package ispw.entity;

import ispw.exception.MapException;

import java.util.TreeMap;

public class MapPrenotazioni extends TreeMap<Integer, Prenotazione>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2201576937326242829L;

	/**
	 * Inserisce la chiave istanziando una Prenotazione.
	 */
	public void insertRecord(Integer key) {
		// TODO Auto-generated method stub
		if (!containsKey(key)) {
			super.put(key, new Prenotazione());
		}
	}

	/**
	 * Inserisce un offerta.
	 */
	public void insertRecord(Integer key, Prenotazione prenotazione) {
		// TODO Auto-generated method stub
		if (!containsKey(key)) {
			super.put(key, prenotazione);
		}
	}

	/**
	 * Rimuove un offerta.
	 */

	public void removeRecord(Integer key) throws MapException {
		// TODO Auto-generated method stub
		if (!containsKey(key)) {
			throw new MapException("Errore in rimozione. Chiave non presente.");
		}
		super.remove(key);

	}
}
