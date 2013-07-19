package voyager.nove.gestionecatalogo.model;

import voyager.nove.gestionecatalogo.dao.CittaDAO;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class Citta extends ElementoIntermedio {
	
	public Citta(IDEsternoElemento idEsternoElemento){
		super(idEsternoElemento);
		
		//Salvo l'oggetto che sto creando in DB e ritorno l'id per l'oggetto
				CittaDAO dao = CittaDAO.getIstanza();
				Integer id = dao.insertAndReturnId(idEsternoElemento);
				setID(id);
	}
	
	public Citta(Integer ID, IDEsternoElemento idEsternoElemento){
		super(ID, idEsternoElemento);
	}

}
