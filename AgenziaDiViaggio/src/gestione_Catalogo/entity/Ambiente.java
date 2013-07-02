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
		//Salvo l'oggetto che sto creando in DB e ritorno l'id per l'oggetto
		AmbienteDAO dao = AmbienteDAO.getIstanza();
		Integer id = dao.insertAndReturnId(idEsternoElemento);
		setID(id);
	}

	public Ambiente(Integer ID, IDEsternoElemento idEsternoElemento){
		super(ID, idEsternoElemento);
	}

}
