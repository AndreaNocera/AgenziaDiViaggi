package gestione_Catalogo.entity;

public class StazioneIntermedia extends Elemento{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Orologio data;

	public StazioneIntermedia(IDEsterno idEsterno, Info info) {
		super(idEsterno, info);
		
		data = new Orologio();
		//aggiorno le info mettendoci la data di inserimento
		info.updateInfo("--- Inserito il " + data.stampaDataAttuale()); 
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
						
						return (this.idEsterno.equals(nuovoElemento.idEsterno));  //devo ridefinire equals anche per IDEsterno (equals in profondita')
			}

}
