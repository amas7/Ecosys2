package org.openjfx.ecosys2;

import java.util.ArrayList;
import java.util.Random;

/**
 * La classe Combinaison représente une combinaison de caractères aléatoires.
 * Elle est utilisée pour générer des combinaisons aléatoires de longueur spécifiée.
 */
public class Combinaison {
//les caracteres utilesé pour genere la comibasion alaetoire
    private static final String charac = "abcdefghijklmnopqrstuvwxyz";

    private String my_combi;

    /** La longueur de la combinaison. */
    private int length;


    public Combinaison(int length) {
        this.length = length;
        this.my_combi = this.randomString(this.length);
    }

    /**
     * Génère une chaîne de caractères aléatoires de la longueur spécifiée.
     *
     * @param length La longueur de la chaîne aléatoire à générer.
     * @return La chaîne de caractères aléatoires générée.
     */
    public String randomString(int length) {
        int k;
        StringBuilder my_random = new StringBuilder();
        Random rd_int = new Random();

        for (k = 0; k < length; k++) {
            my_random.append(charac.charAt(rd_int.nextInt(charac.length())));
        }

        return my_random.toString();
    }


    public String getMy_combi() {
        return my_combi;
    }


    public void setMy_combi(String my_combi) {
        this.my_combi = my_combi;
    }


    public int getLength() {
        return length;
    }


    public void setLength(int length) {
        this.length = length;
    }
}
