package gestione_Catalogo.utils;

public class MappaCatalogo extends MappaComparator<IDEsterno, MioElemento> {
	
	public MappaCatalogo(IDEsternoComparator c){
		super(c);
		
	}
	
	
	public void put (IDEsterno k, MioElemento v){
		super.put(k, v);
	}
	
	
	public MioElemento get(IDEsterno k){
		return (MioElemento) super.get(k);
	}

}
