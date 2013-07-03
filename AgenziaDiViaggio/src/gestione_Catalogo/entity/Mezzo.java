package gestione_Catalogo.entity;

import gestione_Catalogo.dao.MezzoDAO;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class Mezzo extends ElementoIntermedio {
	
	public Mezzo(IDEsternoElemento idEsternoElemento){
		super(idEsternoElemento);
		
		//Salvo l'oggetto che sto creando in DB e ritorno l'id per l'oggetto
		MezzoDAO dao = MezzoDAO.getIstanza();
		this.setID(dao.insertAndReturnId(idEsternoElemento));
	}
	
	public Mezzo(Integer ID, IDEsternoElemento idEsternoElemento){
		super(ID, idEsternoElemento);
	}
	
	/*public void cancellaPersistenza() {
		MezzoDAO dao = MezzoDAO.getIstanza();
		dao.delete(this.getID());
	}*/


}
