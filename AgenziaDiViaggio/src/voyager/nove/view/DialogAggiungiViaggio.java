/**
 * Integrazione modello R. Gambella
 */
package voyager.nove.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

 
import voyager.nove.control.ControllorePromotore;
import voyager.nove.exception.CatalogoException;
import voyager.nove.exception.DAOException;
import voyager.nove.exception.DataException;
import voyager.nove.exception.MapException;
import voyager.nove.exception.OraException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import utils.swing.GBCHelper;
import utils.swing.JHintTextField;

/**
 *Integrazione modello R. Gambella 
 * 
 */
public class DialogAggiungiViaggio extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static final String AGGIUNGGI= "Aggiungi";
	//private static final String HINT_AMBIENTE = "ambiente";
	private static final String HINT_CITTA_PAR = "citta (partenza)";
	private static final String HINT_CITTA_ARR = "citta (arrivo)";
	private static final String AMBIENTE_TERRA= "Terra";
	private static final String AMBIENTE_MARE= "Mare";
	private static final String AMBIENTE_ARIA= "Aria";
	private static final String HINT_MEZZO= "mezzo";
	private static final String HINT_TIPO_MEZZO = "tipo";
	private static final String HINT_VIA= "via";


	private JDialog dialogMe;

	private JPanel ambientepanel;
	private JPanel panelTextField;
	private JPanel panelbottoni;
	private JPanel areaTestoPanel;

	private JComboBox<String> tendinaAmbiente;
	private JHintTextField campoMezzi;
	private JHintTextField campoTipoMezzo;
	private JHintTextField campoCittaPartenza;
	private JHintTextField campoCittaArrivo;
	private JHintTextField campoVia;
	private JTextArea areaTesto;

	private JButton bottoneAggiungi;

	private ControllorePromotore controllorePromotore;

	/**
	 * @param ownerframe
	 */
	public DialogAggiungiViaggio(JFrame ownerframe) {
		super(ownerframe, "Aggiungi Viaggio", true);
		
		try {
			this.controllorePromotore = ControllorePromotore.getInstance();
		} catch (DAOException
				| MapException | SQLException
				| DataException
				| OraException
				| CatalogoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		this.dialogMe = this;
		buildDialog();
	}

	private void buildDialog() {

		this.dialogMe=this;
		this.dialogMe.setLayout(new GridBagLayout());

		this.ambientepanel= new JPanel();
		this.ambientepanel.setLayout(new GridLayout(1, 4));
		this.panelbottoni=new JPanel();
		this.panelbottoni.setLayout(new GridLayout(1,3));
		this.panelTextField=new JPanel();
		this.panelTextField.setLayout(new GridBagLayout());
		this.areaTestoPanel= new JPanel(new GridBagLayout());
		
		this.bottoneAggiungi = new JButton(AGGIUNGGI);
		this.bottoneAggiungi.addActionListener(new GestoreButtons());
		

		this.tendinaAmbiente= new JComboBox<String>();
		this.tendinaAmbiente.setBackground(Color.WHITE);
		this.tendinaAmbiente.addItem(AMBIENTE_ARIA);
		this.tendinaAmbiente.addItem(AMBIENTE_MARE);
		this.tendinaAmbiente.addItem(AMBIENTE_TERRA);
		//this.tendinaAmbiente.addItem(HINT_AMBIENTE);

		this.campoCittaArrivo= new JHintTextField(HINT_CITTA_PAR);
		this.campoCittaPartenza= new JHintTextField(HINT_CITTA_ARR);
		this.campoMezzi= new JHintTextField(HINT_MEZZO);
		this.campoTipoMezzo= new JHintTextField(HINT_TIPO_MEZZO);
		this.campoVia= new JHintTextField(HINT_VIA);

		this.areaTesto= new JTextArea();
		this.areaTesto.setEditable(false);
		
		this.ambientepanel.add(tendinaAmbiente, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));	
		this.panelTextField.add(campoMezzi, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));	
		this.panelTextField.add(campoTipoMezzo, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));	
		this.panelTextField.add(campoCittaPartenza, new GBCHelper(0, 2).setWeight(100, 0).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelTextField.add(campoCittaArrivo, new GBCHelper(0, 3).setWeight(100, 0).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelTextField.add(campoVia, new GBCHelper(0, 4).setWeight(100, 0).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.areaTestoPanel.add(areaTesto, new GBCHelper(0, 0).setWeight(100, 100).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.CENTER).setInsets(5, 5, 5, 5));
		this.panelbottoni.add(bottoneAggiungi);
		
		this.add(this.ambientepanel, new GBCHelper(0, 1).setWeight(100, 100).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER).setInsets(10, 10, 10, 10));
		this.add(this.panelTextField, new GBCHelper(0, 2).setWeight(100, 100).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GridBagConstraints.CENTER).setInsets(10, 10, 10, 10));
		this.add(this.areaTestoPanel, new GBCHelper(0, 3).setWeight(500, 500).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.CENTER).setInsets(10, 10, 10, 10));
		this.add(this.panelbottoni, new GBCHelper(0, 5).setWeight(100, 100).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER).setInsets(10, 10, 10, 10));

	}

	private class GestoreButtons implements ActionListener {
		private JButton azioneChiamata= new JButton();
		private String mezzoConcatenato= new String();
		private Integer idTratta;

		@Override
		public void actionPerformed(ActionEvent event) {
			azioneChiamata=(JButton) event.getSource();

			switch(azioneChiamata.getText()){

			case AGGIUNGGI:

				if (campoTipoMezzo.getText().equals("")) {
					mezzoConcatenato = campoMezzi.getText();
				} else {
					mezzoConcatenato = campoMezzi.getText() + " "+ campoTipoMezzo.getText();
				}
				if (!controllorePromotore.verificaDati(tendinaAmbiente.getSelectedItem()+"",
						mezzoConcatenato, campoCittaPartenza.getText(),
						campoCittaArrivo.getText(), campoVia.getText())) {
					areaTesto.setText("");
					areaTesto.append("Dati mancanti nella entry.");
				} else {
					
						try {
							idTratta = controllorePromotore
									.inserimentoCatalogo(tendinaAmbiente.getSelectedItem()+"",
											mezzoConcatenato,
											campoCittaPartenza.getText(),
											campoCittaArrivo.getText(), campoVia.getText());
						} catch (voyager.nove.exception.ControllerException
								| IOException
								| DAOException
								| MapException
								| CatalogoException
								| SQLException
								| DataException
								| OraException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(null,
								"Tratta inserita.\nId della tratta: "
										+ idTratta);
						campoMezzi.setText("");
						campoTipoMezzo.setText("");
						campoCittaPartenza.setText("");
						campoCittaArrivo.setText("");
						campoVia.setText("");

					
				}
				break;

			}

		}
	}
}
