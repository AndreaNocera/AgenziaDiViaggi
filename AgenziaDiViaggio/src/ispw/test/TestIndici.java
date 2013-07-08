package ispw.test;
import ispw.dao.IndiciDAO;
import ispw.entity.Ambiente;
import ispw.entity.Mezzo;
import ispw.exception.CatalogoException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.MapException;
import ispw.exception.OraException;

import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JFrame;

/*import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;*/


public class TestIndici{
	public static void main(String[] args) throws DAOException, MapException, SQLException, DataException, OraException, CatalogoException{
		IndiciDAO dao = IndiciDAO.getInstance();
		Double indice = 0.0;
		
		/* Parte Riccardo. Prendo l'id dal valore dell'oggetto*/
		/*Ambiente ambiente = Ambiente.getObjectByValue("Terra");
		Mezzo mezzo = Mezzo.getObjectByValue("Autobus");
		System.out.println("Tento la query con: " + ambiente.getId() + " " + mezzo.getId());
		
		Integer indiceSottoClasse = dao.getCount("idMezzo", mezzo.getId());
		Integer indiceSuperClasse = dao.getCount("idAmbiente", ambiente.getId());
		
		indice = indiceSottoClasse.doubleValue() / indiceSuperClasse.doubleValue();
	
		System.out.println(indice);
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		dataset.setValue("Indice", indice);
		dataset.setValue("Rimanente", 1 - indice);
		
		JFreeChart chart = ChartFactory.createPieChart("Prova", dataset, true, false, false);
		
		chart.setBackgroundPaint(Color.white);
		
		PiePlot plot = (PiePlot)chart.getPlot();
		
		plot.setBaseSectionPaint(Color.black);
		plot.setBaseSectionOutlinePaint(Color.black);
		plot.setBackgroundPaint(Color.red);
		plot.setSectionPaint("Indice", Color.yellow);
		plot.setSectionPaint("Rimanente", Color.orange);
		
		ChartPanel pannello = new ChartPanel(chart);
		
		JFrame frame = new JFrame("Prova");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(pannello);
		frame.setSize(500, 500);
		frame.setVisible(true);
		*/
		
	}
}