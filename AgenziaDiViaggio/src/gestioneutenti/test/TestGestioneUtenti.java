package gestioneutenti.test;

import java.awt.*;
import javax.swing.*;

import gestioneutenti.view.BoundaryGestioneUtenti;

public class TestGestioneUtenti {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				BoundaryGestioneUtenti frame = new BoundaryGestioneUtenti();
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				frame.setVisible(true);				
			}
		});
	}
}
