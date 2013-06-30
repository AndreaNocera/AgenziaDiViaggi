package gestione_Catalogo.entity;

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
