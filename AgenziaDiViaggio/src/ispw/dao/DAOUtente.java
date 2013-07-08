package ispw.dao;

import ispw.entity.Utente;
import ispw.exception.ConnectionException;
import ispw.exception.DAOException;
import ispw.exception.MoreThanOneException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOUtente extends DAO {

	private static DAOUtente instance = null;

	private static final String createQuery = "CREATE TABLE IF NOT EXISTS utenti("
			+ "username VARCHAR(20) PRIMARY KEY, "
			+ "password VARCHAR(20),"
			+ "nome VARCHAR(20),"
			+ "cognome VARCHAR(20),"
			+ "ruolo VARCHAR(30)" + ")";

	private static final String getListaUtentiQuery = "SELECT * FROM `utenti` WHERE 1";

	private static final String findByNameAndPasswordQuery = "SELECT * FROM utenti "
			+ "WHERE username = ? AND password = ?";
	private static final String insertQuery = "INSERT INTO `utenti`"
			+ "(`username`, `password`, `nome`, `cognome`, `ruolo` ) "
			+ "VALUES (?,?,?,?,?)";
	private static final String deleteQuery = "DELETE FROM "
			+ "utenti WHERE username=?";
	private static final String findQuery = "SELECT * FROM utenti "
			+ "WHERE username=?";

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private DAOUtente() {
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
			/*closeResource()*/;
		}
	}

	public synchronized static DAOUtente getIstance() {
		if (instance == null)
			instance = new DAOUtente();
		return instance;
	}

	public synchronized Utente findByNameAndPassword(String username, String password)
			throws MoreThanOneException, DAOException {
		Utente utente;

		try {

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(findByNameAndPasswordQuery);

			ps.setString(1, username);
			ps.setString(2, password);

			rs = ps.executeQuery();

			// Se non restituisce tuple significa che l'utente non è presente.
			if (!rs.next()) {
				return null;
			}

			boolean moreThanOne = (rs.getRow() > 1);
			if (moreThanOne) {
				throw new MoreThanOneException(
						"Più di un utente con stesso username e password");
			}

			utente = new Utente(rs.getString(1), rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getString(5));

		} catch (SQLException e) {
			throw new DAOException("Errore in findNameAndPassword.");
		}

		return utente;
	}

	@Override
	public synchronized void insert(Object obj) throws DAOException {
		ResultSet rs;
		Utente utente;
		try {

			utente = (Utente) obj;
			// Situazione 1. Tabella vuota. Inserisco.

			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(getListaUtentiQuery);
			rs = ps.executeQuery();
			if (!rs.next()) {
				ps = conn.prepareStatement(insertQuery);

				ps.setString(1, utente.getUsername());
				ps.setString(2, utente.getPassword());
				ps.setString(3, utente.getNome());
				ps.setString(4, utente.getCognome());
				ps.setString(5, utente.getRuolo());

				ps.executeUpdate();
			}

			// Situazione 2. Elemento non presente.
			ps = conn.prepareStatement(findQuery);

			ps.setString(1, utente.getUsername());

			rs = ps.executeQuery();

			if (!rs.next()) {
				ps = conn.prepareStatement(insertQuery);

				ps.setString(1, utente.getUsername());
				ps.setString(2, utente.getPassword());
				ps.setString(3, utente.getNome());
				ps.setString(4, utente.getCognome());
				ps.setString(5, utente.getRuolo());

				ps.executeUpdate();
			}
			// Situazione 3.Elemento Presente. Non inserisco.

		} catch (ClassCastException e) {
			throw new DAOException("Errore in insert ClassCastException.");
		} catch (SQLException e) {
			throw new DAOException("Errore in insert SQLException.");
		}

	}

	@Override
	public Object read(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object obj) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized void delete(Object obj) throws DAOException, SQLException {
		// TODO Auto-generated method stub
		Utente utente;
		try {
			utente = (Utente) obj;

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(deleteQuery);

			ps.setString(1, utente.getUsername());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			throw new DAOException("Errore in update.");
		} catch (SQLException e) {
			throw new DAOException("Errore in update.");
		}
	}

	public synchronized List<Utente> getListaUtenti() throws DAOException {
		// TODO Auto-generated method stub
		List<Utente> listaUtenti = new ArrayList<Utente>();
		try {
			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(getListaUtentiQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				Utente utente = new Utente(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5));
				listaUtenti.add(utente);
			}

		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Errore in connessione DB");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Errore in insert SQLException.");
		}
		
		return listaUtenti;

	}

	public synchronized Utente readUtenteByUsername(String username) throws DAOException {
		// TODO Auto-generated method stub
		Utente utente = null;
		try {
			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(findQuery);
			ps.setString(1, username);
			rs = ps.executeQuery();
			rs.next();
			utente = new Utente(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5));
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Errore in connessione DB");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Errore in insert SQLException.");
		}
		
		return utente;
	}
}
