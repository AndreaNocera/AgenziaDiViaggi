package ordinaViaggi.test;

import ordinaViaggi.entity.Catalogo;
import ordinaViaggi.exception.CatalogoException;

public class TestFetchCatalogo {
	public static void main(String[] args) {
		Catalogo catalogo = Catalogo.getIstance();
		try {
			catalogo.printCatalogo();
		} catch (CatalogoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
