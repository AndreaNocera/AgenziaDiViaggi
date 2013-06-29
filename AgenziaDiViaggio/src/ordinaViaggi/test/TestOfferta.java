package ordinaViaggi.test;


import ordinaViaggi.dao.DAOOfferta;
import ordinaViaggi.entity.Data;
import ordinaViaggi.entity.MapOfferta;
import ordinaViaggi.entity.Offerta;
import ordinaViaggi.entity.Ora;
import ordinaViaggi.exception.DAOException;
import ordinaViaggi.exception.DataException;
import ordinaViaggi.exception.OraException;

public class TestOfferta {
	public static void main(String[] args) throws DataException, OraException, DAOException {
		MapOfferta mapOfferta = new MapOfferta();
		DAOOfferta daoOfferta = DAOOfferta.getIstance();
		Offerta offerta = new Offerta(daoOfferta.getNextId(),1, new Data(11,10), new Ora(11,00),300);
		offerta.print();
		mapOfferta.insertRecord(offerta.getIdOfferta(), offerta);
		System.out.println("Tratta a cui è riferita ha id: " + mapOfferta.get(offerta.getIdOfferta()).getIdTratta());
		System.out.println("Salvataggio dell'offerta nel db");
		
		daoOfferta.insert(offerta);
		Offerta offertaRead = daoOfferta.read(1);
		offertaRead.print();
	}
}
