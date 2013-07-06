package ispw.indici;


/**
 * @author Gambella Riccardo Luca Paoli Jessica Lucia
 */
import ispw.entity.Data;
import ispw.entity.Ora;
import ispw.exception.DAOException;



public class Indice {
	private String tipo;
	private String superClasse;
	private String classe;
	private Double indice;
	private Data dataComputazione;
	private Ora oraComputazione;

	public Indice(String tipo, String superClasse, String classe,
			Double indice, Data data, Ora ora) {
		this.tipo = tipo;
		this.superClasse = superClasse;
		this.classe = classe;
		this.indice = indice;
		this.setDataComputazione(data);
		this.setOraComputazione(ora);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSuperClasse() {
		return superClasse;
	}

	public void setSuperClasse(String superClasse) {
		this.superClasse = superClasse;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public Double getIndice() {
		return indice;
	}

	public void setIndice(Double indice) {
		this.indice = indice;
	}

	public Data getDataComputazione() {
		return dataComputazione;
	}

	public void setDataComputazione(Data dataComputazione) {
		this.dataComputazione = dataComputazione;
	}

	public Ora getOraComputazione() {
		return oraComputazione;
	}

	public void setOraComputazione(Ora oraComputazione) {
		this.oraComputazione = oraComputazione;
	}

	public String getString() {
		return superClasse + " " + classe + " " + indice + " "
				+ dataComputazione.getString() + " "
				+ oraComputazione.getString();
	}

	/**
	 * Salva l'indice nel DB.
	 * @throws DAOException 
	 */
	public void save() throws DAOException {
		System.out.println(this.getString());
		DAOIndice daoIndice = DAOIndice.getIstance();
		daoIndice.insert(this);
	}

}
