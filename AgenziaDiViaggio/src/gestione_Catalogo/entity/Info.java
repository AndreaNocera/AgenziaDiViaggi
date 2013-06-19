/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */


package gestione_Catalogo.entity;

import java.io.Serializable;

public class Info implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	
	//attributi d'istanza
	private String info;
	
	
	//construttore
	public Info(){
		this.info = "Non ci sono informazioni";
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
	
	public String getInfo(){
		return info;
	}

}
