package org.openjfx.ecosys2;

/**
 * La classe abstraite Animal représente un element présentant un niveau de vie.
 * Elle est utilisée pour définir le comportement general d'un animal .
 */
abstract public class Animal extends Element {
    private int life;
    private int maxLife;

    public Animal(int maxLife,float pos_x, float pos_y) {
        super(pos_x, pos_y);
        this.maxLife=maxLife;
        this.life = this.getMaxLife();  // maximum life at birth
    }


    @Override
    public String toString() {  // used for printing the state of an animal
        return "Animal{" +
                "life=" + life +
                '}';
    }



    // setters and getters
    public int getLife() {
        return this.life;
    }

    public void setLife(int life) { //sets animal's life
        if (life<this.getMaxLife()) {   //checks if not superior to maxlife when eating
            this.life = life;
        }
        else{;
            this.life=getMaxLife();
        }
    }

    public int getMaxLife() {
        return this.maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }
}