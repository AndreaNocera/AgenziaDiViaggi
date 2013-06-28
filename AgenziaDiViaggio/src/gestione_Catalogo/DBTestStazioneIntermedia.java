/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestione_Catalogo;

import java.sql.SQLException;

import gestione_Catalogo.dao.IndiceDAO;
import gestione_Catalogo.dao.StazioneIntermediaDAO;
import gestione_Catalogo.entity.IDEsternoElemento;
import gestione_Catalogo.entity.Info;
import gestione_Catalogo.entity.StazioneIntermedia;

public class DBTestStazioneIntermedia {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		StazioneIntermedia si = new StazioneIntermedia(new IDEsternoElemento("Formia"), new IDEsternoElemento("MareTraghettoRomaCagliariFormia"), new Info("no"));
		
		try {
			
			IndiceDAO.create(si.getIDEsternoDiViaggio(), si.getIndice());
			StazioneIntermediaDAO.create(si.getIDEsternoDiViaggio(), si.getIDEsternoDiElemento(), si.getInfo(), si.getData());
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
