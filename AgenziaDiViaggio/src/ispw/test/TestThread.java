package ispw.test;

import java.sql.SQLException;
import java.util.List;

import ispw.control.ControlloreAmministratore;
import ispw.control.ControlloreCliente;
import ispw.control.ControlloreProgettista;
import ispw.control.ControllorePromotore;
import ispw.control.ControlloreVenditore;
import ispw.exception.CatalogoException;
import ispw.exception.ControllerException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.MapException;
import ispw.exception.OraException;
import ispw.exception.UtenteException;

public class TestThread {
	public TestThread() {
		// Avvia i Thread per test concorrenza
		// Thread Amministratore
		for (int i = 0; i < 3; i++) {
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					String stringaThread = "ProvaThread "
							+ Thread.currentThread().getId();

					ControlloreAmministratore controlloreAmministratore;
					try {
						controlloreAmministratore = ControlloreAmministratore
								.getInstance();
						controlloreAmministratore.inserimentoUtente(
								stringaThread, stringaThread, stringaThread,
								stringaThread, stringaThread);
						controlloreAmministratore
								.rimozioneUtente(stringaThread);
					} catch (DAOException | MapException | SQLException
							| DataException | OraException | CatalogoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UtenteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.println(stringaThread);
				}
			});
			t1.start();
		}

		// Thread Promotore
		for (int i = 0; i < 3; i++) {
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						ControllorePromotore controllorePromotore;
						controllorePromotore = ControllorePromotore
								.getInstance();

						List<String> tratte = controllorePromotore
								.visualizzaCatalogo();
						System.out.println("Catalogo letto dal promotore.");
						for (String tratta : tratte)
							System.out.println(tratta);
					} catch (DAOException | MapException | SQLException
							| DataException | OraException | CatalogoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ControllerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			t1.start();
		}

		// Thread Progettista
		for (int i = 0; i < 3; i++) {
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {

					try {
						ControlloreProgettista controlloreProgettista;
						controlloreProgettista = ControlloreProgettista
								.getInstance();

						List<String> listaAmbienti = controlloreProgettista
								.estrazioneAmbienti();
						System.out.println("Lista ambienti del progettista.");
						for (String ambiente : listaAmbienti)
							System.out.println(ambiente);
					} catch (DAOException | MapException | SQLException
							| DataException | OraException | CatalogoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});
			t1.start();
		}

		// Thread Cliente
		for (int i = 0; i < 3; i++) {
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {

					try {
						ControlloreCliente controlloreCliente;
						controlloreCliente = ControlloreCliente.getInstance();

						List<String> listaAmbienti = controlloreCliente
								.estrazioneAmbienti();
						System.out.println("Lista ambienti del cliente.");
						for (String ambiente : listaAmbienti)
							System.out.println(ambiente);
					} catch (DAOException | MapException | SQLException
							| DataException | OraException | CatalogoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});
			t1.start();
		}

		// Thread Venditore
		for (int i = 0; i < 3; i++) {
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {

					try {
						ControlloreVenditore controlloreVenditore;
						controlloreVenditore = ControlloreVenditore
								.getInstance();

						List<String> listaAmbienti = controlloreVenditore
								.estrazioneAmbienti();
						System.out.println("Lista ambienti del venditore.");
						for (String ambiente : listaAmbienti)
							System.out.println(ambiente);
					} catch (DAOException | MapException | SQLException
							| DataException | OraException | CatalogoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});
			t1.start();
		}

	}

}
