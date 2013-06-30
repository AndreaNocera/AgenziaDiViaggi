package gestione_Catalogo.entity;

import gestione_Catalogo.dao.AmbienteDAO;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public abstract class Ambiente extends ElementoIntermedio {
	
	public Ambiente(IDEsternoElemento idEsternoElemento){
		super(idEsternoElemento);
		
		AmbienteDAO dao = AmbienteDAO.getIstanza();
		int id = dao.insertAndReturnId(idEsternoElemento);
		setID(id);
	}

	public Ambiente(Integer ID, IDEsternoElemento idEsternoElemento){
		super(ID, idEsternoElemento);
	}

}
