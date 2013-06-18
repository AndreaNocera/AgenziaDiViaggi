package gestioneutenti.test;

import java.awt.*;
import javax.swing.*;

import gestioneutenti.view.BoundaryAmministraUtenti;

public class TestAmministraUtenti {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				BoundaryAmministraUtenti frame = new BoundaryAmministraUtenti();
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				frame.setVisible(true);				
			}
		});
	}
}
