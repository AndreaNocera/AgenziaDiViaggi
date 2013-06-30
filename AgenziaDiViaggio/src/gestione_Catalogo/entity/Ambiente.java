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
	}

	public Ambiente(Integer ID, IDEsternoElemento idEsternoElemento){
		super(ID, idEsternoElemento);
	}

}
