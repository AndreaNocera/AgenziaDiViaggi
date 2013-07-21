package voyager.nove.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import voyager.nove.exception.ConnectionException;
import voyager.nove.exception.DAOException;
import voyager.nove.exception.DataException;
import voyager.nove.model.viaggio.Ambiente;
import voyager.nove.model.viaggio.Citta;
import voyager.nove.model.viaggio.Data;
import voyager.nove.model.viaggio.Mezzo;
import voyager.nove.model.viaggio.Tratta;
import voyager.nove.model.viaggio.Via;

public class DAOTratta extends DAO {
	private static DAOTratta instance = null;

	private static final String insertQuery = "INSERT INTO `catalogo`"
			+ "(`idTratta`, `idAmbiente`, `idMezzo`, `idCittaPartenza`, `idCittaArrivo`,"
			+ " `idVia`, giornoInserimento, meseInserimento, annoInserimento) "
			+ "VALUES (?,?,?,?,?,?,?,?,?)";

	private static final String deleteQuery = "DELETE FROM `catalogo` WHERE `idTratta`=?";

	private static final String findQuery = "SELECT * FROM `catalogo` "
			+ "WHERE `idTratta` = ?";

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private DAOTratta() {
		try {
			//Class.forName(driverName);

		} finally {
			/*closeResource()*/;
		}
	}

	public static synchronized DAOTratta getInstance() {
		if (instance == null)
			instance = new DAOTratta();
		return instance;
	}

	@Override
	public synchronized void insert(Object obj) throws DAOException {
		Tratta tratta;
		try {
			tratta = (Tratta) obj;

			conn = getConnection();

			ps = conn.prepareStatement(insertQuery);

			System.out.println("Inserimento della tratta nel db.");
			tratta.printTratta();

			ps.setInt(1, tratta.getId());
			ps.setInt(2, tratta.getAmbiente().getId());
			ps.setInt(3, tratta.getMezzo().getId());
			ps.setInt(4, tratta.getCittaPartenza().getId());
			ps.setInt(5, tratta.getCittaArrivo().getId());
			ps.setInt(6, tratta.getVia().getId());
			ps.setInt(7, tratta.getDataInserimento().getGiorno());
			ps.setInt(8, tratta.getDataInserimento().getMese());
			ps.setInt(9, tratta.getDataInserimento().getAnno());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			throw new DAOException("Errore in insert ClassCastException.");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Errore in insert SQLException.");
		}

	}

	@Override
	public synchronized Tratta read(Integer id) throws DAOException, DataException {
		Tratta tratta = new Tratta();
		String valore;
		try {
			conn = getConnection();

			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			rs.next();
			tratta.setId(rs.getInt(1));
			valore = readValue("ambienti", rs.getInt(2));
			tratta.setAmbiente(new Ambiente(rs.getInt(2), valore));

			valore = readValue("mezzi", rs.getInt(3));
			tratta.setMezzo(new Mezzo(rs.getInt(3), valore));

			valore = readValue("citta", rs.getInt(4));
			tratta.setCittaPartenza(new Citta(rs.getInt(4), valore));

			valore = readValue("citta", rs.getInt(5));
			tratta.setCittaArrivo(new Citta(rs.getInt(5), valore));

			valore = readValue("via", rs.getInt(6));
			tratta.setVia(new Via(rs.getInt(6), valore));

			Data data = new Data(rs.getInt(7), rs.getInt(8), rs.getInt(9));
			tratta.setDataInserimento(data);

			return tratta;

		} catch (ClassCastException e) {
			throw new DAOException("Errore in read.");
		} catch (SQLException e) {
			throw new DAOException("Errore in read.");
		}
	}

	@Override
	public void update(Object obj) throws DAOException {}

	@Override
	public synchronized void delete(Object obj) throws DAOException {
		Tratta tratta;
		tratta = (Tratta) obj;

		try {
			conn = getConnection();
			ps = conn.prepareStatement(deleteQuery);
			System.out.println("Cancellazione della tratta nel db.");
			tratta.printTratta();

			ps.setInt(1, tratta.getId());
			ps.executeUpdate();

		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public synchronized String readValue(String table, Integer id) throws DAOException {

		String query = "SELECT * FROM " + table + " " + "WHERE id=?";
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(query);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			rs.next();
			return rs.getString(2);

		} catch (ConnectionException e) {
			throw new DAOException("Errore in readValue.");
		} catch (SQLException e) {
			throw new DAOException("Errore in readValue.");
		}

	}
}
