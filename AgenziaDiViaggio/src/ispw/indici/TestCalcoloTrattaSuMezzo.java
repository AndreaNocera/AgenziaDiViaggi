package ispw.indici;

import ispw.entity.Ambiente;
import ispw.entity.Catalogo;
import ispw.entity.Citta;
import ispw.entity.Data;
import ispw.entity.Mezzo;
import ispw.entity.Ora;
import ispw.exception.CatalogoException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.MapException;
import ispw.exception.OraException;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class TestCalcoloTrattaSuMezzo {
	public static void main(String[] args) throws DAOException, MapException,
			SQLException, DataException, OraException, CatalogoException {
		Catalogo catalogo = Catalogo.getInstance();
		CalcoloIndici calcoloIndici = CalcoloIndici.getIstance();
		List<Ambiente> listaAmbienti = catalogo.getAmbienti();
		for (Ambiente ambiente : listaAmbienti) {
			List<Mezzo> listaMezzi = catalogo.getMezzi(ambiente);
			for (Mezzo mezzo : listaMezzi) {
				List<Citta> listaCittaPartenza = catalogo.getCittaPartenza(
						ambiente, mezzo);
				for (Citta cittaPartenza : listaCittaPartenza) {
					List<Citta> listaCittaArrivo = catalogo.getCittaArrivo(
							ambiente, mezzo, cittaPartenza);
					for (Citta cittaArrivo : listaCittaArrivo) {
						// Calcola indice tratta/mezzo
						double indiceCalcolato = calcoloIndici
								.computaIndiciMetodoA(ambiente.getId(),
										mezzo.getId(), cittaPartenza.getId(),
										cittaArrivo.getId());
						/* Creazione dell'indice e salvataggio */
						Calendar calendar = Calendar.getInstance();
						Data data = new Data(new Integer(
								calendar.get(Calendar.DAY_OF_MONTH)),
								new Integer(calendar.get(Calendar.MONTH) + 1));
						Ora ora = new Ora(new Integer(
								calendar.get(Calendar.HOUR)), new Integer(
								calendar.get(Calendar.MINUTE)));

						Indice indice = new Indice("TuttiViaggi",
								mezzo.getValore(), cittaPartenza.getValore()
										+ "," + cittaArrivo.getValore(),
								new Double(indiceCalcolato), data, ora);
						indice.save();
					}
				}
			}
		}
	}
}
