package gestione_Catalogo.dao;


/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 * Francesco Tomei
 */
public class MezzoDAO extends RawDataDAO {
	private static MezzoDAO istanza = null;
	private static String tabella = "mezzo";
	
	private MezzoDAO() {
		super(tabella);
	}
	
	public static MezzoDAO getIstanza() {
		if (istanza == null)
			istanza = new MezzoDAO();
		System.out.println("istanza");
		return istanza;
	}
}
