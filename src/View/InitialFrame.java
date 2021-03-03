/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.*;
import javax.swing.*;

public class InitialFrame extends JFrame {

    private InitialPanel mjp;

    public InitialFrame() {
        super("Lab 4 Graphics Frame");
        LayoutSetupMAC();
        mjp = new InitialPanel();
        this.add(mjp);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        int[] framRes = autoScreenRes();
        setSize(framRes[1], framRes[0]);

        int[] windowPositions = center(framRes[1], framRes[0], 1.0);
        setLocation(windowPositions[0], windowPositions[1]);

        setVisible(true);
    }

    public InitialPanel getMjp() {
        return mjp;
    }

    public void setMjp(InitialPanel mjp) {
        this.mjp = mjp;
    }

    void LayoutSetupMAC() {
        // On some MACs it might be necessary to have the statement below 
        //for the background color of the button to appear    
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //------------------------------------------------------           
    }

    public int[] autoScreenRes() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //gets screen size
        int height1 = (int) screenSize.getHeight();
        int width1 = (int) screenSize.getWidth();
        double r2_width = 4.0;// sets 4:3 ratio
        double r2_height = 3.0;

        int ratio1 = width1 * height1;//the area of pixels in the rectangle
        double ratio2 = 1;

        do { // checks whether or not a pre-determined ratio is met to get the acurate screensize
            r2_height = r2_width / 1.333333333333333333333333333333333333333; //gets the height that coorisponds to the width in a 4:3 ratio
            ratio2 = r2_width * r2_height; //finds the new ratios area
            r2_width++;

            if ((ratio1 / ratio2) <= 3) {
                break;
            }
            if (width1 + 1 == r2_width) {
                break;
            }

        } while ((ratio1 / ratio2) != 3.0);//3 is a preset ratio that we want to get to

        return new int[]{(int) (r2_height), (int) (r2_width)};
    }

    public int[] center(int x, int y, double adjuVertical) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //gets screen size

        int win_width = x;
        int win_height = y;

        double pos_x = (screenSize.getWidth() / 2) - (win_width / 2); // takes half of the screen length/width and half of the window length/width and substracts them... 
        double pos_y = ((screenSize.getHeight() / 2) * adjuVertical) - (win_height / 2); //this give you the difference bettween the sides and gives you the pixels to move the window to the center of its respective axis
        //adju just allows you to adjust the vertical position of the window
        
        return new int[]{(int) (pos_x), (int) (pos_y)};
    }

}
