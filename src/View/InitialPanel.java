/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.*;
import javax.swing.*;

public class InitialPanel extends JPanel
{

    private CenterPanel cp;
    private NorthPanel np;

    public InitialPanel()
    {
        super();
        setBackground(Color.yellow);
        BorderLayout bl = new BorderLayout();
        setLayout(bl);
        cp = new CenterPanel();
        np = new NorthPanel();
        add(cp, BorderLayout.CENTER);
        add(np, BorderLayout.NORTH);

    }

    public CenterPanel getCp()
    {
        return cp;
    }

    public void setCp(CenterPanel cp)
    {
        this.cp = cp;
    }

    public NorthPanel getNp() {
        return np;
    }

    public void setNp(NorthPanel np) {
        this.np = np;
    }

}
