package gestione_Catalogo.entity;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public abstract class ElementoFinale extends ElementoCatalogo {

	//attributi di istanza
	private MappaOfferta mappaOfferta;

	public ElementoFinale(Integer ID, IDEsternoElemento idEsternoElemento) {
		super(ID, idEsternoElemento);
		mappaOfferta = new MappaOfferta();

	}



}
