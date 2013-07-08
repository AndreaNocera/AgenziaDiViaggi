/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestioneOfferta.entity;

import java.io.Serializable;
import java.util.Set;

import gestioneOfferta.entity.IDEsterno;
import gestione_Catalogo.entity.Info;
import gestione_Catalogo.exception.IDEsternoElementoException;


public class Elemento implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	//Variabili istanza
	private IDEsterno 	idEsterno;
	private Info		info;
	private String		nome;
	private String		persistencyName;
	
	//costruttore
	public Elemento(IDEsterno idEsterno, String nome, Info info){
		this.info = info;
		this.idEsterno = idEsterno;
		this.setNome(nome);
		this.persistencyName = "voy_ambiente";
	}

	public Info getInfo(){
		return this.info;
	}
	
	public IDEsterno getIDEsterno() {
		return this.idEsterno;
	}

	public void setPersistencyName(String persistencyName) {
		this.persistencyName = persistencyName;
	}

	public String getPersistencyName() {
		return persistencyName;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
