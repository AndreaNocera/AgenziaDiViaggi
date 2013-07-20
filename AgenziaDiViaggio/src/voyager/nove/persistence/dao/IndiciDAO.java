package voyager.nove.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import voyager.nove.exception.ConnectionException;
import voyager.nove.persistence.manager.ConnectionManager;
import voyager.nove.persistence.manager.JDBCConnectionManager;

public class IndiciDAO {
	
	private static ConnectionManager connectionManager;
	
	private static IndiciDAO singletonIndiciDAO;
	
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	private IndiciDAO(){
		connectionManager = JDBCConnectionManager.getInstance();		
	}
	
	public static IndiciDAO getInstance() {
		if (singletonIndiciDAO == null) {
			singletonIndiciDAO = new IndiciDAO();
		}
		
		return singletonIndiciDAO;
	}
	
	private String[] getIdOffertabyCatalogo(String idAmbiente, String idMezzo, String idCittaSorg, String idCittaDest){

		boolean flag_tutti_i_parametri = false;
		
		if(idAmbiente == null && idMezzo == null && idCittaSorg == null && idCittaDest == null)
			flag_tutti_i_parametri = true;
		
		else if(idAmbiente.replaceAll(" ", "").compareTo("") == 0 && idMezzo.replaceAll(" ", "").compareTo("") == 0 && idCittaSorg.replaceAll(" ", "").compareTo("") == 0 && idCittaDest.replaceAll(" ", "").compareTo("") == 0)
			flag_tutti_i_parametri = true;
		
		String[] risultato = new String[0];
		StringBuilder strbld;
		
		ArrayList<String> lista_risultati = new ArrayList<String>();
		
		try{
			
			boolean flag_and = false;
			
			conn = connectionManager.getConnection();
			
			strbld = new StringBuilder();
			
			strbld.append("SELECT idOfferta FROM offerta WHERE idTratta = (");
			strbld.append("SELECT idTratta FROM catalogo ");
			
			if(flag_tutti_i_parametri == false)
				strbld.append("WHERE ");
			
			if(idAmbiente != null && idAmbiente.replaceAll(" ", "").compareTo("") != 0){
				if(flag_and == true)
					strbld.append("AND ");
				else flag_and = true;
				
				strbld.append("idAmbiente = ");
				strbld.append(idAmbiente);
				strbld.append(" ");
			}
			
			if(idMezzo != null && idMezzo.replaceAll(" ", "").compareTo("") != 0){
				if(flag_and == true)
					strbld.append("AND ");
				else flag_and = true;
				
				strbld.append("idMezzo = ");
				strbld.append(idMezzo);
				strbld.append(" ");
			}
			
			if(idCittaSorg != null && idCittaSorg.replaceAll(" ", "").compareTo("") != 0){
				if(flag_and == true)
					strbld.append("AND ");
				else flag_and = true;
				
				strbld.append("idCittaPartenza = ");
				strbld.append(idCittaSorg);
				strbld.append(" ");
			}
			
			if(idCittaDest != null && idCittaDest.replaceAll(" ", "").compareTo("") != 0){
				if(flag_and == true)
					strbld.append("AND ");
				else flag_and = true;
				
				strbld.append("idCittaArrivo = ");
				strbld.append(idCittaDest);
				strbld.append(" ");
			}
			
			strbld.append(")");
			
			System.out.println("Debug: " + strbld.toString());
			
			ps = conn.prepareStatement(strbld.toString());
			
			rs = ps.executeQuery();
			
			lista_risultati.add(rs.getString(1));
			
			while(rs.next())
				lista_risultati.add(rs.getString(1));
			
			risultato = new String[lista_risultati.size()];
			
			for(int i = 0; i < risultato.length; i++)
				risultato[i] = lista_risultati.get(i);
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			connectionManager.close(conn, ps, rs);
		}
		
		return risultato;
	}
	
	private int getCountBigliettifromOfferta(String idOfferta, String anno){
		if(idOfferta == null)
			throw new NullPointerException("Parametri della funzione non corretti (nulli)!");

		if(idOfferta.replaceAll(" ", "").compareTo("") == 0)
			throw new IllegalArgumentException("Parametri della funzione non corretti (vuoti)!");
		
		int risultato = -1;
		StringBuilder strbld;
		
		try{
			conn = connectionManager.getConnection();
			
			strbld = new StringBuilder();
			
			strbld.append("SELECT COUNT(idBiglietto) FROM biglietti WHERE idBiglietto = (" +
						  "SELECT idBiglietto FROM Prenotazioni WHERE idPrenotazione = (" +
						  "SELECT idPrenotazione FROM Offerta WHERE idOfferta = ");
			strbld.append(idOfferta);
			
			if(anno != null && anno.replaceAll(" ", "").compareTo("") != 0){
				strbld.append(" AND anno = ");
				strbld.append(anno);
			}
			
			strbld.append("))");
			
			ps = conn.prepareStatement(strbld.toString());
			
			rs = ps.executeQuery();
			
			risultato = rs.getInt(1);
			
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			connectionManager.close(conn, ps, rs);
		}
		
		return risultato;
	}
	
	public int getCountBiglietti(String idAmbiente, String idMezzo, String idCittaSorg, String idCittaDest, String anno){
		
		int risultato = 0;
		String[] array_offerte = getIdOffertabyCatalogo(idAmbiente, idMezzo, idCittaSorg, idCittaDest);
		
		for(String temp : array_offerte){
			int risultato_parziale = getCountBigliettifromOfferta(temp, anno);
			
			if(risultato_parziale < 0)
				throw new IllegalArgumentException("Nel conteggio dei biglietti e' apparso un numero negativo!");
			
			risultato += risultato_parziale;
		}
		
		return risultato;
	}
}
