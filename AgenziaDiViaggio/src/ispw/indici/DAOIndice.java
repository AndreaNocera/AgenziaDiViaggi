package ispw.indici;

/**
 * @author Gambella Riccardo Luca Paoli Jessica Lucia
 */

import ispw.dao.DAO;
import ispw.entity.Data;
import ispw.entity.Ora;
import ispw.exception.ConnectionException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.OraException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOIndice extends DAO {

	private static DAOIndice instance = null;

	private static final String getListaIndiciQuery = "SELECT * FROM `indici` WHERE 1";

	private static final String createQuery = "CREATE TABLE IF NOT EXISTS indici("
			+ "tipo VARCHAR(30),"
			+ "superClasse VARCHAR(20), "
			+ "classe VARCHAR(20),"
			+ "indice DOUBLE(9,4),"
			+ "giornoComputazione INT(10),"
			+ "meseComputazione INT(10),"
			+ "annoComputazione INT(10),"
			+ "oraComputazione INT(10),"
			+ "minutiComputazione INT(10)" + ")";

	private static final String insertQuery = "INSERT INTO `indici`"
			+ "(`tipo`,`superClasse`, `classe`,  `indice`,  `giornoComputazione`,  `meseComputazione`,  `annoComputazione`,"
			+ "`oraComputazione`,  `minutiComputazione`) "
			+ "VALUES (?,?,?,?,?,?,?,?,?)";

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private DAOIndice() {
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

	public static DAOIndice getIstance() {
		if (instance == null)
			instance = new DAOIndice();
		return instance;
	}

	@Override
	public void insert(Object obj) throws DAOException {
		// TODO Auto-generated method stub
		Indice indice;
		try {

			indice = (Indice) obj;

			// L'indice è univoco in quanto il tempo di computazione è diverso.
			ps = conn.prepareStatement(insertQuery);

			ps.setString(1, indice.getTipo());
			ps.setString(2, indice.getSuperClasse());
			ps.setString(3, indice.getClasse());
			ps.setDouble(4, indice.getIndice());
			ps.setInt(5, indice.getDataComputazione().getGiorno());
			ps.setInt(6, indice.getDataComputazione().getMese());
			ps.setInt(7, indice.getDataComputazione().getAnno());
			ps.setInt(8, indice.getOraComputazione().getOra());
			ps.setInt(9, indice.getOraComputazione().getMinuti());

			ps.executeUpdate();
			// Situazione 3.Elemento Presente. Non inserisco.

		} catch (ClassCastException e) {
			throw new DAOException("Errore in insert ClassCastException.");
		} catch (SQLException e) {
			throw new DAOException("Errore in insert SQLException.");
		} finally {
			closeResource();
		}

	}

	public List<Indice> getListaIndici() throws DAOException, DataException, OraException {
		List<Indice> listaIndici = new ArrayList<Indice>();
		try {
			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(getListaIndiciQuery);

			rs = ps.executeQuery();
			while (rs.next()) {
				Indice indice = new Indice(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getDouble(4), new Data(rs.getInt(5),
								rs.getInt(6), rs.getInt(7)), new Ora(
								rs.getInt(8), rs.getInt(9)));
				listaIndici.add(indice);
			}
		} catch (ClassCastException e) {
			throw new DAOException("Errore in printListaAmbienti.");
		} catch (SQLException e) {
			throw new DAOException("Errore in printListaAmbienti.");
		} finally {
			closeResource();
		}

		return listaIndici;
	}

	@Override
	public Object read(Integer id) throws DAOException, DataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object obj) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object obj) throws DAOException, SQLException {
		// TODO Auto-generated method stub

	}

}
