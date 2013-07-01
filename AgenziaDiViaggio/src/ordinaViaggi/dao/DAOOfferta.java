/**
 * 
 */
package ordinaViaggi.dao;

import ordinaViaggi.entity.Data;
import ordinaViaggi.entity.Offerta;
import ordinaViaggi.entity.Ora;
import ordinaViaggi.exception.ConnectionException;
import ordinaViaggi.exception.DAOException;
import ordinaViaggi.exception.DataException;
import ordinaViaggi.exception.OraException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
 * 
 * @author Gambella Riccardo
 * @generated 
 *            "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class DAOOfferta extends DAO {
	private static DAOOfferta istance = null;

	private static final String getOffertaQuery = "SELECT * FROM `offerta`";

	private static final String createQuery =  "CREATE TABLE IF NOT EXISTS `offerta`("
			+ "idOfferta INT(10) PRIMARY KEY, "
			+ "idTratta INT(10), "
			+ "giorno INT(10),"
			+ "mese INT(10),"
			+ "anno INT(10),"
			+ "ora INT(10),"
			+ "minuti INT(10),"
			+ "posti INT(10)"
			+ ")";
	
	private static final String insertQuery = "INSERT INTO `offerta`(`idOfferta`, `idTratta`, `giorno`, `mese`, `anno`, `ora`, `minuti`, `posti`) "
			+ "VALUES (?,?,?,?,?,?,?,?)";

	private static final String deleteQuery = "DELETE FROM `offerta` WHERE `idOfferta`=?";

	private static final String findQuery = "SELECT * FROM `offerta` "
			+ "WHERE `idOfferta` = ?";

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	/**
	 * Il DAO dell'offerta si occupa della gestione delle tuple nella tabella
	 * offerta.
	 */
	private DAOOfferta() {
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

	public static DAOOfferta getIstance() {
		if (istance == null)
			istance = new DAOOfferta();
		return istance;
	}

	@Override
	public void insert(Object obj) throws DAOException {
		// TODO Auto-generated method stub
		Offerta offerta;
		try {
			offerta = (Offerta) obj;

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(insertQuery);

			System.out.println("Inserimento dell'offerta nel db.");
			offerta.print();

			ps.setInt(1, offerta.getIdOfferta());
			ps.setInt(2, offerta.getIdTratta());
			ps.setInt(3, offerta.getData().getGiorno());
			ps.setInt(4, offerta.getData().getMese());
			ps.setInt(5, offerta.getData().getAnno());
			ps.setInt(6, offerta.getOra().getOra());
			ps.setInt(7, offerta.getOra().getMinuti());
			ps.setInt(8, offerta.getPosti());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			throw new DAOException("Errore in insert ClassCastException.");
		} catch (SQLException e) {
			throw new DAOException("Errore in insert SQLException.");
		}

	}

	@Override
	public Offerta read(Integer id) throws DAOException {
		Offerta offerta = new Offerta();
		try {
			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			rs.next();
			offerta.setIdOfferta(rs.getInt(1));
			offerta.setIdTratta(rs.getInt(2));
			offerta.setData(new Data(rs.getInt(3), rs.getInt(4), rs.getInt(5)));
			offerta.setOra(new Ora(rs.getInt(6), rs.getInt(7)));
			offerta.setPosti(rs.getInt(8));

			return offerta;

		} catch (ClassCastException e) {
			throw new DAOException("Errore in read. ClassCastException.");
		} catch (SQLException e) {
			throw new DAOException("Errore in read. SQLException");
		} catch (DataException e) {
			// TODO Auto-generated catch block
			throw new DAOException(
					"Errore in read.Inserimento scorretto delle data.");
		} catch (OraException e) {
			// TODO Auto-generated catch block
			throw new DAOException(
					"Errore in read.Inserimento scorretto dell'ora.");
		}
	}

	@Override
	public void update(Object obj) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object obj) throws DAOException {
		// TODO Auto-generated method stub
		Offerta offerta;
		offerta = (Offerta) obj;

		try {
			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(deleteQuery);
			System.out.println("Cancellazione dell'offerta nel db.");
			offerta.print();

			ps.setInt(1, offerta.getIdOfferta());
			ps.executeUpdate();

		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Integer getNextId() throws DAOException {
		try {
			// Situazione 1. Tabella Vuota. Id da ritornare 1.
			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(getOffertaQuery);
			rs = ps.executeQuery();
			if (!rs.next())
				return 1;

			// Situazione 2. Almeno un Elemento presente.
			rs.last();
			return (rs.getInt(1)) + 1;
		} catch (ClassCastException e) {
			throw new DAOException("Errore in getNextId.");
		} catch (SQLException e) {
			throw new DAOException("Errore in getNextID.");
		}
	}

	public List<Offerta> getListaOfferta() throws SQLException, DataException, OraException {
		// TODO Auto-generated method stub
		List<Offerta> listOfferte = new ArrayList<Offerta>();

		conn = getConnection(usr, pass);

		ps = conn.prepareStatement(getOffertaQuery);

		rs = ps.executeQuery();

		while (rs.next()) {
			Offerta offerta = new Offerta();
			offerta.setIdOfferta(rs.getInt(1));
			offerta.setIdTratta(rs.getInt(2));
			offerta.setData(new Data(rs.getInt(3), rs.getInt(4), rs.getInt(5)));
			offerta.setOra(new Ora(rs.getInt(6), rs.getInt(7)));
			offerta.setPosti(rs.getInt(8));
			listOfferte.add(offerta);
		}
		return listOfferte;
	}

}













