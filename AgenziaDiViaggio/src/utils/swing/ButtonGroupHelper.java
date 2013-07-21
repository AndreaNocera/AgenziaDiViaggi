/**
 *
 */
package utils.swing;

/** Per semplificare le operazioni sui gruppi di bottoni escludenti.
 * @author TEAM 9
 *
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;


public class ButtonGroupHelper {
	private ButtonGroup bg;

	
    class BtnActionListener implements ActionListener {
        Method metodo = null;
        Object object;
        Object[] args;
        public BtnActionListener( Method m, Object value, Object[] parametri) {
        	this.metodo = m;
            this.object = value;
            this.args= parametri;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //call method
               metodo.invoke(object, (Object[])args);
            } catch (IllegalArgumentException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                e1.printStackTrace();
            }
        }
    }
    
    public ButtonGroupHelper(ButtonGroup newbuttongroup){
        this.bg = newbuttongroup;
       }
    
    public void add(AbstractButton btn, Method metodo, Object istanza, Object[] params){
    	this.bg.add(btn);
    	btn.addActionListener(new BtnActionListener( metodo, istanza, params));
    }
}
    
