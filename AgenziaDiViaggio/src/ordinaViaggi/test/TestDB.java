package ordinaViaggi.test;

import ordinaViaggi.bean.CatalogoBean;
import ordinaViaggi.dao.CatalogoDAOMySQL;


/** @author Gambella Riccardo and Luca Paoli
 * 
 */

public class TestDB{
	public static void main(String[] args){
		CatalogoBean bean = new CatalogoBean();
		CatalogoDAOMySQL catalogo = new CatalogoDAOMySQL();
		
		/*
		 * Per inserimento se non presente la tupla. La prima volta decommentare ed eseguire,
		 * poi commentare per non ottenere MySQLIntegrityConstraintViolationException
		 */
		/*bean.setId("1");
		bean.setAmbiente("Terra");
		bean.setMezzo("Treno");
		bean.setCittaPartenza("Roma");
		bean.setCittaArrivo("Napoli");
		bean.setVia("Formia");
		
		catalogo.create(bean);
		*/
		
		bean = catalogo.read("1");
		
		System.out.println("Ambiente: " + bean.getAmbiente());
		System.out.println("Mezzo: " + bean.getMezzo());
		System.out.println("Città di Partenza: " + bean.getCittaPartenza());
		System.out.println("Città arrivo: " + bean.getCittaArrivo());
		System.out.println("Via: " + bean.getVia());
		
		
		//catalogo.dropCatalogo(); Da vedere IF EXISTS on MySQL
		
		
	}
}