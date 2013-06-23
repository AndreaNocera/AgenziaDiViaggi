package ordinaViaggi.entity;

import java.util.Collection;

import ordinaViaggi.exception.MapDAOException;

public class TestDAOMySQL {
	
	
	/*
	 * Scende in ricorsione nella mappa e stampa le chiavi (Nomi). Usa il
	 * NumSpazi solo per dare un effetto grafico. Questo metodo è solo per
	 * verifica del funzionamento.
	 */
	private static void printMapRecursive(Map map, int levelReached, int level) {
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
	public static void main(String[] args) {
		MAPDAOMySQL dao = MAPDAOMySQL.getIstance();
		try {
			Map map = dao.get();
			printMapRecursive(map, 0, 5);
		} catch (MapDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
