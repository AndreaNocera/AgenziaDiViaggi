package gestione_Catalogo;

import java.sql.SQLException;

import gestione_Catalogo.dao.IndiceDAO;
import gestione_Catalogo.dao.ViaAriaDAO;
import gestione_Catalogo.entity.IDEsternoElemento;
import gestione_Catalogo.entity.Indice;

public class DBTestIndice {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Indice i = new Indice(5);
		
		
		try {
			
			IndiceDAO.create(new IDEsternoElemento("AriaBoingPerugiaTorinoImola"), i);
			
			ViaAriaDAO.create(new IDEsternoElemento("AriaBoingPerugiaTorinoImola"), new IDEsternoElemento("Aria"));
			
			//IndiceDAO.update(new IDEsterno("MareTraghettoPerugiaTorinoImola"), new Indice(10));
			
			//Indice e = IndiceDAO.read(new IDEsterno("MareTraghettoPerugiaTorinoImola"));
			
			//System.out.println(e.getErogati());
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
