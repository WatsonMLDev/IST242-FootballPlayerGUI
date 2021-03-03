/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.lang.reflect.Field;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author fred
 */
public class CenterPanel extends JPanel {

    private ArrayList<JButton> jbs;

    public CenterPanel() {
        jbs = new ArrayList<>();
        //this.setLayout(new GridLayout(1,0));
        ;//rows by columns
    }

    public void createButtons(int n, int m) {
        int numOfButtons = ((n + 1) * m);
        for (int i = 0; i < numOfButtons; i++) {
            JButton b = new JButton(i + "");
            b.setName(String.valueOf(i));// sets an index to get later
            jbs.add(b);     //adds the button to the array
            b.setBackground(Color.pink);
            this.add(b);  //add the button to the screen
        }
        this.setLayout(new GridLayout((n + 1), m));//rows x columns
        this.validate();  //asks Java to recalculate the layout
        this.repaint(); //asks Java to refresh the screen
    }

    public void displayDataOnButtons(ArrayList<ArrayList<String>> arrOFarr, ArrayList<String> headers) {

        for (int i = 0; i < headers.size(); i++) {
            jbs.get(i).setText(headers.get(i));

        }

        changeColor(0, headers.size() - 1, "white", "black");

        int jbsCounter = headers.size();
        for (int i = 0; i < arrOFarr.size(); i++) {

            for (int j = 0; j < arrOFarr.get(i).size(); j++) {
                jbs.get(jbsCounter).setText(arrOFarr.get(i).get(j));
                jbsCounter++;
            }
        }

        boolean on = true;
        for (int i = headers.size(); i < jbs.size(); i += headers.size()) {

            if (i > jbs.size()) {
                break;
            } else if (on) {
                changeColor(i, i + (headers.size() - 1), "orange", "black");
                on = false;
            } else {
                changeColor(i, i + (headers.size() - 1), "gray", "black");
                on = true;
            }
        }

        this.validate();  //asks Java to recalculate the layout
        this.repaint();
    }

    public void changeColor(int start, int stop, String colorBack, String colorFore) {
        Class colorClass = Color.class;
        Color colorAttributeBack;
        Color colorAttributeFore;
        try {
            Field colormethodBack = colorClass.getField(colorBack);
            colorAttributeBack = (Color) colormethodBack.get(null);

            Field colormethodFore = colorClass.getField(colorFore);
            colorAttributeFore = (Color) colormethodFore.get(null);

            for (int i = start; i <= stop; i++) {
                jbs.get(i).setBackground(colorAttributeBack);
                jbs.get(i).setForeground(colorAttributeFore);
                this.validate();  //asks Java to recalculate the layout
                this.repaint();
            }
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
            colorAttributeBack = null; // Not defined
            colorAttributeFore = null; //not defined
        }

    }

    public int rowStartingPos(String searchTerm, int headers) {
        boolean flag = false;

        for (int i = headers; i < getJbs().size(); i += headers) {
            for (int j = 0; j < headers; j++) {

                if ((getJbs().get(i + j).getText() + "").equals(searchTerm)) {

                    flag = true;

                }
            }
            if (flag) {
                return i;
            }
        }

        return -1;
    }

    public ArrayList<JButton> getJbs() {
        return jbs;
    }

    public void setJbs(ArrayList<JButton> jbs) {
        this.jbs = jbs;
    }

}
