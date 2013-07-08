/*
 * Autori: Luca Paoli, Jessica Lucia, Riccardo Gambella
*/
package ispw.indici;

import ispw.entity.Data;
import ispw.entity.Ora;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.OraException;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/*import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;*/

public class IndiciPlot {
	/*Singleton*/
	private static IndiciPlot istanza = null;
	
	/*Costruttore privato per il Singleton*/
	private IndiciPlot(){}
	
	/*Istanza del Singleton*/
	public static IndiciPlot getInstance(){
		if(istanza == null)
			istanza = new IndiciPlot();
		
		return istanza;
	}
	
	/*@SuppressWarnings("unused")
	public JPanel visualizzaPlotIndici(String tipo, String superClasse, String classe){
		DAOIndice dao = DAOIndice.getIstance();
		List<Indice> listaIndici = new ArrayList<Indice>();		//lista vuota
		
		try {
			listaIndici = dao.getListaIndici();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OraException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		XYSeries serie = generaSerie(listaIndici, tipo, classe, superClasse);
		XYSeriesCollection dataset = new XYSeriesCollection();
		
		dataset.addSeries(serie);
		
		JFreeChart chart = ChartFactory.createXYLineChart("Indici di gradimento", "Data", "Gradimento", dataset, PlotOrientation.VERTICAL, true, true, true);
	
		XYPlot plot = (XYPlot)chart.getPlot();		//per eventuli modifiche al grafico
		
		return new ChartPanel(chart);
	}
	
	private static XYSeries generaSerie(List<Indice> listaIndici, String tipo, String classe, String superClasse){
		XYSeries serie = new XYSeries(classe + "/" + superClasse);
		
		//Variabili per il calcolo normalizzato
		Long minimo = null;
		Long corrente = null;
		
		for(Indice indiceCorrente : listaIndici){
			if(indiceCorrente.getTipo().compareTo(tipo) != 0 ||
			   indiceCorrente.getClasse().compareTo(classe) != 0 || 
			   indiceCorrente.getSuperClasse().compareTo(superClasse) != 0)
				continue;			//l'indice non � del tipo giusto
			
			corrente = convertiDataEOra(indiceCorrente.getDataComputazione(), indiceCorrente.getOraComputazione());
			
			if(minimo == null)
				minimo = corrente;					//inizializza il minimo
			
			if(corrente.compareTo(minimo) < 0)		//ottiene il minimo relativo all'insieme considerato
				minimo = corrente;
		}
		
		for(Indice indiceCorrente : listaIndici){
			if(indiceCorrente.getTipo().compareTo(tipo) != 0 ||
			   indiceCorrente.getClasse().compareTo(classe) != 0 || 
			   indiceCorrente.getSuperClasse().compareTo(superClasse) != 0)
				continue;			//l'indice non � del tipo giusto
					
			corrente = convertiDataEOra(indiceCorrente.getDataComputazione(), indiceCorrente.getOraComputazione());
			
			serie.add(corrente -  minimo,
					  new Double(indiceCorrente.getIndice() * 100));
		}
		
		return serie;
	}
	
	private static Long convertiDataEOra(Data data, Ora ora){
		
		//Cast a long primitivo. Java farà il cast all'oggetto "long" grazie all'autoboxing
		Long SecondiData = (long)(data.getAnno() * (365 * 24 * 3600) +
							   data.getMese() * 30 * 24 * 3600 +
							   data.getGiorno() * 24 * 3600);
		Long OraSecondi = (long)(ora.getOra() * 3600 +
							 ora.getMinuti() * 60);
		
		return new Long(SecondiData + OraSecondi);
	}*/
}
