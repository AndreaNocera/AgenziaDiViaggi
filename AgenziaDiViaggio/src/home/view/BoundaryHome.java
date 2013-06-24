package home.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import gestioneutenti.model.Utente;
import gestioneutenti.model.competenze.Competenza;
import home.controller.ControllerHome;

import javax.swing.*;

import utils.swing.GBCHelper;

public class BoundaryHome extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	public static final String FRAME_TITLE = "Voyager";
	private static final int SCREEN_SIZE_PROPORTION = 1;
	private static final int SCREEN_LOCATION_PROPORTION = 4;
	
	private static final String WELCOME = (Calendar.getInstance().get(Calendar.AM_PM) == Calendar.AM) ? "Buongiorno" : "Buonasera";
	
	private static final String WELCOME_CARD = "Welcome";
	
	private JPanel panelHome;
	private JPanel panelFunc;
	private JPanel panelWelcome;
	
	private JLabel labelVoyager;
	private JLabel labelWelcome;
	
	private Utente utente;
	
	private ControllerHome controllerMain;
	
	private int counter;

	public BoundaryHome(Utente utente) {
		this.utente = utente;
		this.controllerMain = new ControllerHome();
		buildFrame();
	}
	
	public void buildFrame() {
		
		//Initialization
		this.setTitle(FRAME_TITLE);	
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setResizable(true);
		this.setUndecorated(false);
		this.addWindowListener(new FrameClosingListener());			
		this.setLayout(new GridBagLayout());		
		
		
		//Panel Home
		panelHome = new JPanel();
		panelHome.setLayout(new GridBagLayout());
		counter = 0;
		for (Competenza competenza : this.utente.getRuolo().getCompetenze()) {
			JButton button = new JButton(competenza.asString());
			if (competenza.getId() == Competenza.LOGIN) button.addActionListener(new LogoutListener());
			button.addActionListener(new CompetenzaListener(competenza));
			panelHome.add(button, new GBCHelper(0, counter).setWeight(0, 0).setFill(GridBagConstraints.HORIZONTAL).setInsets(0, 0, 20, 0));
			counter ++;
		}
				
		
		//Panel Welcome
		panelWelcome = new JPanel();
		panelWelcome.setLayout(new GridBagLayout());		
		
		
		//Panel Func
		panelFunc = new JPanel();
		panelFunc.setLayout(new CardLayout());
		panelFunc.setBorder(BorderFactory.createEtchedBorder());
		this.panelFunc.add(panelWelcome, WELCOME_CARD);
		
		counter = 0;
		for (Competenza competenza : this.utente.getRuolo().getCompetenze()) {
			JPanel card = this.controllerMain.getBoundary(competenza.getId(), this.utente);
			this.panelFunc.add(card, competenza.asString());
			counter ++;
		}	
				
		//Frame Packing
		labelVoyager = new JLabel("Voyager");
		labelVoyager.setFont(new Font(labelVoyager.getFont().getName(), Font.BOLD, 20));
		labelWelcome = new JLabel(WELCOME + " " + this.utente.getDatiUtente().getNome() + "!");
		this.add(labelVoyager, new GBCHelper(0, 0).setWeight(0, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER).setInsets(10, 10, 10, 10));
		this.add(labelWelcome, new GBCHelper(1, 0).setWeight(0, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(10, 10, 10, 10));
		this.add(panelHome, new GBCHelper(0, 1).setWeight(0, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.NORTH).setInsets(10, 10, 10, 10));
		this.add(panelFunc, new GBCHelper(1, 1).setWeight(100, 100).setFill(GridBagConstraints.BOTH).setInsets(10, 10, 10, 10));
		this.pack();		
	}
	
	public class CompetenzaListener implements ActionListener {
		
		private Competenza competenza;
		
		public CompetenzaListener(Competenza competenza) {
			super();
			this.competenza = competenza;
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			showBoundary(competenza);
		}
	}
	
	public class LogoutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			logout();			
		}
		
	}
	
	public void showBoundary(Competenza competenza) {
		CardLayout layoutPanelFunc = (CardLayout) this.panelFunc.getLayout();
		layoutPanelFunc.show(this.panelFunc, competenza.asString());
	}
	
	public void logout() {
		
	}
	
	public void setDefaultScreenSize() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		this.setSize(screenWidth / SCREEN_SIZE_PROPORTION, screenHeight / SCREEN_SIZE_PROPORTION);		
	}
	
	public void setDefaultScreenLocation() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		this.setLocation(screenWidth / SCREEN_LOCATION_PROPORTION, screenHeight / SCREEN_LOCATION_PROPORTION);
		
	}
	
	public class FrameClosingListener extends WindowAdapter {
		
		public void windowClosing(WindowEvent event) {
			int choice = JOptionPane.showConfirmDialog(getParent(), "Sei sicuro di voler uscire?", "Voyager", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (choice == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
		}		
	}

}
