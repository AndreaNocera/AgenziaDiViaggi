package gestione_Catalogo.entity;

import java.util.Set;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public abstract class ElementoFinale extends ElementoCatalogo {

	//attributi di istanza
	private MappaOfferte mappaOfferte;
	
	
	
	public ElementoFinale(IDEsternoElemento idEsternoElemento) {
		super(idEsternoElemento);
		mappaOfferte = new MappaOfferte();

	}

	public ElementoFinale(Integer ID, IDEsternoElemento idEsternoElemento) {
		super(ID, idEsternoElemento);
		mappaOfferte = new MappaOfferte();

	}
	

	public Set<Integer> listaChiaviElementi() {
		return mappaOfferte.keySet();
	}


	public void aggiungiOfferta(Integer k, Offerta o){
		mappaOfferte.aggiungiOfferta(k, o);
	}

}
