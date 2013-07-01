package ordinaViaggi.entity;

import ordinaViaggi.exception.DataException;
/**
 * 
 * @author Gambella Riccardo
 *
 */
public class Data {
	private Integer giorno;
	private Integer mese;
	private Integer anno;
	public Data(Integer giorno, Integer mese) throws DataException{
		if(giorno < 1 || giorno > 31)
			throw new DataException("Giorno non inserito correttamente");
		if(mese < 1 || mese > 12)
			throw new DataException("Giorno non inserito correttamente");
		this.giorno = giorno;
		this.mese = mese;
		this.anno = 2013;
	}
	public Data(Integer giorno, Integer mese, Integer anno) throws DataException{
		if(giorno < 1 || giorno > 31)
			throw new DataException("Giorno non inserito correttamente");
		if(mese < 1 || mese > 12)
			throw new DataException("Giorno non inserito correttamente");
		this.giorno = giorno;
		this.mese = mese;
		this.anno = anno;
	}
	
	public Integer getGiorno() {
		return giorno;
	}
	public void setGiorno(Integer giorno) {
		this.giorno = giorno;
	}
	public Integer getMese() {
		return mese;
	}
	public void setMese(Integer mese) {
		this.mese = mese;
	}
	public Integer getAnno() {
		return anno;
	}
	public void setAnno(Integer anno) {
		this.anno = anno;
	}
	public String getString(){
		return (giorno + "/" + mese + "/" + anno);
	}
	public boolean contains(Integer giorno, Integer mese, Integer anno) {
		// TODO Auto-generated method stub
		if(this.giorno.equals(giorno) && this.mese.equals(mese) && this.anno.equals(anno))
			return true;
		return false;
	}
	
	public void print(){
		System.out.print(giorno + "/" + mese + "/" + anno + " ");
	}
	
	
}
