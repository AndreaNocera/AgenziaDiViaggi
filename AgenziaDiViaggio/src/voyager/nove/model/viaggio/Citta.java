package voyager.nove.model.viaggio;

import voyager.nove.model.viaggio.basic.IDEsternoElemento;
import voyager.nove.persistence.dao.CittaDAO;

public class Citta extends ElementoIntermedio {
	
	public Citta(IDEsternoElemento idEsternoElemento){
		super(idEsternoElemento);
		
		CittaDAO dao = CittaDAO.getIstanza();
		Integer id = dao.insertAndReturnId(idEsternoElemento);
		setID(id);
	}
	
	public Citta(Integer ID, IDEsternoElemento idEsternoElemento){
		super(ID, idEsternoElemento);
	}

}
