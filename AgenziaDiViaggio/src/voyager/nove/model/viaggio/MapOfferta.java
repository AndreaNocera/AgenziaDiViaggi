package voyager.nove.model.viaggio;

import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import voyager.nove.exception.DataException;
import voyager.nove.exception.MapException;

public class MapOfferta extends TreeMap<Integer, Offerta> {

	private static final long serialVersionUID = 3957741829514129408L;
	private final Lock lock;
	
	public MapOfferta(){
		lock = new ReentrantLock();
	}

	public void insertRecord(Integer key) throws DataException {
		if (!containsKey(key)) {
			lock.lock();
			super.put(key, new Offerta());
			lock.unlock();
		}
	}

	public void insertRecord(Integer key, Offerta offerta) {
		if (!containsKey(key)) {
			lock.lock();
			super.put(key, offerta);
			lock.unlock();
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