package utils.swing;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public final class PreparaGrafica {

	private static int border = 5;
	private static int altezzaBottoni = 20; 
	private static int altezzaTitoli = 30;
	private static Font label_font = new Font("Arial", 0, altezzaTitoli);
	private static Font button_font = new Font("Arial", 0, altezzaBottoni);
	private static int allineamento = JLabel.CENTER;
	private static int button_size_x = 300, button_size_y = 50 ;
	
	static JLabel nuovoTitolo(String testo) {
		JLabel etichetta = new JLabel(testo);
		etichetta.setFont(PreparaGrafica.label_font);
        etichetta.setLocation(border, border);
        etichetta.setHorizontalAlignment(allineamento);
        etichetta.setVerticalAlignment(allineamento);
        etichetta.setSize(200,50);
        etichetta.setVisible(true);
        return etichetta;
	}

	static JButton nuovoBottone(String testo, ActionListener listener) {
		JButton bottone = new JButton(testo);
		bottone.setFont(PreparaGrafica.button_font);
		bottone.setHorizontalAlignment(allineamento);
		bottone.setVerticalAlignment(allineamento);
		bottone.setSize(testo.length()*14, button_size_y);
		bottone.setVisible(true);
		bottone.addActionListener(listener);
        return bottone;
	}
	
	
}
