/**
 * 
 */
package ordinaViaggi.dao;

import ordinaViaggi.exception.ConnectionException;
import ordinaViaggi.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Gambella
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class DAOListaPrenotazioni extends DAO{
private static DAOListaPrenotazioni istance = null;
	
	private static final String getCatalogoQuery = "SELECT *" + "FROM Ambienti";

	private static final String createQuery = "CREATE TABLE IF NOT EXISTS Ambienti("
			+ "id INT(10) PRIMARY KEY AUTO_INCREMENT, "
			+ "value VARCHAR(20), "
			+ ")";

	private static final String insertQuery = "INSERT INTO Ambienti "
			+ "(value)"
			+ "VALUES(?)";
	private static final String updateQuery = "UPDATE Ambienti SET "
			+ "id=?, value=?"
			+ "WHERE id=?";
	private static final String deleteQuery = "DELETE FROM "
			+ "Ambienti WHERE id=?";
	private static final String findQuery = "SELECT * FROM Ambienti "
			+ "WHERE id=?";

	private static final String dropQuery = "DROP TABLE Ambienti IF EXISTS";
	
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	private DAOListaPrenotazioni() {
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

	public static DAOListaPrenotazioni getIstance() {
		if (istance == null)
			istance = new DAOListaPrenotazioni();
		return istance;
	}
	
	

	@Override
	public void insert(Object obj) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object read(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
}