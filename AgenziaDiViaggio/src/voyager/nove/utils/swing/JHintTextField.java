/**
 * 
 */
package voyager.nove.utils.swing;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;

/**
 *Conosciuta classe per l'implementazione di hinted text fields...
 *
 */
public class JHintTextField extends JTextField implements FocusListener {


	private static final long serialVersionUID = 1L;
	
	private  String hint;

    public JHintTextField(final String hint) {
    	super(hint);
    	this.hint = hint;
        super.addFocusListener(this);
        
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(this.getText().isEmpty()) {
            super.setText("");
        }
    }
    @Override
    public void focusLost(FocusEvent e) {
        if(this.getText().isEmpty()) {
            super.setText(hint);
        }
    }

    @Override
    public String getText() {
        String typed = super.getText();
        return typed.equals(hint) ? "" : typed;
    }
}