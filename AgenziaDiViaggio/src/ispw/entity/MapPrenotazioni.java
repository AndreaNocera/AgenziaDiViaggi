package ispw.entity;

import ispw.exception.MapException;

import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MapPrenotazioni extends TreeMap<Integer, Prenotazione>{

	private static final long serialVersionUID = -1606210623577413355L;
	private final Lock lock;

	public MapPrenotazioni(){
		lock = new ReentrantLock();
	}
	
	/**
	 * Inserisce la chiave istanziando una Prenotazione.
	 */
	public void insertRecord(Integer key) {
		// TODO Auto-generated method stub
		if (!containsKey(key)) {
			lock.lock();
			super.put(key, new Prenotazione());
			lock.unlock();
		}
	}

	/**
	 * Inserisce un offerta.
	 */
	public void insertRecord(Integer key, Prenotazione prenotazione) {
		// TODO Auto-generated method stub
		if (!containsKey(key)) {
			lock.lock();
			super.put(key, prenotazione);
			lock.lock();
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
		lock.lock();
		super.remove(key);
		lock.unlock();

	}
}
