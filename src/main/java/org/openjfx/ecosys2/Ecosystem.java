package org.openjfx.ecosys2;

import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * La classe Ecosystem représente un ecosytème contenant des grenouilles, des mouches, des intrus.
 * Elle est utilisée pour reperer et regrouper les elements qu'elle contient.
 */
public class Ecosystem {
    //attributs regarding animals
    private String month;
    private int nb_frog;  //number of frogs
    private int nb_fly; // number of flies
    private int nb_intru; //number oof intrudors
    private ArrayList<Frog>tabFrog = new ArrayList<Frog>();    //frogs contained in the ecosystem
    private ArrayList<Fly>tabFly= new ArrayList<Fly>(); //flies in the ecosystem
    private ArrayList<Intrudor> tabIntru = new ArrayList<>(nb_intru);
    private Intrudor intru;
    private float length;
    private float height;

    private ArrayList<KeyCode> keyCodeArrayList=new ArrayList<KeyCode>();


    public Ecosystem(float length, float height, int nb_frog, int nb_fly, int nb_intru) {
        this.length = length;
        this.height = height;
        this.nb_frog = nb_frog;
        this.nb_fly = nb_fly;
        this.nb_intru=nb_intru;


        keyCodeArrayList.add(KeyCode.A);
        keyCodeArrayList.add(KeyCode.B);
        keyCodeArrayList.add(KeyCode.C);
        keyCodeArrayList.add(KeyCode.D);
        keyCodeArrayList.add(KeyCode.E);
        keyCodeArrayList.add(KeyCode.F);
        keyCodeArrayList.add(KeyCode.G);
        keyCodeArrayList.add(KeyCode.H);
        keyCodeArrayList.add(KeyCode.I);
        keyCodeArrayList.add(KeyCode.J);


        // init arrays
        Random random = new Random();
        int k;

        //flies
        for (k = 0; k < this.nb_fly; k++) { //creating flies
            float pos_x = random.nextFloat(100,600) * this.length;
            float pos_y = random.nextFloat(100,600) * this.height;
            int f_speed = random.nextInt(30);
            this.tabFly.add(new Fly(pos_x, pos_y, f_speed));
        }


        //frogs
        for (k = 0; k < (this.nb_frog); k++) { //creating flies
            float pos_x = random.nextFloat() * (this.length-100) + 50;
            float pos_y = random.nextFloat() * (this.height-100) +50;
            this.tabFrog.add(new Frog(pos_x, pos_y, 0,keyCodeArrayList.get(k)));
        }

        //intrudor
        for (k=1;k<this.nb_intru+1;k++){
            this.tabIntru.add(new Intrudor(k));
        }
    }

    @Override
    public String toString() {
        return "Ecosys{" +
                "nb_frog=" + nb_frog +
                ", nb_fly=" + nb_fly +
                ", tabFly=" + tabFly +
                ", tabFrog=" + tabFrog +
                ", tabIntru=" + tabIntru +
                ", length=" + length +
                ", height=" + height +
                '}';
    }

    //setters and getters


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getNb_frog() {
        return nb_frog;
    }

    public void setNb_frog(int nb_frog) {
        this.nb_frog = nb_frog;
    }

    public int getNb_fly() {
        return nb_fly;
    }

    public void setNb_fly(int nb_fly) {
        this.nb_fly = nb_fly;
    }

    public int getNb_intru() {
        return nb_intru;
    }

    public void setNb_intru(int nb_intru) {
        this.nb_intru = nb_intru;
    }

    public ArrayList<Frog> getTabFrog() {
        return tabFrog;
    }

    public void setTabFrog(ArrayList<Frog> tabFrog) {
        this.tabFrog = tabFrog;
    }

    public ArrayList<Fly> getTabFly() {
        return tabFly;
    }

    public void setTabFly(ArrayList<Fly> tabFly) {
        this.tabFly = tabFly;
    }

    public ArrayList<Intrudor> getTabIntru() {
        return tabIntru;
    }

    public void setTabIntru(ArrayList<Intrudor> tabIntru) {
        this.tabIntru = tabIntru;
    }

    public Intrudor getIntru() {
        return intru;
    }

    public void setIntru(Intrudor intru) {
        this.intru = intru;
    }

    public float getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
