package gestioneOfferta.entity;

import gestione_Catalogo.entity.Info;
import java.util.Date;

public class Offerta extends Elemento {
	private IDEsterno idTratta;
	private IDEsterno idOfferta;
	private Date dataPartenza = new Date(1);
	private Date dataArrivo = new Date(1);
	private Integer classe;
	private Integer postiPerClasse;
	
	public Offerta(IDEsterno idOfferta, IDEsterno idTratta, Date partenza, Date arrivo, Integer classe, Integer posti, Info info) {
		super(idOfferta,"",info);
		this.idTratta = idTratta;
		this.dataPartenza = partenza;
		this.dataArrivo = arrivo;
		this.classe = classe;
		this.postiPerClasse = posti;
	}
	
	public void setIdViaggio(IDEsterno idTratta) {
		this.idTratta = idTratta;
	}
	
	public IDEsterno getIdViaggio() {
		return idTratta;
	}
	
	public void setDataPartenza(Date dataPartenza) {
		this.dataPartenza = dataPartenza;
	}
	
	public Date getDataPartenza() {
		return dataPartenza;
	}
	
	public void setDataArrivo(Date dataArrivo) {
		this.dataArrivo = dataArrivo;
	}
	
	public Date getDataArrivo() {
		return dataArrivo;
	}
	
	public void setClasse(int classe) {
		this.classe = classe;
	}
	
	public int getClasse() {
		return classe;
	}
	
	public void setPostiPerClasse(int postiPerClasse) {
		this.postiPerClasse = postiPerClasse;
	}
	
	public int getPostiPerClasse() {
		return postiPerClasse;
	}

	public void setIdOfferta(IDEsterno idOfferta) {
		this.idOfferta = idOfferta;
	}

	public IDEsterno getIdOfferta() {
		return idOfferta;
	}
	
	public IDEsterno getIdTratta() {
		return idTratta;
	}
}
