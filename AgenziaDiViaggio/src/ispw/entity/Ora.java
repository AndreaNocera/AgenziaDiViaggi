package ispw.entity;

import ispw.exception.OraException;

public class Ora {
	private Integer ora;
	private Integer minuti;
	public Ora(Integer ora, Integer minuti) throws OraException{
		if(ora < 0 || ora > 24)
			throw new OraException();
		if(minuti < 0 || minuti > 59)
			throw new OraException();
		this.setOra(ora);
		this.setMinuti(minuti);
	}
	
	public Integer getMinuti() {
		return minuti;
	}
	public void setMinuti(Integer minuti) {
		this.minuti = minuti;
	}
	public Integer getOra() {
		return ora;
	}
	public void setOra(Integer ora) {
		this.ora = ora;
	}
	public String getString(){
		String min;
		if(minuti < 10)
			min = "0" + minuti;
		else 
			min = minuti.toString();
		return ora + ":" + min;
	}
	public boolean contains(Integer ora, Integer minuti) {
		// TODO Auto-generated method stub
		if(this.ora.equals(ora) && this.minuti.equals(minuti))
			return true;
		return false;
	}
	public void print(){
		System.out.print(ora + ":");
		if(minuti < 10)
			System.out.print("0");
		System.out.print(minuti + " ");
	}

	
}
