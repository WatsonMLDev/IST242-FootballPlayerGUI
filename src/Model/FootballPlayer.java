/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.ArrayList;


/**
 *
 * @author watso
 */
public class FootballPlayer extends Person implements TableMember {

    private int number;
    private String position;

    public FootballPlayer(int number, String position, String name, Height height, int weight, String hometown, String highSchool) {

        super(name, height, weight, hometown, highSchool);

        this.number = number;
        this.position = position;
    }

    public FootballPlayer() {

        super();

        this.number = 0;
        this.position = "N/A";
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return super.toString() + " FootballPlayer{" + "number=" + number + ", position=" + position + '}';
    }

    @Override
    public String getAttribute(int n) {
        String methodResult = null;

        switch (n) {
            case 0:
                methodResult = getNumber() + "";
                break;
            case 1:
                methodResult = getPosition() + "";
                break;
            case 2:
                methodResult = getName() + "";
                break;
            case 3:
                methodResult = getHeight() + "";
                break;
            case 4:
                methodResult = getWeight() + "";
                break;
            case 5:
                methodResult = getHometown() + "";
                break;
            case 6:
                methodResult = getHighSchool() + "";
                break;
            default:
                methodResult = "invalid input" + "";
                break;
        }
        return methodResult;
    }

    @Override
    public ArrayList<String> getAttributes() {
        ArrayList<String> attributeItems = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            attributeItems.add(getAttribute(i));
        }
        return attributeItems;
    }

    @Override
    public String getAttributeName(int n) {
        String attributename;
        ArrayList<String> attributeNames = getAttributeNames();
        if (n < attributeNames.size()) {
            attributename = attributeNames.get(n);
        } else {
            attributename = "Out of bounds";
        }
        return attributename;

    }

    @Override
    public ArrayList<String> getAttributeNames() {
        Class thisClass = FootballPlayer.class;
        ArrayList<Field> fields = new ArrayList<>(Arrays.asList(thisClass.getDeclaredFields()));

        Class thisClassSuper = Person.class;
        ArrayList<Field> superAttributes = new ArrayList<>(Arrays.asList(thisClassSuper.getDeclaredFields()));

        fields.addAll(superAttributes);

        
        ArrayList<String> attributeNames = new ArrayList<>();

        for (Field names : fields) {
            attributeNames.add(names.getName());
        }
        return attributeNames;
    }

    

    

}
