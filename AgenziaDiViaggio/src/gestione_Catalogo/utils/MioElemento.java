package gestione_Catalogo.utils;

public class MioElemento{

	
	public static int nextID = 1;
	private Integer id;
	private IDEsterno idEsterno;
	
	
	public MioElemento(String nome){
		this.id = new Integer(nextID);
		nextID++;
		this.idEsterno = new IDEsterno(nome);
	}
	
	
	public Integer getID(){
		return id;
	}
	
	public IDEsterno getIDEsterno(){
		return idEsterno;
	}

	

}
