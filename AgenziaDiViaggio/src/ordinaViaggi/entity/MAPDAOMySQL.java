package ordinaViaggi.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ordinaViaggi.exception.ConnectionException;
/**@author Gambella Riccardo
 * 
 */
import ordinaViaggi.exception.MapDAOException;

public class MAPDAOMySQL implements MapDAO {

	private static MAPDAOMySQL istance = null;

	private static final String driverName = "com.mysql.jdbc.Driver";
	private static final String connectionURL = "jdbc:mysql:";
	private static final String URL = connectionURL + "//" + "localhost:3306"
			+ "/";
	private static final String dbName = URL + "voyager";

	private static final String usr = "root";
	private static final String pass = "root";

	private static final String getCatalogoQuery = "SELECT *" + "FROM CATALOGO";

	private static final String createQuery = "CREATE TABLE IF NOT EXISTS CATALOGO("
			+ "IdCatalogo INT(10) PRIMARY KEY AUTO_INCREMENT, "
			+ "Ambiente VARCHAR(20), "
			+ "Mezzo VARCHAR(20), "
			+ "CittaPartenza VARCHAR(20), "
			+ "CittaArrivo VARCHAR(20), "
			+ "Via VARCHAR(20)" 
			+ ")";

	private static final String insertQuery = "INSERT INTO CATALOGO "
			+ "(Ambiente, Mezzo, CittaPartenza, CittaArrivo, Via)"
			+ "VALUES(?, ?, ?, ?, ?)";
	private static final String updateQuery = "UPDATE CATALOGO SET "
			+ "ID=?, AMBIENTE=?, MEZZO=?, CITTAPARTENZA=?, CITTAARRIVO=?, VIA=? "
			+ "WHERE ID=?";
	private static final String deleteQuery = "DELETE FROM "
			+ "CATALOGO WHERE ID=?";
	private static final String findQuery = "SELECT * FROM CATALOGO "
			+ "WHERE ID=?";

	private static final String dropQuery = "DROP TABLE CATALOGO IF EXISTS";

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private MAPDAOMySQL() {
		try {
			Class.forName(driverName);

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(createQuery);

			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResource();
		}
	}

	public static MAPDAOMySQL getIstance() {
		if (istance == null)
			istance = new MAPDAOMySQL();
		return istance;
	}

	private static Connection getConnection(String user, String password)
			throws ConnectionException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbName, user, password);
		} catch (SQLException sqle) {
			throw new ConnectionException("Errore nella connessione al DB.");
		}

		return conn;
	}

	private static void closeResource() {
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * Salva la mappa sul DB
	 */

	@Override
	public void save(Map map) throws MapDAOException {
		// TODO Auto-generated method stub
		List<String> tupla = new ArrayList<String>();
		saveMapRecursive(map, tupla, 0, 5);

	}

	/**
	 * Estrae dal database le informazioni necessarie al caricamento della
	 * mappa.
	 * 
	 */
	@Override
	public Map get() throws MapDAOException {
		Map map = new Map();
		//Preleva le tuple dalla tabella catalogo e inseriscile nella mappa
		try {
			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(getCatalogoQuery);
			rs = ps.executeQuery();

			while(rs.next()){
				//String idCatalogo = rs.getString("IDCatalogo");
				String ambiente = rs.getString("AMBIENTE");
				String mezzo = rs.getString("MEZZO");
				String cittaPartenza = rs.getString("CITTAPARTENZA");
				String cittaArrivo = rs.getString("CITTAARRIVO");
				String via = rs.getString("VIA");

				//Inserimento delle info nella mappa
				List<String> record = new ArrayList<String>();
				List<String> subElementsInfo = new ArrayList<String>();
				List<SubElement> listaSubElements = new ArrayList<SubElement>();

				record.add(ambiente);
				record.add(mezzo);
				record.add(cittaPartenza);
				record.add(cittaArrivo);
				record.add(via);

				// Crea la lista di SubElements di tipo SubElementCatalogo
				for(int i=0;i<5;i++)
					subElementsInfo.add("");


				for(String info : subElementsInfo){
					SubElement subElement = new SubElementCatalogo(new Map(),info);
					listaSubElements.add(subElement);
				}

				//Opero l'inserimento nella mappa
				inserimentoInMapRecursive(map, record, listaSubElements);
			}
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * 
	 * @param map
	 * @param record
	 * @param listaSubElements
	 * @throws MapDAOException
	 * 
	 *             Metodo per inserimento ricorsivo nella mappa.
	 */
	protected void inserimentoInMapRecursive(Map map, List<String> record,
			List<SubElement> listaSubElements) throws MapDAOException {
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
			throw new MapDAOException("Errore in inserimento Map recursive!!");
		}

		/*
		 * Continuo a scendere in profondità se non ho inserito la città di
		 * arrivo.
		 */
		if (record.size() != 0)
			inserimentoInMapRecursive(secondmap, record, listaSubElements);

	}

	/**
	 * 
	 * @param map
	 * @param levelReached
	 * @param level
	 * Metodo di salvataggio ricorsivo della mappa nel DB
	 */
	private static void saveMapRecursive(Map map, List<String> tupla, int levelReached, int level) {
		// Livello voluto raggiunto. Salvataggio della tupla nel DB
		if (levelReached == level){
			String ambiente = tupla.get(0);
			String mezzo = tupla.get(1);
			String cittaPartenza = tupla.get(2);
			String cittaArrivo = tupla.get(3);
			String via = tupla.get(4);
			System.out.println("Tupla da inserire: " + ambiente + " " + mezzo + 
					" " + cittaPartenza + " " + cittaArrivo + " " + via);
			try { 
				conn = getConnection(usr, pass);
				
				ps = conn.prepareStatement(insertQuery);
	
				ps.setString(1, ambiente); 
				ps.setString(2, mezzo);
				ps.setString(3, cittaPartenza); 
				ps.setString(4, cittaArrivo);
				ps.setString(5, via);

				ps.executeUpdate(); 
			} 
			catch (ConnectionException e) { 
				// TODO Auto-generated catch block 
				e.printStackTrace(); } 
			catch (SQLException e){ 
				// TODO Auto-generated catch block 
				e.printStackTrace(); 
			} 
			finally {
				closeResource(); 
			}
		}

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

		//Inserisce la chiave nella tupla, per inserimento successivo nel DB.
		tupla.add(key);
		SubElement sub = map.get(key);

		// Get della mappa successiva.
		Map secondMap = sub.getMap();
		/* Ricorsione sulla mappa stampa della mappa di livello successivo */
		saveMapRecursive(secondMap, tupla,  levelReached + 1, level);
	}
}

/*
 * @Override public void create(CatalogoBean dato) { if (dato == null)
 * return;
 * 
 * try { conn = getConnection(usr, pass);
 * 
 * ps = conn.prepareStatement(insertQuery);
 * 
 * ps.setString(1, dato.getId()); ps.setString(2, dato.getAmbiente());
 * ps.setString(3, dato.getMezzo()); ps.setString(4,
 * dato.getCittaPartenza()); ps.setString(5, dato.getCittaArrivo());
 * ps.setString(6, dato.getVia());
 * 
 * 
 * 
 * ps.executeUpdate(); } catch (ConnectionException e) { // TODO
 * Auto-generated catch block e.printStackTrace(); } catch (SQLException e)
 * { // TODO Auto-generated catch block e.printStackTrace(); } finally {
 * closeResource(); } }
 * 
 * //read(id) == findByPrimaryKey(id)
 * 
 * @Override public CatalogoBean read(String id) { if (id == null) return
 * null;
 * 
 * CatalogoBean result = null;
 * 
 * try { conn = getConnection(usr, pass); ps =
 * conn.prepareStatement(findQuery);
 * 
 * ps.setString(1, id);
 * 
 * rs = ps.executeQuery();
 * 
 * rs.next();
 * 
 * result = new CatalogoBean();
 * 
 * result.setId(rs.getString(1)); result.setAmbiente(rs.getString(2));
 * result.setMezzo(rs.getString(3));
 * result.setCittaPartenza(rs.getString(4));
 * result.setCittaArrivo(rs.getString(5)); result.setVia(rs.getString(6));
 * 
 * } catch (ConnectionException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); } catch (SQLException e) { // TODO Auto-generated
 * catch block e.printStackTrace(); } finally { closeResource(); }
 * 
 * return result; }
 * 
 * 
 * @Override public void update(CatalogoBean dato) { if (dato == null)
 * return;
 * 
 * try { conn = getConnection(usr, pass);
 * 
 * ps = conn.prepareStatement(updateQuery);
 * 
 * ps.setString(1, dato.getId()); ps.setString(2, dato.getAmbiente());
 * ps.setString(3, dato.getMezzo()); ps.setString(4,
 * dato.getCittaPartenza()); ps.setString(5, dato.getCittaArrivo());
 * ps.setString(6, dato.getVia()); ps.setString(7, dato.getId());
 * 
 * ps.executeUpdate(); } catch (ConnectionException e) { // TODO
 * Auto-generated catch block e.printStackTrace(); } catch (SQLException e)
 * { // TODO Auto-generated catch block e.printStackTrace(); } finally {
 * closeResource(); } }
 * 
 * @Override public void delete(String id) { if (id == null) return;
 * 
 * try { conn = getConnection(usr, pass); ps =
 * conn.prepareStatement(deleteQuery);
 * 
 * ps.setString(1, id);
 * 
 * ps.executeUpdate(); } catch (ConnectionException e) { // TODO
 * Auto-generated catch block e.printStackTrace(); } catch (SQLException e)
 * { // TODO Auto-generated catch block e.printStackTrace(); } finally {
 * closeResource(); } }
 */

/* Elimina tutta la tabella CATALOGO con le sue entries */
public void dropCatalogo() {
	try {
		conn = getConnection(usr, pass);

		ps = conn.prepareStatement(dropQuery);

		ps.execute();
	} catch (ConnectionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
