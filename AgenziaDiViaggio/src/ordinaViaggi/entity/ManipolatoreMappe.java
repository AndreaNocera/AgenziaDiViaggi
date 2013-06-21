package ordinaViaggi.entity;

import ordinaViaggi.exception.MapDAOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ManipolatoreMappe {
	
	protected Map readMap() throws MapDAOException {
		Map map = new Map();
		return map.get();
	}

	/* Salva una mappa, visibile solo dai controllori. */
	protected void saveMap(Map map) throws MapDAOException {
		map.save();
	}

	private SubElement getSubElementRecursive(Map map,
			List<String> selectedList, String keyToFind) throws MapDAOException {
		// System.out.println("Entrata in estrazioneRec");

		/* Se selezione è vuota sono arrivato al punto di estrazione */
		if (selectedList.isEmpty()) {
			Collection<String> collection = map.keySet();
			// Verifica se la chiave da trovare è presente
			if (!collection.contains(keyToFind))
				throw new MapDAOException("Chiave non presente nella mappa.");
			else {
				SubElement subElement = map.get(keyToFind);
				return subElement;
			}
		}

		// Seleziona l'elemento selezionato dalla ComboBox
		String selected = selectedList.get(0);
		selectedList.remove(0);

		// Controllo Errore se la chiave non è presente
		if (!map.containsKey(selected)) {
			System.out.println("Tento di cercare" + selected);
			System.out.println("Error. Key not found!");
		}
		SubElement sub = map.get(selected);
		Map secondMap = sub.getMap();

		return getSubElementRecursive(secondMap, selectedList, keyToFind);

	}

	private SubElement getSubElement(List<String> selectedList,
			String key) throws MapDAOException {
		List<String> selectedListcopy = new ArrayList<String>(selectedList);
		return getSubElementRecursive(readMap(), selectedListcopy, key);
	}

	/*
	 * Ottiene l'info del SubElement relativo alla chiave key
	 */
	public String getInfo(List<String> selectedList, String key)
			throws MapDAOException {
		/*
		 * Copia della lista in un altro reference, per evitare la distruzione
		 * di selectedList. Da migliorare con clone() in ArrayList().
		 */
		List<String> selectedListcopy = new ArrayList<String>(selectedList);
		return getSubElement(selectedListcopy, key).getInfo();
	}

	public List<String> getTicket(List<String> selectedList, String key)
			throws MapDAOException {
		/*
		 * Copia della lista in un altro reference, per evitare la distruzione di selectedList.
		 * Da migliorare con clone() in ArrayList().
		 */
		List<String> selectedListcopy = new ArrayList<String>(selectedList);
		SubElement subElement = getSubElement(selectedListcopy, key);
		if(subElement instanceof SubElementBiglietto){
			Biglietto biglietto = subElement.getBiglietto();
			List<String> dati = new ArrayList<String>();
			// Crea i dati del biglietto
			dati.add(biglietto.getTravelers().get(0).getName());
			dati.add(biglietto.getTravelers().get(0).getSurname());
			return dati;
			
		}
		throw new MapDAOException();
		
	}

	private void estrazioneMappaRecursive(Map map,
			List<String> selectedList, List<String> mapList) {
		// System.out.println("Entrata in estrazioneRec");

		/* Se selezione è vuota sono arrivato al punto di estrazione */
		if (selectedList.isEmpty()) {
			Collection<String> collection = map.keySet();
			for (String key : collection)
				mapList.add(key);
			return;
		}

		// Seleziona l'elemento selezionato dalla ComboBox
		String selected = selectedList.get(0);
		selectedList.remove(0);

		// Controllo Errore se la chiave non è presente
		if (!map.containsKey(selected)) {
			System.out.println("Tento di cercare" + selected);
			System.out.println("Error. Key not found!");
		}
		SubElement sub = map.get(selected);
		Map secondMap = sub.getMap();

		estrazioneMappaRecursive(secondMap, selectedList, mapList);

	}

	public void estrazioneMappa(List<String> selectedList,
			List<String> mapList) throws MapDAOException {

		/*
		 * Estrazione delle chiavi della mappa Copia della lista in un altro
		 * reference, per evitare la distruzione di selectedList. Da migliorare
		 * con clone() in ArrayList().
		 */
		List<String> selectedListcopy = new ArrayList<String>(selectedList);
		estrazioneMappaRecursive(readMap(), selectedListcopy, mapList);

	}

	/* Metodi di gestione delle mappe */
	/*
	 * Inserimento ricorsivo nella mappa. Non gli viene passato il SubElement,
	 * ne crea uno vuoto.
	 */
	protected void inserimentoInMapRecursive(Map map,
			List<String> record, List<SubElement> listaSubElements)
			throws MapDAOException {
		if (record.size() != listaSubElements.size())
			throw new MapDAOException("Errore in inserimentoInMapRecursive");
		String key = record.remove(0);
		SubElement subElement = listaSubElements.remove(0);
		/* Verifico che la chiave sia presente, altrimenti la creo */
		if (!map.containsKey(key)) {
			map.insertRecord(key, subElement);
			System.out.println("Inserimento della chiave: " + key);
		}
		SubElement sub = map.get(key);
		Map secondmap = sub.getMap();
		/*
		 * Per come ho implementato ogni volta che creo un SubElement creo anche
		 * il rif alla mappa E' un errore quindi che una mappa sia null.
		 */
		if (secondmap == null) {
			System.out.println("Errore mappa non può essere null.");
			secondmap = new Map();
		}

		/*
		 * Continuo a scendere in profondità se non ho inserito la città di
		 * arrivo.
		 */
		if (record.size() != 0)
			inserimentoInMapRecursive(secondmap, record, listaSubElements);

	}

	/*
	 * Rimozione di un elemento nella mappa. Se la mappa successiva è vuota,
	 * cancella l'elemento selezionato in quanto non punta a niente
	 * successivamente. La divisione tra catalogo e offerta è data dal oggetto a
	 * runtime di SubElement.
	 */
	protected void rimozioneInMapRecursive(Map map, List<String> record,
			String richiedente) throws MapDAOException {

		String key = record.remove(0);
		/* Verifico che la chiave sia presente, altrimenti genero un eccezione */
		if (!map.containsKey(key)) {
			throw new MapDAOException("Errore in rimozioneInMapRecursive");
		}
		SubElement sub = map.get(key);
		Map secondmap = sub.getMap();
		/*
		 * Per come ho implementato ogni volta che creo un SubElement creo anche
		 * il rif alla mappa E' un errore quindi che una mappa sia null.
		 */
		if (secondmap == null) {
			System.out.println("Errore mappa non può essere null.");
			secondmap = new Map();
		}

		/* Punto di rimozione raggiunto */
		if (record.size() == 0) {
			map.remove(key);
			return;
		}

		/*
		 * Continuo a scendere in profondità se non ho inserito la città di
		 * arrivo.
		 */
		else {
			rimozioneInMapRecursive(secondmap, record, richiedente);
			/*
			 * Dopo aver cancellato la mappa sottostante verifico che ci siano
			 * elementi nella mappa puntata . Se non ci sono cancello l'elemento
			 * selezionato.
			 */

			// Con il catalogo rimuovo sempre
			if (secondmap.isEmpty() && richiedente.equals("Catalogo")) {
				map.remove(key);
			}
			// Con l'offerta rimuovo solo se non entro nel catalogo
			else if (secondmap.isEmpty() && richiedente.equals("Offerta")) {
				/*
				 * Verifico di non essere arrivato al catalogo. Ciò è verificato
				 * fintanto che non trovo SubElementCatalogo
				 */
				if ((sub instanceof SubElementCatalogo)) {
					return;
				} else {
					map.remove(key);
				}
			}
			return;
		}
	}

	/*
	 * Scende in ricorsione nella mappa e stampa le chiavi (Nomi). Usa il
	 * NumSpazi solo per dare un effetto grafico. Questo metodo è solo per
	 * verifica del funzionamento.
	 */
	protected void printMapRecursive(Map map, int levelReached, int level) {
		// Livello voluto raggiunto
		if (levelReached == level)
			return;

		if (map == null) {
			// System.out.println("Errore: Mappa null.");
			return;
		}
		if (map.isEmpty()) {
			// System.out.println("Mappa vuota.");
			return;
		}
		Collection<String> collection = map.keySet();
		for (String key : collection) {
			/* Per tabulazione */
			for (int i = 0; i < levelReached; i++)
				System.out.print("  ");
			System.out.println(key);
			SubElement sub = map.get(key);

			// Get della mappa successiva.
			Map secondMap = sub.getMap();
			/* Ricorsione sulla mappa stampa della mappa di livello successivo */
			printMapRecursive(secondMap, levelReached + 1, level);
		}
	}
}
