package org.openjfx.ecosys2;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * La classe Intrudor représente un element de type intrus qui est un mangeur.
 * Un intrus posède un nom, une saison de chasse et une combinaison de touche clavier permettant de le vaincre
 */

public class Intrudor extends Element implements Eater{
    private static float pos_x = 50;
    private static float pos_y = 50;
    static private int iter_name=0;
    static private int iter_month=0;
    static private int iter_path=0;
    private String name;
    private Combinaison combinaison;
    private ImageView imageView;

    private Timeline timeline;

    //FX
    static final private ArrayList<String> arrayNames=new ArrayList<>();
    static {
        arrayNames.add("Optimus Prime");
        arrayNames.add("Terminator");
        arrayNames.add("Forest Gump");
        arrayNames.add("Luke Skywalker");
        arrayNames.add("Indiana Jones");
        arrayNames.add("Marty McFly");
        arrayNames.add("Tony Stark");
        arrayNames.add("Rocky Balboa");
        arrayNames.add("Le transporteur");
        arrayNames.add("Maverick");
        arrayNames.add("Harry Potter");
        arrayNames.add("John McClane");}
    private String huntPeriod;
    static final private ArrayList<String> arrayMonths=new ArrayList<>();
    static{
        arrayMonths.add("0");
        arrayMonths.add("1");
        arrayMonths.add("2");
        arrayMonths.add("3");
        arrayMonths.add("4");
        arrayMonths.add("5");
        arrayMonths.add("6");
        arrayMonths.add("7");
        arrayMonths.add("8");
        arrayMonths.add("9");
        arrayMonths.add("10");
        arrayMonths.add("11");
    }

    static private ArrayList<String> arrayPaths=new ArrayList<>();
    static{
        arrayPaths.add("/images/optimus.jpg");
        arrayPaths.add("/images/schwarzy.jpg");
        arrayPaths.add("/images/forest.jpeg");
        arrayPaths.add("/images/luke.jpg");
        arrayPaths.add("/images/indiana.jpg");
        arrayPaths.add("/images/marty.jpg");
        arrayPaths.add("/images/tony.jpg");
        arrayPaths.add("/images/rocky.jpeg");
        arrayPaths.add("/images/jason.jpeg");
        arrayPaths.add("/images/tom.jpg");
        arrayPaths.add("/images/harry.jpg");
        arrayPaths.add("/images/john.jpg");
    }


    public Intrudor(int length) {
        super(pos_x, pos_y);
        this.combinaison=new Combinaison(length);

        //image
        Image intruImage = new Image(getClass().getResourceAsStream(arrayPaths.get(iter_path)));
        iter_path+=1;
        this.imageView=new ImageView(intruImage);

        double desiredWidth = 10; // Adjust as needed
        double desiredHeight = 10; // Adjust as needed

        this.imageView.setFitWidth(desiredWidth);
        this.imageView.setFitHeight(desiredHeight);
        this.imageView.setX(this.getPos_x() - desiredWidth / 2);   //centers image on frog position
        this.imageView.setY(this.getPos_y() - desiredHeight / 2);
        this.imageView.opacityProperty();

        //setting name
        this.name=arrayNames.get(iter_name);
        iter_name+=1;

        //setting hunt month
        this.huntPeriod=arrayMonths.get(iter_month);
        iter_month+=1;
    }


    @Override
    public String toString() {
        return "Intrudor{" +
                "name='" + name + '\'' +
                ", combinaison=" + combinaison.getMy_combi() +
                ", imageView=" + imageView +
                ", timeline=" + timeline +
                ", huntPeriod='" + huntPeriod + '\'' +
                '}';
    }
    /**
     * Définit le comportement alimentaire d'un intru, la vie de l'animal consomé est mise à 0
     *
     * @param animal L'objet {@code Animal} représentant l'entité à consommer.
     */
    @Override
    public void eat(Animal animal) {
        animal.setLife(0);
    }

    //getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHuntPeriod() {
        return huntPeriod;
    }

    public void setHuntPeriod(String huntPeriod) {
        this.huntPeriod = huntPeriod;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Combinaison getCombinaison() {
        return combinaison;
    }

    public void setCombinaison(Combinaison combinaison) {
        this.combinaison = combinaison;
    }
}
