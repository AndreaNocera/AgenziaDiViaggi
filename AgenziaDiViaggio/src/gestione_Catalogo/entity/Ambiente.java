package gestione_Catalogo.entity;

import java.io.Serializable;

public abstract class Ambiente extends Elemento implements Serializable{

	
	private static final long serialVersionUID = 1L;

	
	public Ambiente(IDEsterno idEsterno, Info info) {
		super(idEsterno, info);
		// TODO Auto-generated constructor stub
	}

}
