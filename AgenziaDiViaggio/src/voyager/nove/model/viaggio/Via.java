package voyager.nove.model.viaggio;

import voyager.nove.model.viaggio.basic.IDEsternoElemento;
import voyager.nove.persistence.dao.ViaDAO;

public class Via extends ElementoFinale  {
	
	final public static String DIRETTO = "(Diretto)";
	
	public Via(IDEsternoElemento idEsternoElemento){
		super(idEsternoElemento);
		
		ViaDAO dao = ViaDAO.getIstanza();
		Integer id = dao.insertAndReturnId(idEsternoElemento);
		setID(id);
	}
	
	public Via(Integer ID, IDEsternoElemento idEsternoElemento){
		super(ID, idEsternoElemento);
	}

}
