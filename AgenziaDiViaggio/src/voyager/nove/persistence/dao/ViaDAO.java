package voyager.nove.persistence.dao;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 * Francesco Tomei
 */
public class ViaDAO extends RawDataDAO{
	
	private static ViaDAO istanza = null;
	private static String tabella = "via";
	
	private ViaDAO() {
		super(tabella);
	}

	public static ViaDAO getIstanza() {
		if (istanza == null)
			istanza = new ViaDAO();
		return istanza;
	}
	
}
