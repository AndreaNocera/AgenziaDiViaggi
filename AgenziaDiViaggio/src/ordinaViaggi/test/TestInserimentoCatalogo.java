package ordinaViaggi.test;

import ordinaViaggi.entity.Ambiente;
import ordinaViaggi.entity.Catalogo;
import ordinaViaggi.entity.Citta;
import ordinaViaggi.dao.DAOAmbiente;
import ordinaViaggi.dao.DAOCatalogo;
import ordinaViaggi.dao.DAOCitta;
import ordinaViaggi.dao.DAOMezzo;
import ordinaViaggi.dao.DAOVia;
import ordinaViaggi.entity.Mezzo;
import ordinaViaggi.entity.Tratta;
import ordinaViaggi.entity.Via;
import ordinaViaggi.exception.CatalogoException;
import ordinaViaggi.exception.DAOException;

public class TestInserimentoCatalogo {
	public static void main(String[] args) {
		DAOCatalogo daoCatalogo = DAOCatalogo.getIstance();
		DAOAmbiente daoAmbiente = DAOAmbiente.getIstance();
		DAOMezzo daoMezzo = DAOMezzo.getIstance();
		DAOCitta daoCitta = DAOCitta.getIstance();
		DAOVia daoVia = DAOVia.getIstance();
		Integer id = 0;
		Tratta tratta = new Tratta();
		try {
			id = daoCatalogo.getNextId();
			System.out.println("Id generato della tratta: " + id);
			tratta.setId(id);
			
			String ambiente = "Terra";
			id = daoAmbiente.getIdByValue(ambiente);
			System.out.println("Id generato dell'ambiente: " + id);
			tratta.setAmbiente(new Ambiente(id, ambiente));
			
			String mezzo = "Treno";
			id = daoMezzo.getIdByValue(mezzo);
			System.out.println("Id generato dal mezzo: " + id);
			tratta.setMezzo(new Mezzo(id, mezzo));
			
			/*
			 * Problema delle città nuove con stesso id. Come lo risolvo??
			 */
			String cittaPartenza = "Roma";
			id = daoCitta.getIdByValue(cittaPartenza);
			System.out.println("Id generato dalla citta di partenza: " + id);
			tratta.setCittaPartenza(new Citta(id, cittaPartenza));
			
			String cittaArrivo = "Napoli";
			id = daoCitta.getIdByValue(cittaArrivo);
			System.out.println("Id generato dalla citta di arrivo: " + id);
			tratta.setCittaArrivo(new Citta(id, cittaArrivo));
			
			String via = "Formia";
			id = daoVia.getIdByValue(via);
			System.out.println("Id generato dalla via: " + id);
			tratta.setVia(new Via(id, via));
			
			System.out.println("Tratta finale:");
			tratta.printTratta();
			
			System.out.println("Inserimento della tratta nel catalogo.");
			Catalogo catalogo = Catalogo.getIstance();
			catalogo.inserimentoInCatalogo(tratta);
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CatalogoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
	}
}
