package gestione_Catalogo.utils;

public class MappaCatalogo extends MappaComparator<IDEsternoElemento, MioElemento> {
	
	public MappaCatalogo(IDEsternoComparator c){
		super(c);
		
	}
	
	
	public void put (IDEsternoElemento k, MioElemento v){
		super.put(k, v);
	}
	
	
	public MioElemento get(IDEsternoElemento k){
		return (MioElemento) super.get(k);
	}

}
