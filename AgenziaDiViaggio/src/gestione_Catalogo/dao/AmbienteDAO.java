package gestione_Catalogo.dao;


/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 * Francesco Tomei
 */
public class AmbienteDAO extends RawDataDAO {

	private static AmbienteDAO istanza = null;
	private static String tabella = "ambiente";

	private AmbienteDAO() {
		super(tabella);
	}

	public static AmbienteDAO getIstanza() {
		if (istanza == null)
			istanza = new AmbienteDAO();
		return istanza;
	}
}
