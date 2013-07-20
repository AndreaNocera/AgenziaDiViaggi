package voyager.nove.model.viaggio.map;

import java.util.TreeMap;

import voyager.nove.exception.OffertaInesistenteException;
import voyager.nove.model.viaggio.Offerta;
import voyager.nove.model.viaggio.basic.Data;

public class MappaOfferte extends TreeMap<Data,Offerta>{

	private static final long serialVersionUID = 1L;

	public MappaOfferte(MappaOfferteComparator moc){
		super(moc);
	}

	public void aggiungiOfferta(Data k, Offerta o){
		if(!containsKey(k))
			super.put(k, o);
	}
	
	public void rimuoviOfferta(Data k) throws OffertaInesistenteException{
		if(!containsKey(k)){
			throw new OffertaInesistenteException("Errore in rimozione. Offerta \""+k+"\" non presente.");
		}
		super.remove(k);
	}

	public Offerta getOfferta(Data k) throws OffertaInesistenteException {
		if (!containsKey(k)){
			throw new OffertaInesistenteException("Offerta \""+k+"\" non presente.");
		}
		return super.get(k);
	}
	
}
