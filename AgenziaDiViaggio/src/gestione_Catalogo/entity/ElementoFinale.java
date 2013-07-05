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
		mappaOfferte = new MappaOfferte(new MappaOfferteComparator());

	}

	public ElementoFinale(Integer ID, IDEsternoElemento idEsternoElemento) {
		super(ID, idEsternoElemento);
		mappaOfferte = new MappaOfferte(new MappaOfferteComparator());

	}
	

	public Set<Data> listaChiaviOfferte() {
		return mappaOfferte.keySet();
	}


	public void aggiungiOfferta(Data k, Offerta o){
		mappaOfferte.aggiungiOfferta(k, o);
	}
	
	public void rimuoviOfferta(Data k){
		mappaOfferte.remove(k);
	}

}
