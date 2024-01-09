package org.openjfx.ecosys2;

/**
 * La classe abstraite Element représente un element, qui se definit par 2 attributs pour sa position au sein d'un environnement.
 */
abstract public class Element {
    // describes an element of an ecosystem
    private float pos_x;
    private float pos_y;

    public Element(float pos_x, float pos_y) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }

    public float getPos_x() {
        return pos_x;
    }

    public void setPos_x(float pos_x) {
        this.pos_x = pos_x;
    }

    public float getPos_y() {
        return pos_y;
    }

    public void setPos_y(float pos_y) {
        this.pos_y = pos_y;
    }
}