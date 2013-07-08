package ispw.indici;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import ispw.entity.Ambiente;
import ispw.entity.Catalogo;
import ispw.entity.Data;
import ispw.entity.Mezzo;
import ispw.entity.Ora;
import ispw.exception.CatalogoException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.MapException;
import ispw.exception.OraException;

public class TestCalcoloMezziSuAmbiente {
	public static void main(String[] args) throws DAOException, MapException, SQLException, DataException, OraException, CatalogoException {
		Catalogo catalogo = Catalogo.getInstance();
		CalcoloIndici calcoloIndici = CalcoloIndici.getIstance();
		List<Ambiente> listaAmbienti = catalogo.getAmbienti();
		for(Ambiente ambiente : listaAmbienti){
			List<Mezzo> listaMezzi = catalogo.getMezzi(ambiente);
			for(Mezzo mezzo : listaMezzi){
				//Calcola indice mezzo/ambiente
				double indiceCalcolato = calcoloIndici.computaIndiciMetodoB(ambiente.getId(), mezzo.getId(), null, null);
				/*Creazione dell'indice e salvataggio*/
				
				System.out.println("Indice calcolato:" + indiceCalcolato);
				
				Calendar calendar = Calendar.getInstance();
				Data data = new Data(new Integer(calendar.get(Calendar.DAY_OF_MONTH)),
						new Integer(calendar.get(Calendar.MONTH)+1));
				Ora ora = new Ora(new Integer(calendar.get(Calendar.HOUR)),new Integer(calendar.get(Calendar.MINUTE)));
				
				Indice indice = new Indice("TuttiViaggi",ambiente.getValore(),mezzo.getValore(),new Double(indiceCalcolato),
						data, ora);
				indice.save();
			}
		}
		
	}
}
