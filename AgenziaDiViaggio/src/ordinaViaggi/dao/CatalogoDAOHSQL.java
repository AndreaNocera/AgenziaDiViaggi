package ordinaViaggi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ordinaViaggi.bean.CatalogoBean;

import ordinaViaggi.exception.ConnectionException;
import ordinaViaggi.factory.ConnectionFactoryHSQL;

public class CatalogoDAOHSQL implements IDAO<CatalogoBean> {

	private static final String usr = "user";
	private static final String pass = "user";

	private static final String createQuery = 
			"CREATE TABLE IF NOT EXISTS CATALOGO(" +
			"ID VARCHAR(20) PRIMARY KEY, " +
			"AMBIENTE VARCHAR(20), " +
			"MEZZO VARCHAR(20), " +
			"CITTAPARTENZA VARCHAR(20), " +
			"CITTAARRIVO VARCHAR(20), " +
			"VIA VARCHAR(20)" +
			")";

	private static final String insertQuery = 
			"INSERT INTO CATALOGO " +
			"VALUES(?, ?, ?, ?, ?, ?)";
	
	private static final String updateQuery = 
			"UPDATE CATALOGO SET " +
			"ID=?, AMBIENTE=?, MEZZO=?, CITTAPARTENZA=?, CITTAARRIVO=?, VIA=? " +
			"WHERE ID=?";
	private static final String deleteQuery = 
			"DELETE FROM " +
			"CATALOGO WHERE ID=?";
	private static final String findQuery = 
			"SELECT * FROM CATALOGO " +
			"WHERE ID=?";

	private static final String dropQuery = 
			"DROP TABLE CATALOGO IF EXISTS";

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public CatalogoDAOHSQL() {
		try {
			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(createQuery);

			ps.executeUpdate();
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

	private Connection getConnection(String user, String password)
			throws ConnectionException {
		return ConnectionFactoryHSQL.getInstance()
				.getConnection(user, password);
	}

	private void closeResource() {
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

	@Override
	public void create(CatalogoBean dato) {
		if (dato == null)
			return;

		try {
			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(insertQuery);

			ps.setString(1, dato.getId());
			ps.setString(2, dato.getAmbiente());
			ps.setString(3, dato.getMezzo());
			ps.setString(4, dato.getCittaPartenza());
			ps.setString(5, dato.getCittaArrivo());
			ps.setString(6, dato.getVia());

			

			ps.executeUpdate();
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

	// read(id) == findByPrimaryKey(id)
	@Override
	public CatalogoBean read(String id) {
		if (id == null)
			return null;

		CatalogoBean result = null;

		try {
			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(findQuery);

			ps.setString(1, id);

			rs = ps.executeQuery();

			rs.next();

			result = new CatalogoBean();

			result.setId(rs.getString(1));
			result.setAmbiente(rs.getString(2));
			result.setMezzo(rs.getString(3));
			result.setCittaPartenza(rs.getString(4));
			result.setCittaArrivo(rs.getString(5));
			result.setVia(rs.getString(6));
			
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResource();
		}

		return result;
	}

	@Override
	public void update(CatalogoBean dato) {
		if (dato == null)
			return;

		try {
			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(updateQuery);

			ps.setString(1, dato.getId());
			ps.setString(2, dato.getAmbiente());
			ps.setString(3, dato.getMezzo());
			ps.setString(4, dato.getCittaPartenza());
			ps.setString(5, dato.getCittaArrivo());
			ps.setString(6, dato.getVia());
			ps.setString(7, dato.getId());

			ps.executeUpdate();
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

	@Override
	public void delete(String id) {
		if (id == null)
			return;

		try {
			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(deleteQuery);

			ps.setString(1, id);

			ps.executeUpdate();
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