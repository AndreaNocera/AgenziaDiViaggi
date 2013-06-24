/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestione_Catalogo.entity;

public class StazioneIntermedia extends Elemento{
	

	private static final long serialVersionUID = 1L;
	
	private Info		info; //Le Info dovrebbero essere una proprietà del viaggio e non del singolo elemento. La stazione intermedia (o via) è l'elemento che caratterizza il viaggio, essendo l'ultimo... pertanto info è un attributo di StazioneIntermedia.
	private Orologio	data;
	
	

	public StazioneIntermedia(Info info){
		super(new IDEsternoElemento("(Diretto)"));
		
		if (info.toString().equalsIgnoreCase("")) this.info = new Info();
		else this.info = info;
		
		data = new Orologio();
		
		//aggiorno le info mettendoci la data di inserimento
		this.info.updateInfo("--- Inserito il " + data.stampaDataAttuale());
	}
	
	public StazioneIntermedia(IDEsternoElemento idEsterno, Info info) {
		super(idEsterno);
		
		if (info.toString().equalsIgnoreCase("")) this.info = new Info();
		else this.info = info;
		
		data = new Orologio();
		
		//aggiorno le info mettendoci la data di inserimento
		this.info.updateInfo("--- Inserito il " + data.stampaDataAttuale());
	}

	public Info getInfo() {
		return info;
	}
	

	public Orologio getData() {
		return data;
	}

	
	//ridefinisco il metodo equals
	public boolean equals(Object altroObject){
				
		// verifico se sono lo stesso oggetto
		if (this == altroObject) return true;
						
		// verifico se il parametro implicito è null
		if (altroObject == null) return false;
						
		//verifico se le classi non coincidono
		if (getClass() != altroObject.getClass()) return false;
						
		//ok, ora so che altroOggetto è un elemento non nullo, per cui faccio i confronti tra attributi
						
		StazioneIntermedia nuovoElemento = (StazioneIntermedia) altroObject;
						
		return (this.idEsternoElemento.equals(nuovoElemento.idEsternoElemento));  //devo ridefinire equals anche per IDEsterno (equals in profondita')
	}

}
