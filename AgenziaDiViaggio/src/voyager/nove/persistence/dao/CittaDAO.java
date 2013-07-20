package voyager.nove.persistence.dao;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 * Francesco Tomei
 */

public class CittaDAO extends RawDataDAO{
	private static CittaDAO istanza = null;
	private static String tabella = "citta";

	private CittaDAO() {
		super(tabella);
	}

	public static CittaDAO getIstanza() {
		if (istanza == null)
			istanza = new CittaDAO();
		return istanza;
	}
	
}
