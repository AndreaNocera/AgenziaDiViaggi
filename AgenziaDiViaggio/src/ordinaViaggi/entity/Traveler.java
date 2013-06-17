package ordinaViaggi.entity;

import java.io.Serializable;

public class Traveler implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6148939740735158943L;
	private String name;
	private String surname;
	public Traveler(String name, String surname){
		this.name = name;
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
}
