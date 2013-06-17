package entity;

import java.io.Serializable;

public class IDEsterno implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private String IDEsterno;
	
	public IDEsterno(String IDEsterno){
		this.IDEsterno = IDEsterno;
	}
	
	public String toString(){
		return IDEsterno;
	}

}
