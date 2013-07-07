package gestione_Catalogo.entity;

import gestione_Catalogo.exception.OffertaInesistenteException;

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
	

	public void aggiungiOfferta(Data k, Offerta o){
		mappaOfferte.aggiungiOfferta(k, o);
	}
	
	public void rimuoviOfferta(Data k) throws OffertaInesistenteException{
		mappaOfferte.rimuoviOfferta(k);
	}

	public Offerta getOfferta(Data k) throws OffertaInesistenteException{
		return mappaOfferte.getOfferta(k);
	}


	public Set<Data> listaChiaviOfferte() {
		return mappaOfferte.keySet();
	}
	
	
	public boolean esistenzaOfferta(Data k){
		return mappaOfferte.containsKey(k);
	}

	public boolean isEmpty(){
		return mappaOfferte.isEmpty();
	}

}
