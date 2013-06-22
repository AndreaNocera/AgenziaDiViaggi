package ordinaViaggi.dao;

/** @author Gambella Riccardo and Luca Paoli
 * 
 */

public interface IDAO<T>{
	public void create(T dato);
	public T read(String id);
	public void update(T dato);
	public void delete(String id);
}