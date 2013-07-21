package voyager.nove.model.viaggio;

import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import voyager.nove.exception.MapException;

public class MapPrenotazioni extends TreeMap<Integer, Prenotazione>{

	private static final long serialVersionUID = -1606210623577413355L;
	private final Lock lock;

	public MapPrenotazioni(){
		lock = new ReentrantLock();
	}
	
	public void insertRecord(Integer key) {
		if (!containsKey(key)) {
			lock.lock();
			super.put(key, new Prenotazione());
			lock.unlock();
		}
	}

	public void insertRecord(Integer key, Prenotazione prenotazione) {
		if (!containsKey(key)) {
			lock.lock();
			super.put(key, prenotazione);
			lock.lock();
		}
	}

	public void removeRecord(Integer key) throws MapException {
		if (!containsKey(key)) {
			throw new MapException("Errore in rimozione. Chiave non presente.");
		}
		lock.lock();
		super.remove(key);
		lock.unlock();
	}
	
}
