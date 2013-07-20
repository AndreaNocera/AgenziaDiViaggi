package voyager.nove.model.viaggio;

import java.util.Set;

import voyager.nove.exception.OffertaInesistenteException;
import voyager.nove.model.viaggio.basic.Data;
import voyager.nove.model.viaggio.basic.IDEsternoElemento;
import voyager.nove.model.viaggio.map.MappaOfferte;
import voyager.nove.model.viaggio.map.MappaOfferteComparator;

public abstract class ElementoFinale extends ElementoCatalogo {

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
