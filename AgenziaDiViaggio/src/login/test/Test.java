package login.test;

import java.awt.*;
import javax.swing.*;

import login.view.BoundaryLogin;

public class Test {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				BoundaryLogin frame = new BoundaryLogin();
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				frame.setVisible(true);				
			}
		});
	}
}

