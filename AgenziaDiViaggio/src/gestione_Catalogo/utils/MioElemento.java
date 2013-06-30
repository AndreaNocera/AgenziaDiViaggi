package gestione_Catalogo.utils;

public class MioElemento{

	
	public static int nextID = 1;
	private Integer id;
	private IDEsternoElemento nome;
	
	
	public MioElemento(String nome){
		this.id = new Integer(nextID);
		nextID++;
		this.nome = new IDEsternoElemento(nome);
	}
	
	
	public Integer getID(){
		return id;
	}
	
	public IDEsternoElemento getIDEsterno(){
		return nome;
	}



	

}
