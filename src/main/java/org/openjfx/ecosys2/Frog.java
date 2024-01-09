package org.openjfx.ecosys2;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * La classe Frog représente un animal de type grenouille.
 */
public class Frog extends Animal implements Eater{
    private static int maxLife = 50;
    private double orientation;
    private KeyCode keycode;
    private ImageView imageView;
    private Text textLife;



    public Frog(float pos_x, float pos_y, double orientation, KeyCode keycode){
        super(maxLife,pos_x, pos_y);
        this.orientation=orientation;
        this.keycode=keycode;
        this.textLife=new Text(String.valueOf(this.getLife()));

        //text
        textLife.setFont(Font.font(20)); //soze font
        textLife.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        textLife.setFill(Color.rgb(255, 255, 255, 0.5)); //color font
        textLife.setX(this.getPos_x()); //sets position
        textLife.setY(this.getPos_y());

        //image
        javafx.scene.image.Image frogImage = new Image(getClass().getResourceAsStream("/images/frog.png"));
        ImageView imageView = new ImageView(frogImage);
        this.imageView=imageView;

        double desiredWidth = 50; //adjust if needed
        double desiredHeight = 50;

        this.imageView.setFitWidth(desiredWidth);
        this.imageView.setFitHeight(desiredHeight);
        this.imageView.setX(this.getPos_x() - desiredWidth / 2);   //centers image on frog position
        this.imageView.setY(this.getPos_y() - desiredHeight / 2);
        this.imageView.opacityProperty();
    }

    /**
     * Cette méthode permet à la grenouille de manger un autre animal, tel qu'une mouche.
     * Elle augmente la vie de la grenouille de la vie de l'animal consommé
     * et met la vie de l'animal consommé à 0.
     *
     * @param animal L'animal à manger.
     * @throws IllegalArgumentException si l'animal fourni est null.
     */
    @Override
    public void eat(Animal animal) {


        // eats another animal such as a fly
        if (animal == null) {
            throw new IllegalArgumentException("Cannot eat a null animal.");
        }

        this.setLife(this.getLife() + animal.getLife());
        animal.setLife(0);
    }

    @Override
    public String toString() {
        return "Frog{" +
                "life=" + this.getLife() +
                "\nposition selon x=" + this.getPos_x() +
                "\nposition selon y=" + this.getPos_y() +
                "\norientation=" + this.getOrientation() +
                '}';
    }

    //setters and getters
    public double getOrientation() {
        return orientation;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }

    public KeyCode getKeycode() {
        return keycode;
    }

    public void setKeycode(KeyCode keycode) {
        this.keycode = keycode;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView image) {
        imageView = image;
    }

    public Text getTextLife() {
        return textLife;
    }

    public void setTextLife(Text textLife) {
        this.textLife = textLife;
    }
}
