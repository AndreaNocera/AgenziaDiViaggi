/**
 * Integrazione,
 * basata sul modello sviluppato da R. Gambella.
 */
package voyager.nove.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import utils.swing.ButtonGroupHelper;
import utils.swing.GBCHelper;

/** 
 *  
 *
 */
public class BoundaryGestioneCatalogo extends JPanel{

	private static final long serialVersionUID = 1L;

	private JPanel panelMe;
	private JPanel panelfunzionalita;

	private JLabel titolofunzionalita;
	private JButton Aggiungi_btn;
	private JButton Elimina_btn;
	private JButton Visualizza_btn;
	private ButtonGroup Funzi_RadioGrp;
	private ButtonGroupHelper rgh_funzionalita;
	
	private String funzionalitaScelta;

	private final String FUNZ_AGGIUNGERE= "Aggiungi viaggio";
	private final String FUNZ_ELIMINARE= "Elimna viaggio";
	private final String FUNZ_VISUALIZZARE= "Visualizza Catagolo";
	
	private JDialog dialogfiglio= null;
	/**
	 * 
	 */
	public BoundaryGestioneCatalogo() {
		this.panelMe = this;
		buildPanel();
	}

	private void buildPanel() {

		this.panelMe=this;
		this.panelMe.setLayout(new GridBagLayout());

		this.panelfunzionalita=new JPanel();
		this.panelfunzionalita.setLayout(new GridLayout(1,3));

		this.Funzi_RadioGrp= new ButtonGroup();
		this.titolofunzionalita= new JLabel("Funzionalita:");
		this.Aggiungi_btn = new JButton(FUNZ_AGGIUNGERE);
		this.Elimina_btn = new JButton(FUNZ_ELIMINARE);
		this.Visualizza_btn = new JButton(FUNZ_VISUALIZZARE);
		this.rgh_funzionalita= new ButtonGroupHelper(Funzi_RadioGrp);
				
		this.panelfunzionalita.add(titolofunzionalita);
		this.panelfunzionalita.add(Aggiungi_btn);
		this.panelfunzionalita.add(Elimina_btn);
		this.panelfunzionalita.add(Visualizza_btn);

		this.funzionalitaScelta="";
		
		try {
			rgh_funzionalita.add(Aggiungi_btn, this.getClass().getDeclaredMethod("cambiafunzionalita", String.class ), this, new Object[] { FUNZ_AGGIUNGERE });
			rgh_funzionalita.add(Elimina_btn, this.getClass().getDeclaredMethod("cambiafunzionalita", String.class ), this, new Object[] { FUNZ_ELIMINARE });
			rgh_funzionalita.add(Visualizza_btn, this.getClass().getDeclaredMethod("cambiafunzionalita", String.class ), this, new Object[] { FUNZ_VISUALIZZARE });
	
		} catch (NoSuchMethodException| SecurityException e) {e.printStackTrace();} 

		//Frame Pack
		this.add(this.panelfunzionalita, new GBCHelper(0, 0).setWeight(100, 100).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER).setInsets(10, 10, 10, 10));

	}


	/**Questo metodo viene invocati automaticamente grazie a ButtonGroupHelper
	 */
	public  void cambiafunzionalita(String nuovafunzionalita) {
		
		funzionalitaScelta= nuovafunzionalita;
		switch(funzionalitaScelta){
		case FUNZ_AGGIUNGERE:
			dialogfiglio= new DialogAggiungiViaggio((JFrame)SwingUtilities.getWindowAncestor(panelMe));
			break;
		case FUNZ_ELIMINARE:
			dialogfiglio= new DialogEliminaViaggio((JFrame)SwingUtilities.getWindowAncestor(panelMe));
			break;
		case FUNZ_VISUALIZZARE:
			dialogfiglio= new DialogVisualizzaCatalogo((JFrame)SwingUtilities.getWindowAncestor(panelMe));
			break;
		}
	
		dialogfiglio.setSize(600, 400);
		dialogfiglio.setResizable(true);
		dialogfiglio.setVisible(true);
	
	}
}
