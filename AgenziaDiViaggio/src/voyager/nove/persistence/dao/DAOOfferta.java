package voyager.nove.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import voyager.nove.exception.ConnectionException;
import voyager.nove.exception.DAOException;
import voyager.nove.exception.DataException;
import voyager.nove.exception.OraException;
import voyager.nove.model.viaggio.Data;
import voyager.nove.model.viaggio.Offerta;
import voyager.nove.model.viaggio.Ora;

public class DAOOfferta extends DAO {
	private static DAOOfferta instance = null;

	private static final String getOffertaQuery = "SELECT * FROM `offerta`";

	private static final String createQuery = "CREATE TABLE IF NOT EXISTS `offerta`("
			+ "idOfferta INT(10) PRIMARY KEY, "
			+ "idTratta INT(10), "
			+ "giorno INT(10),"
			+ "mese INT(10),"
			+ "anno INT(10),"
			+ "oraPartenza INT(10),"
			+ "minutiPartenza INT(10),"
			+ "oraArrivo INT(10),"
			+ "minutiArrivo INT(10),"
			+ "posti INT(10),"
			+ "giornoInserimento INT(10),"
			+ "meseInserimento INT(10),"
			+ "annoInserimento INT(10)" + ")";

	private static final String insertQuery = "INSERT INTO `offerta`(`idOfferta`, `idTratta`, "
			+ "`giorno`, `mese`, `anno`, `oraPartenza`, `minutiPartenza`,  `oraArrivo`, `minutiArrivo`, `posti`, "
			+ "giornoInserimento, meseInserimento, annoInserimento) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String deleteQuery = "DELETE FROM `offerta` WHERE `idOfferta`=?";
	
	private static final String updatePostiQuery = "UPDATE `offerta` " +
			"SET `posti`=? " +
			"WHERE `idOfferta`=?";

	private static final String findQuery = "SELECT * FROM `offerta` "
			+ "WHERE `idOfferta` = ?";

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private DAOOfferta() {
		try {
			//Class.forName(driverName);

			conn = getConnection();

			ps = conn.prepareStatement(createQuery);

			ps.executeUpdate();
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeResource();
		}
	}

	public static DAOOfferta getInstance() {
		if (instance == null)
			instance = new DAOOfferta();
		return instance;
	}

	@Override
	public void insert(Object obj) throws DAOException {
		Offerta offerta;
		try {
			offerta = (Offerta) obj;

			conn = getConnection();

			ps = conn.prepareStatement(insertQuery);

			System.out.println("Inserimento dell'offerta nel db.");
			offerta.print();

			ps.setInt(1, offerta.getIdOfferta());
			ps.setInt(2, offerta.getIdTratta());
			ps.setInt(3, offerta.getData().getGiorno());
			ps.setInt(4, offerta.getData().getMese());
			ps.setInt(5, offerta.getData().getAnno());
			ps.setInt(6, offerta.getOraPartenza().getOra());
			ps.setInt(7, offerta.getOraPartenza().getMinuti());
			ps.setInt(8, offerta.getOraArrivo().getOra());
			ps.setInt(9, offerta.getOraArrivo().getMinuti());
			ps.setInt(10, offerta.getPosti());
			ps.setInt(11, offerta.getDataInserimento().getGiorno());
			ps.setInt(12, offerta.getDataInserimento().getMese());
			ps.setInt(13, offerta.getDataInserimento().getAnno());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			throw new DAOException("Errore in insert ClassCastException.");
		} catch (SQLException e) {
			throw new DAOException("Errore in insert SQLException.");
		}

	}

	@Override
	public Offerta read(Integer id) throws DAOException, DataException {
		Offerta offerta = new Offerta();
		try {
			conn = getConnection();

			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			rs.next();
			offerta.setIdOfferta(rs.getInt(1));
			offerta.setIdTratta(rs.getInt(2));
			offerta.setData(new Data(rs.getInt(3), rs.getInt(4), rs.getInt(5)));
			offerta.setOraPartenza(new Ora(rs.getInt(6), rs.getInt(7)));
			offerta.setOraArrivo(new Ora(rs.getInt(8), rs.getInt(9)));
			offerta.setPosti(rs.getInt(10));
			Data data = new Data(rs.getInt(11), rs.getInt(12), rs.getInt(13));
			offerta.setDataInserimento(data);

			return offerta;

		} catch (ClassCastException e) {
			throw new DAOException("Errore in read. ClassCastException.");
		} catch (SQLException e) {
			throw new DAOException("Errore in read. SQLException");
		} catch (DataException e) {
			throw new DAOException(
					"Errore in read.Inserimento scorretto delle data.");
		} catch (OraException e) {
			throw new DAOException(
					"Errore in read.Inserimento scorretto dell'ora.");
		}
	}

	@Override
	public void update(Object obj) throws DAOException {}

	@Override
	public void delete(Object obj) throws DAOException {
		Offerta offerta;
		offerta = (Offerta) obj;

		try {
			conn = getConnection();
			ps = conn.prepareStatement(deleteQuery);
			ps.setInt(1, offerta.getIdOfferta());
			ps.executeUpdate();

		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getNextId() throws DAOException {
		try {
			conn = getConnection();
			ps = conn.prepareStatement(getOffertaQuery);
			rs = ps.executeQuery();
			if (!rs.next())
				return 1;

			rs.last();
			return (rs.getInt(1)) + 1;
		} catch (ClassCastException e) {
			throw new DAOException("Errore in getNextId.");
		} catch (SQLException e) {
			throw new DAOException("Errore in getNextID.");
		}
	}

	public List<Offerta> getListaOfferta() throws SQLException, DataException, OraException {
		List<Offerta> listOfferte = new ArrayList<Offerta>();

		conn = getConnection();

		ps = conn.prepareStatement(getOffertaQuery);

		rs = ps.executeQuery();

		while (rs.next()) {
			Offerta offerta = new Offerta();
			offerta.setIdOfferta(rs.getInt(1));
			offerta.setIdTratta(rs.getInt(2));
			offerta.setData(new Data(rs.getInt(3), rs.getInt(4), rs.getInt(5)));
			offerta.setOraPartenza(new Ora(rs.getInt(6), rs.getInt(7)));
			offerta.setOraArrivo(new Ora(rs.getInt(8), rs.getInt(9)));
			offerta.setPosti(rs.getInt(10));
			Data data = new Data(rs.getInt(11), rs.getInt(12), rs.getInt(13));
			offerta.setDataInserimento(data);
			listOfferte.add(offerta);
		}
		return listOfferte;
	}

	public void updatePosti(Offerta offerta, Integer posti) {		
		
		try {
			conn = getConnection();
			
			ps = conn.prepareStatement(updatePostiQuery);
			
			ps.setInt(1, posti);
			ps.setInt(2, offerta.getIdOfferta());
			ps.executeUpdate();
			
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

}
