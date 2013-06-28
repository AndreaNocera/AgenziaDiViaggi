package gestione_Catalogo.entity;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class Info {
	
	//attributi d'istanza
	private String info;
	
	
	//construttore
	public Info(){
		this.info = "No info";
	}
	
	public Info(String info){
		this.info = info;
	}
	
	public void setInfo(String info){
		this.info = info;
	}
	
	public void updateInfo(String info){
		String s;
		s = this.info + " " + info;
		this.info = s;
	}
	
	public String toString(){
		return info;
	}

}
