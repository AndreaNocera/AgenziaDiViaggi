package ordinaViaggi.entity;

import ordinaViaggi.exception.MapDAOException;

/**
 * @author Gambella Riccardo
 * Interfaccia MapDAO.
 */

public interface MapDAO {
	public void save(Map map)
	throws MapDAOException;
	public Map get()
	throws MapDAOException;
}
