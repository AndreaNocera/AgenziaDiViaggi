/**
   @version 1.01 2004-09-24
   @author Cay Horstmann
 */

package ordinaViaggi.test;

import java.sql.*;
import java.io.*;

/**
 * This program tests that the database and the JDBC driver are correctly
 * configured.
 */
class TestDB {
	private static String driver = "org.hsqldb.jdbcDriver";
	private static String url = "jdbc:hsqldb:file:hlogin.sql";
	private static String username = "user";
	private static String password = "user";
	
	public static void main(String args[]) {
		
		
		//Caricamento del driver
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Se carica il driver e stampa hello tutto ok.");
		System.out.println("Caricato driver");
		try {
			runTest();
		} catch (SQLException ex) {
			while (ex != null) {
				ex.printStackTrace();
				ex = ex.getNextException();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Runs a test by creating a table, adding a value, showing the table
	 * contents, and removing the table.
	 * @throws ClassNotFoundException 
	 */
	public static void runTest() throws SQLException, IOException, ClassNotFoundException {
		Connection conn = getConnection();
		try {
			Statement stat = conn.createStatement();

			stat.execute("CREATE TABLE Greetings (Message CHAR(20))");
			stat.execute("INSERT INTO Greetings VALUES ('Hello, World!')");

			ResultSet result = stat.executeQuery("SELECT * FROM Greetings");
			result.next();
			System.out.println(result.getString(1));
			stat.execute("DROP TABLE Greetings");
		} finally {
			conn.close();
		}
	}

	/**
	 * Gets a connection from the properties specified in the file
	 * database.properties
	 * 
	 * @return the database connection
	 * @throws ClassNotFoundException 
	 */
	public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {
		/*
		 * Sarebbe utile leggere da file di configurazione se riuscissi a
		 * trovarlo. 
		 * Properties props = new Properties(); FileInputStream in =
		 * new FileInputStream("database.properties"); props.load(in);
		 * in.close();
		 * 
		 * String drivers = props.getProperty("jdbc.drivers"); if (drivers !=
		 * null) System.setProperty("jdbc.drivers", drivers); String url =
		 * props.getProperty("jdbc.url"); String username =
		 * props.getProperty("jdbc.username"); String password =
		 * props.getProperty("jdbc.password");
		 */
		
		return DriverManager.getConnection(url, username, password);
	}
}
