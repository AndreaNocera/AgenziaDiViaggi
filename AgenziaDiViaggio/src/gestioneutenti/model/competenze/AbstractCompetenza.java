/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model.competenze
 * 
 * @name AbstractCompetenza.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package gestioneutenti.model.competenze;

public abstract class AbstractCompetenza implements Competenza {
	
	private int ID;
	private String STRING = new String();

	protected AbstractCompetenza(int id, String string) {
		this.ID = id;
		this.STRING = string;
	}

	@Override
	public int getId() {
		return ID;
	}

	@Override
	public String asString() {
		return STRING;
	}

}
