package org.openjfx.ecosys2;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

/**
 * La classe Fly représente un animal de type mouche.
 */
public class Fly extends Animal implements Moveable{
    static private int maxLife=3;
    private int f_speed;    // flying speed
    private int age;

    private Frog nearest_frog=null; //closest frog

    //FX
    private Timeline timeline=null;
    private ImageView imageView=null;


    public Fly(float pos_x , float pos_y, int f_speed){
        super(maxLife, pos_x, pos_y);
        this.f_speed=f_speed;

        //image
        Image flyImage = new Image(getClass().getResourceAsStream("/images/fly.png"));
        ImageView imageView = new ImageView(flyImage);
        this.imageView=imageView;

        double desiredWidth = 10; // Adjust as needed
        double desiredHeight = 10; // Adjust as needed

        this.imageView.setFitWidth(desiredWidth);
        this.imageView.setFitHeight(desiredHeight);

        this.imageView.setX(this.getPos_x() - desiredWidth / 2);
        this.imageView.setY(this.getPos_y() - desiredHeight / 2);
    }

    /**
     * Met à jour la grenouille la plus proche en fonction d'un seuil de distance donné. Si toutes les grenouilles sont plus éloignées que le seuil, alors nearest_frog est mis à null     *
     * @param ecosystem L'écosystème contenant les grenouilles.
     * @param seuil Le seuil de distance pour considérer une grenouille comme la plus proche.
     */
    public void update_nearest(Ecosystem ecosystem, float seuil){

        for (Frog frog : ecosystem.getTabFrog()){
            if (Math.sqrt(Math.pow(frog.getPos_x()-this.getPos_x(),2)+Math.pow(frog.getPos_y()-this.getPos_y(),2))<seuil){  //distance<seuil
                this.nearest_frog=frog;
                break;
            }
            else {
                this.nearest_frog=null;
            }
        }
    }
    /**
     * Met à jour la position de la mouche de manière aléatoire, en veillant à ce qu'elle reste dans l'écosystème.
     *
     * @param WINDOW_WIDTH largeur de l'écosystème
     * @param WINDOW_HEIGHT hauteur de l'écosystème
     */
    @Override
    public void move(float WINDOW_WIDTH, float WINDOW_HEIGHT) {
        Random random=new Random();

        float deltaX=(random.nextFloat()*2-1)*50; //adjust the factor if needed
        float deltaY=(random.nextFloat()*2-1)*50; //adjust the factor if needed

        double new_posX=this.imageView.getX()+deltaX;
        double new_posY=this.imageView.getY()+deltaY;

        //out of window
        if (new_posX<0){
            new_posX=0;
        } else if (new_posX>WINDOW_WIDTH) {
            new_posX=WINDOW_WIDTH;
        }

        if (new_posY<0){
            new_posY=0;
        } else if (new_posY> WINDOW_HEIGHT) {
            new_posY=WINDOW_HEIGHT;
        }

        this.imageView.setX(new_posX);
        this.imageView.setY(new_posY);
        this.setPos_x((float) new_posX);
        this.setPos_y((float)new_posY);

    }

    @Override
    public String toString() {
        return "Fly{" +
                "life=" + this.getLife() +
                "\nposition selon x=" + this.getPos_x() +
                "\nposition selon y=" + this.getPos_y() +
                "\nflying speed=" + this.getF_speed() +
                '}';
    }



    //setters and getters
    public int getF_speed() {
        return f_speed;
    }

    public void setF_speed(int f_speed) {
        this.f_speed = f_speed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Frog getNearest_frog() {
        return nearest_frog;
    }

    public void setNearest_frog(Frog nearest_frog) {
        this.nearest_frog = nearest_frog;
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


}