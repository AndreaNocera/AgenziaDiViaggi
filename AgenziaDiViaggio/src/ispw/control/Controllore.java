package ispw.control;

import java.util.Iterator;
import java.util.List;

/**
 * @author Gambella Riccardo Controllore astratto.
 */

public abstract class Controllore{

	public Controllore() {
	}

	public Controllore(String s) {

	}

	public boolean verificaDati(List<String> list) {
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			if (it.next().equals("--"))
				return false;
		}
		return true;
	}
	
}
