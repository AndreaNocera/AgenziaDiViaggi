package voyager.nove.persistence.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface ConnectionManager {
	
	public Connection getConnection();
	
	public void close(Connection connection);
	
	public void close(Connection connection, Statement statement);
	
	public void close(Connection connection, Statement statement, ResultSet resultSet);

}
