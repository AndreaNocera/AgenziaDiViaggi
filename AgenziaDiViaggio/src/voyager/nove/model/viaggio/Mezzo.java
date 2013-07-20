package voyager.nove.model.viaggio;

import voyager.nove.model.viaggio.basic.IDEsternoElemento;
import voyager.nove.persistence.dao.MezzoDAO;

public class Mezzo extends ElementoIntermedio {
	
	public Mezzo(IDEsternoElemento idEsternoElemento){
		super(idEsternoElemento);
		
		MezzoDAO dao = MezzoDAO.getIstanza();
		this.setID(dao.insertAndReturnId(idEsternoElemento));
	}
	
	public Mezzo(Integer ID, IDEsternoElemento idEsternoElemento){
		super(ID, idEsternoElemento);
	}

}
