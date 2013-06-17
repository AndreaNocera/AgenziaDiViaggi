package gestioneutenti.view;

import gestioneutenti.model.Utente;
import gestioneutenti.view.utils.UserTable;
import gestioneutenti.view.utils.UtenteDialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import utils.GBCHelper;

public class BoundaryGestioneUtenti extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private static final int SCREEN_SIZE_PROPORTION = 2;
	private static final int SCREEN_LOCATION_PROPORTION = 4;
	public static final String FRAME_TITLE = "Voyager";	
	
	private static final Utente SAMPLE_USER_1 = new Utente("Giacomo", "Marciani", "Roma", "27 6 1990", "Uomo", "giacomo.marciani@gmail.com", "Amministratore", "giacomo.marciani", "password");
	private static final Utente SAMPLE_USER_2 = new Utente("Jesus", "Cevallos", "Roma", "27 6 1990", "Uomo", "jesusfcevallos@gmail.com", "Amministratore", "jesus.cevallos", "password");
	private static final Utente SAMPLE_USER_3 = new Utente("Ilyas", "Aboki", "Roma", "27 6 1990", "Uomo", "ilyas.aboki@gmail.com", "Amministratore", "ilyas.aboki", "password");
	private static final Utente[] SAMPLE_USERS = {SAMPLE_USER_1, SAMPLE_USER_2, SAMPLE_USER_3};
	
	public JFrame frameMain;
	
	private GridBagLayout mGBLayout;
	
	private JMenuBar menuBar;
	private JMenu menuFile;	
	private JMenuItem mitemNew;
	private JMenuItem mitemEdit;
	private JMenuItem mitemDelete;
	private JMenuItem mitemHome;
	private JMenuItem mitemManageProfile;
	private JMenuItem mitemLogout;
	private JMenuItem mitemQuit;
	
	private JPanel panelTitle;
	private JPanel panelSearch;
	private JScrollPane panelTable;
	private JPanel panelButton;	
	
	private JLabel labelTitle;
	private JTextField textfieldSearch;
	private JButton buttonSearch;
	private JButton buttonNew;
	private JButton buttonEdit;
	private JButton buttonDelete;
	private UserTable userTable;
	
	public BoundaryGestioneUtenti() {
		buildFrame();		
	}	
	
	public void newUser(String[] data) {
			//if (controller.performUserCreate(data)) {
				JOptionPane.showMessageDialog(getParent(), "Nuovo utente creato!", data[7], JOptionPane.INFORMATION_MESSAGE);
			//}
	}
	
	public void editUser(String[] data) {		
		//if (controller.performUserUpdate(data)) {
		JOptionPane.showMessageDialog(getParent(), "Utente aggiornato!", data[7], JOptionPane.INFORMATION_MESSAGE);
		//}
	}
	
	public void deleteUser(String username) {
		//if (controller.performUserDelete(username) {
		JOptionPane.showMessageDialog(getParent(), "Utente rimosso!", username, JOptionPane.INFORMATION_MESSAGE);
		//
	}
	
	public void searchUser() {
		JOptionPane.showMessageDialog(getParent(), "Oops! Not implemented function!", "Info", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void goHome() {
		JOptionPane.showMessageDialog(getParent(), "Oops! Not implemented function!", "Info", JOptionPane.INFORMATION_MESSAGE);
	}

	public void manageProfile() {
		JOptionPane.showMessageDialog(getParent(), "Oops! Not implemented function!", "Info", JOptionPane.INFORMATION_MESSAGE);
	}

	public void logout() {
		JOptionPane.showMessageDialog(getParent(), "Oops! Not implemented function!", "Info", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void quit() {
		WindowEvent windowClosing = new WindowEvent(frameMain, WindowEvent.WINDOW_CLOSING);
		frameMain.dispatchEvent(windowClosing);
	}
	
	public class NewListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			UtenteDialog dialog = new UtenteDialog(frameMain);
			dialog.setVisible(true);	
			if (dialog.hasValidData()) {
				newUser(dialog.getUserData());
			}
		}		
	}
	
	public class EditListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Utente user = userTable.getSelectedUser();
			UtenteDialog dialog = new UtenteDialog(frameMain, user);
			dialog.setVisible(true);
			if (dialog.hasModifiedData() && dialog.hasValidData()) {
				editUser(dialog.getUserData());
			}
		}		
	}
	
	public class DeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Utente user = userTable.getSelectedUser();
			int choice = JOptionPane.showConfirmDialog(getParent(), "Sei sicuro di voler rimuovere " + user.getUsername() + "?", "Conferma Rimozione", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (choice == JOptionPane.YES_OPTION) {
				deleteUser(user.getUsername());
			}
		}
	}
	
	public class SearchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			searchUser();
		}
	}
	
	public class HomeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			goHome();			
		}		
	}
	
	public class ManageProfileListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			manageProfile();
		}		
	}
	
	public class LogoutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			logout();			
		}		
	}
	
	public class QuitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			quit();			
		}		
	}
	
	public class FrameClosingListener extends WindowAdapter {
		
		public void windowClosing(WindowEvent event) {
			int choice = JOptionPane.showConfirmDialog(getParent(), "Sei sicuro di voler uscire?", "Voyager", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (choice == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
		}		
	}
	
	private void buildFrame() {
		
		//Initialization
		this.setDefaultScreenSize();
		this.setDefaultScreenLocation();
		this.setTitle(FRAME_TITLE);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setUndecorated(false);				
		this.addWindowListener(new FrameClosingListener());	
		
		frameMain = this;
		
		//Layout
		mGBLayout = new GridBagLayout();
		this.setLayout(mGBLayout);
		
		
		//Menu
		menuBar = new JMenuBar();		
		menuFile = new JMenu("File");
		menuFile.setMnemonic('F');
		mitemNew = new JMenuItem("Nuovo");
		mitemNew.addActionListener(new NewListener());
		mitemNew.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
		mitemEdit = new JMenuItem("Modifica");
		mitemEdit.addActionListener(new EditListener());
		mitemEdit.setAccelerator(KeyStroke.getKeyStroke("ctrl M"));
		mitemEdit.setEnabled(false);
		mitemDelete = new JMenuItem("Rimuovi");
		mitemDelete.addActionListener(new DeleteListener());
		mitemDelete.setAccelerator(KeyStroke.getKeyStroke("ctrl R"));
		mitemDelete.setEnabled(false);
		mitemHome = new JMenuItem("Home");
		mitemHome.addActionListener(new HomeListener());
		mitemHome.setAccelerator(KeyStroke.getKeyStroke("ctrl H"));
		mitemManageProfile = new JMenuItem("Gestisci Profilo");
		mitemManageProfile.addActionListener(new ManageProfileListener());
		mitemLogout = new JMenuItem("Logout");
		mitemLogout.addActionListener(new LogoutListener());
		mitemQuit = new JMenuItem("Esci");	
		mitemQuit.addActionListener(new QuitListener());
		menuFile.add(mitemNew);
		menuFile.add(mitemEdit);
		menuFile.add(mitemDelete);
		menuFile.addSeparator();
		menuFile.add(mitemHome);
		menuFile.add(mitemManageProfile);
		menuFile.add(mitemLogout);
		menuFile.addSeparator();
		menuFile.add(mitemQuit);		
		menuBar.add(menuFile);		
		this.setJMenuBar(menuBar);
		
		
		//Panel Title
		panelTitle = new JPanel();		
		labelTitle = new JLabel("Gestione Utenti");
		labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.BOLD, 20));		
		panelTitle.add(labelTitle);
		
		
		//Panel Search
		panelSearch = new JPanel();		
		textfieldSearch = new JTextField("", 30); 
		buttonSearch = new JButton("Cerca");		
		panelSearch.add(textfieldSearch);
		panelSearch.add(buttonSearch);
		
		
		//Panel Table
		userTable = new UserTable(SAMPLE_USERS);
		userTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				buttonEdit.setEnabled(true);
				buttonDelete.setEnabled(true);
				mitemEdit.setEnabled(true);
				mitemDelete.setEnabled(true);
			}
			
		});
		panelTable = new JScrollPane(userTable);
		
		
		//Panel Button
		panelButton = new JPanel();
		panelButton.setLayout(new GridBagLayout());		
		buttonNew = new JButton("Nuovo");
		buttonEdit = new JButton("Modifica");
		buttonEdit.setEnabled(false);
		buttonDelete = new JButton("Rimuovi");	
		buttonDelete.setEnabled(false);
		buttonNew.addActionListener(new NewListener());
		buttonEdit.addActionListener(new EditListener());
		buttonDelete.addActionListener(new DeleteListener());
		buttonSearch.addActionListener(new SearchListener());	
		panelButton.add(buttonNew, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.HORIZONTAL).setInsets(0, 0, 10, 0));
		panelButton.add(buttonEdit, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.HORIZONTAL).setInsets(0, 0, 10, 0));
		panelButton.add(buttonDelete, new GBCHelper(0, 2).setWeight(100, 0).setFill(GridBagConstraints.HORIZONTAL).setInsets(0, 0, 0, 0));	
		
		
		//Frame Pack
		this.add(panelTitle, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 15, 15));
		this.add(panelSearch, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.EAST).setInsets(5, 5, 5, 10));
		this.add(panelTable, new GBCHelper(0, 2).setWeight(100, 100).setFill(GridBagConstraints.BOTH).setInsets(10, 10, 10, 10));
		this.add(panelButton, new GBCHelper(1, 2).setWeight(0, 100).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.NORTH).setInsets(10, 5, 0, 10));		
		this.pack();
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
}
