package gestione_Catalogo.entity;

import gestione_Catalogo.dao.ViaDAO;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class Via extends ElementoFinale  {
	
	final public static String DIRETTO = "(Diretto)";
	
	public Via(IDEsternoElemento idEsternoElemento){
		super(idEsternoElemento);
		//Salvo l'oggetto che sto creando in DB e ritorno l'id per l'oggetto
				ViaDAO dao = ViaDAO.getIstanza();
				Integer id = dao.insertAndReturnId(idEsternoElemento);
				setID(id);
	}
	
	public Via(Integer ID, IDEsternoElemento idEsternoElemento){
		super(ID, idEsternoElemento);
	}

}
