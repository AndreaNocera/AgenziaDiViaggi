package ordinaViaggi.factory;

import java.sql.Connection;

import ordinaViaggi.exception.ConnectionException;

public interface IConnectionFactory{
	public Connection getConnection(String nome_utente, String password) throws ConnectionException;
}