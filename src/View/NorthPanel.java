/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author watso
 */
public class NorthPanel extends JPanel {
    private JTextField st;
    
    public NorthPanel() {
        st = new JTextField(20);
        add(st);
        this.setBackground(Color.black);
        //no need for layout
    }

    public JTextField getSt() {
        return st;
    }

    public void setSt(JTextField st) {
        this.st = st;
    }
    
}
