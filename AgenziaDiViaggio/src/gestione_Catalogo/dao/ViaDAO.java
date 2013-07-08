package gestione_Catalogo.dao;

import gestione_Catalogo.entity.Via;
import gestione_Catalogo.entity.IDEsternoElemento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 * Francesco Tomei
 */
public class ViaDAO extends RawDataDAO{
	
	private static ViaDAO istanza = null;
	private static String tabella = "via";
	
	private ViaDAO() {
		super(tabella);
	}

	public static ViaDAO getIstanza() {
		if (istanza == null)
			istanza = new ViaDAO();
		return istanza;
	}
	
}
