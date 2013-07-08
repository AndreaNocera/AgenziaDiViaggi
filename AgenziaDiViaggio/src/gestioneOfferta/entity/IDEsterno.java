/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */


package gestioneOfferta.entity;

import java.io.Serializable;

public class IDEsterno implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private String IDEsterno;
	
	public IDEsterno(String IDEsterno){
		this.IDEsterno = IDEsterno;
	}
	
	public IDEsterno(IDEsterno IDEsterno){
		this.IDEsterno = IDEsterno.toString();
	}
	
	public String toString(){
		return IDEsterno;
	}

}
