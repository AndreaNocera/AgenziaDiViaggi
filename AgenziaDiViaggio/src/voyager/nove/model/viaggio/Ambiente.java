package voyager.nove.model.viaggio;

import voyager.nove.model.viaggio.basic.IDEsternoElemento;
import voyager.nove.persistence.dao.AmbienteDAO;

public abstract class Ambiente extends ElementoIntermedio {
	
	public Ambiente(IDEsternoElemento idEsternoElemento) {		
		super(idEsternoElemento);
		
		AmbienteDAO ambienteDAO = AmbienteDAO.getIstanza();
		Integer id = ambienteDAO.insertAndReturnId(idEsternoElemento);
		setID(id);
	}

	public Ambiente(Integer ID, IDEsternoElemento idEsternoElemento){
		super(ID, idEsternoElemento);
	}

}
