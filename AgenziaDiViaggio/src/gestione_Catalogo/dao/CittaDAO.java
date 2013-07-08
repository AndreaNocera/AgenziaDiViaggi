package gestione_Catalogo.dao;

import gestione_Catalogo.entity.Citta;

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

public class CittaDAO extends RawDataDAO{
	private static CittaDAO istanza = null;
	private static String tabella = "citta";

	private CittaDAO() {
		super(tabella);
	}

	public static CittaDAO getIstanza() {
		if (istanza == null)
			istanza = new CittaDAO();
		return istanza;
	}
	
}
