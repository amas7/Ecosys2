package org.openjfx.ecosys2;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class Game extends Application {
    public static int seuil = 100;
    public static final float WINDOW_WIDTH = 800;
    public static final float WINDOW_HEIGHT = 600;
    private int score = 0;

    private int level=0;
    private Ecosystem ecosystem;

    final private TextField entry_combi= new TextField("Entrez la combinaison");
    private Text scoreText;

    public Game(int nfly, int nfrog) {

        this.ecosystem=new Ecosystem(WINDOW_WIDTH,WINDOW_HEIGHT,nfrog, nfly,12);

        this.entry_combi.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        this.entry_combi.setAlignment(Pos.CENTER);
        this.entry_combi.setLayoutY(0);
        this.entry_combi.setOpacity(0.85);
        this.entry_combi.setPrefWidth(500);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        try {

        //background image
        Image backgroundImage = new Image(getClass().getResourceAsStream("/images/bg.png"));
        ImageView backgroundImageView = new ImageView(backgroundImage);


        backgroundImageView.setFitWidth(WINDOW_WIDTH);
        backgroundImageView.setFitHeight(WINDOW_HEIGHT);

        backgroundImageView.setX(0);
        backgroundImageView.setY(0);

        //adds the background image view to the root
        root.getChildren().add(backgroundImageView);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //displays frogs
        for (Frog frog : ecosystem.getTabFrog()) {

            //setting image in root
            root.getChildren().add(frog.getImageView());

            // displays KeyCode text
            displayKeyCodeText(frog.getKeycode(), frog.getPos_x(), frog.getPos_y(), root);

            // adds a cricle around the frog
            Circle indicationCircle = new Circle(frog.getPos_x(), frog.getPos_y(), seuil);
            indicationCircle.setFill(null);
            indicationCircle.setStroke(Color.rgb(255, 255, 255, 0.5)); // Couleur du contour du cercle
            indicationCircle.setStrokeWidth(2); // Épaisseur du contour

            root.getChildren().add(indicationCircle);

        }
        //displays the flies
        Circle flyCircle = new Circle();
        for (Fly fly : ecosystem.getTabFly()) {

            //seting image in root
            root.getChildren().add(fly.getImageView());

            // timeline for updating the fly's position randomly
            //Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), actionEvent -> fly.move(WINDOW_WIDTH,WINDOW_HEIGHT)));
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), actionEvent -> updateFlyPosition(fly.getImageView(),fly)));
            timeline.setCycleCount(Timeline.INDEFINITE);    //timeline plays indefinitely
            timeline.play();
            fly.update_nearest(ecosystem, seuil);
            fly.setTimeline(timeline);
        }

        //intrudors
//        for (Intrudor intru:ecosystem.getTabIntru()){
//            Timeline timelineIntru = new Timeline(new KeyFrame(Duration.millis(5000), actionEvent -> appearIntru(intru,root)));
//            timelineIntru.setCycleCount(Timeline.INDEFINITE);
//            if(intru.getHuntPeriod().equals(String.valueOf(this.level))){
//                timelineIntru.play();
//            }
//        }


//        Timeline timelineIntru = new Timeline(new KeyFrame(Duration.millis(5000), actionEvent -> appearIntru(ecosystem.getTabIntru().get(0), root)));
//        timelineIntru.setCycleCount(1);
//        timelineIntru.play();

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(15), event -> {
                appearIntru(ecosystem.getTabIntru().get(level), root);

        });

        Timeline intrusTimeline = new Timeline(keyFrame);
        intrusTimeline.setCycleCount(Timeline.INDEFINITE);  // Répéter indéfiniment
        intrusTimeline.play();





        //scoreboard
        scoreText = new Text("Score: " + score);
        scoreText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        scoreText.setFill(Color.rgb(255, 255, 255, 0.5)); //color font
        scoreText.setX(WINDOW_WIDTH - 160); //sets position
        scoreText.setY(40);

        root.getChildren().add(scoreText);

        playBackgroundMusic();

        //setting up the scene
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setTitle("FrogMania");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new javafx.scene.image.Image("file:src/main/resources/images/icon.jpg"));
        primaryStage.show();


        // handles frog movement on key press
        handleKeyEvents(scene, root);
    }

//    }


    private void appearIntru(Intrudor intru, Group root){

        final String[] entry = {""};
        intru.getImageView().setFitHeight(100);
        intru.getImageView().setFitWidth(100);
        intru.getImageView().setX(0);
        intru.getImageView().setY(100);   //intru image
        root.getChildren().add(intru.getImageView());   // intru appearing

        this.entry_combi.setOnMouseClicked(mouseEvent -> this.entry_combi.clear()); //clears when clicked
        this.entry_combi.setText("Entrez la combinaison pour battre " + intru.getName());

        PauseTransition pause = new PauseTransition(Duration.seconds(0.5)); //pause between appearance and combinaison imput
        pause.setOnFinished(e -> {
            root.getChildren().add(entry_combi);
        });    //shows text
        pause.play();


        TextField right_combiText = new TextField(intru.getCombinaison().getMy_combi());    //get combinaison
        right_combiText.setEditable(false);
        right_combiText.setLayoutX(0);
        right_combiText.setLayoutY(550);
        right_combiText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        right_combiText.setPrefWidth(50+20*level);
        root.getChildren().add(right_combiText);    //displays the combinaison to do


        PauseTransition pauseIntru = new PauseTransition(Duration.seconds(5));  //pause for user to give combinaison
        pauseIntru.setOnFinished(event -> {
            entry[0] = entry_combi.getText();
            root.getChildren().removeAll(entry_combi, intru.getImageView(), right_combiText); //undisplay textfield, intru combinaison

            if (intru.getCombinaison().getMy_combi().equals(entry[0])) {    //right combinaison
                score += 100; // adjust if needed
                this.level += 1;  //passing to new level
            } else {  //wrong combinaison
                score -= 200; // adjust if needed
                this.level += 1;  //passing to new level
            }
            scoreText.setText("Score: " + score);
            intru.getTimeline().stop(); //stop timeline


        });
        pauseIntru.play();  //pausing
    }

    private void updateFlyPosition(ImageView flyImage, Fly fly) {
        // Move the fly randomly within the window boundaries using Brownian motion
        double deltaX = (Math.random() * 2 - 1) * 50; // Adjust the scale factor for desired movement range
        double deltaY = (Math.random() * 2 - 1) * 50;

        // Ensure the fly stays within the window boundaries
        double newX = flyImage.getX() + deltaX;
        double newY = flyImage.getY() + deltaY;

        if (newX < 0) {
            newX = 0;
        } else if (newX > WINDOW_WIDTH) {
            newX = WINDOW_WIDTH;
        }

        if (newY < 0) {
            newY = 0;
        } else if (newY > WINDOW_HEIGHT) {
            newY = WINDOW_HEIGHT;
        }

        flyImage.setX(newX);
        flyImage.setY(newY);
        fly.setPos_x((float) newX);
        fly.setPos_y((float) newY);
        fly.update_nearest(ecosystem, seuil);
    }
    private void displayKeyCodeText(KeyCode keyCode, double x, double y, Group root) {
        //displays the key for the frog on the UI
        Text keyCodeText = new Text(keyCode.getName());
        keyCodeText.setFill(Color.WHITE);
        keyCodeText.setFont(Font.font("Verdana", FontWeight.BOLD, 8));

        keyCodeText.setX(x - 25);  // Adjust the horizontal position as needed
        keyCodeText.setY(y + 30);  // Adjust the vertical position as needed

        root.getChildren().add(keyCodeText);
    }


    MediaPlayer mediaPlayer;
    private void playBackgroundMusic() {
        try {

            String musicPath = "target/classes/sound/bg-music.mp3";
            Media media = new Media(new File(musicPath).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();

        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions accordingly
        }
    }

    private void playSound(String soundPath) {
        try {
            Media sound2 = new Media(new File(soundPath).toURI().toString());
            MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);
            mediaPlayer2.play();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions accordingly
        }
    }

    private void handleKeyEvents(Scene scene, Group root) {
        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (keyCode.isLetterKey()) {    //checks if key is leter
                handleFrogMovement(keyCode, root);
            }
        });
    }
    private void handleFrogMovement(KeyCode keyCode, Group root) {
        boolean flyFound = false; // flie at proximity

        for (Frog frog : ecosystem.getTabFrog()) {  //for all frogs
            if (keyCode == frog.getKeycode()) { //pressed key=frog's key
                Iterator<Fly> iterator = ecosystem.getTabFly().iterator();  //iterates on all flies

                while (iterator.hasNext()) {
                    Fly fly = iterator.next();

                    if (fly.getNearest_frog() == frog) {    //fly at proximity

                        frog.eat(fly);  //eating fly
                        frog.getTextLife().setText(String.valueOf(frog.getLife())); //updating display of life

                        score += 20;    //TODO link too player's score using class Player
                        scoreText.setText("Score: " + score);

                        //adding a tongue for the frog
                        Line tongue = createTongue(frog.getImageView(), fly.getImageView());
                        root.getChildren().add(tongue); //displaying

                        try{
                        playSound("target/classes/sound/normal-hitnormal.mp3");
                        } catch (Exception e) {
                            e.printStackTrace();}

                            // Delay for 0.5 seconds
                        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                        pause.setOnFinished(e -> root.getChildren().remove(tongue));    //removes tongue
                        pause.play();
                        // Stop the timeline associated with the fly
                        fly.getTimeline().stop();

                        //removes fly from UI
                        root.getChildren().remove(fly.getImageView());

                        //removes fly from the list
                        iterator.remove();

                        flyFound = true;
                        break;
                    }
                }

                if (!flyFound) {
                    // Aucune mouche trouvée à proximité, le joueur perd 10 points
                    score -= 10;
                    scoreText.setText("Score: " + score);

                    // Display "fail.png" at the bottom right of the frog
                    displayFailImage(frog.getImageView(), root);
                    try{
                    playSound("target/classes/sound/miss.mp3");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (ecosystem.getTabFly().isEmpty()) {
                    stopGameAndShowFinalScore(root);
                }
            }
        }

    }



    private Line createTongue(ImageView frogImageView, ImageView flyImageView) {
        // Create a red line between the centers of frog and fly
        Line tongue = new Line();
        tongue.setStartX(frogImageView.getX() + frogImageView.getFitWidth() * 0.85);
        tongue.setStartY(frogImageView.getY() + frogImageView.getFitHeight() * 0.25);
        tongue.setEndX(flyImageView.getX() + flyImageView.getFitWidth() / 2);
        tongue.setEndY(flyImageView.getY() + flyImageView.getFitHeight() / 2);
        tongue.setStroke(Color.rgb(255, 0, 0, 0.5));
        tongue.setStrokeWidth(2);
        return tongue;
    }

    private void displayFailImage(ImageView frogImageView, Group root){
            try {
                Image failImage = new Image(getClass().getResourceAsStream("/images/fail.png"));
                ImageView failImageView = new ImageView(failImage);

                double desiredWidth = 10; // Adjust as needed
                double desiredHeight = 10; // Adjust as needed

                failImageView.setFitWidth(desiredWidth);
                failImageView.setFitHeight(desiredHeight);

                // sets position at the bottom right of the frog
                failImageView.setX(frogImageView.getX() + frogImageView.getFitWidth() - failImageView.getFitWidth());
                failImageView.setY(frogImageView.getY() + frogImageView.getFitHeight() - failImageView.getFitHeight());

                root.getChildren().add(failImageView); //display
                TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), failImageView);    //bounce effect
                translateTransition.setByY(10); // Move up
                translateTransition.setAutoReverse(true);
                translateTransition.play();

                //delay
                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                pause.setOnFinished(e -> root.getChildren().remove(failImageView));
                pause.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    private void stopGameAndShowFinalScore(Group root) {
        // white background
        try {
        ImageView bgImageView = new ImageView(new Image(getClass().getResource("/images/white_bg.png").toExternalForm()));
        bgImageView.setFitWidth(WINDOW_WIDTH);
        bgImageView.setFitHeight(WINDOW_HEIGHT);
        root.getChildren().add(bgImageView);
        mediaPlayer.stop(); //stops music     //TODO music

        //display the final score
        TextField finalScoreText = new TextField("Final Score: " + score);
        finalScoreText.setEditable(false);
        finalScoreText.setStyle("-fx-font-size: 24;");
        finalScoreText.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        finalScoreText.setAlignment(Pos.CENTER);
        finalScoreText.setOpacity(0.85);

        Button replayButton = new Button("Restart Game ? ");
        replayButton.setFont(Font.font("Verdana"));
        replayButton.setOpacity(0.85);

        replayButton.setOnAction(event -> {
            // Get the reference to the current window's Stage
            Stage currentStage = (Stage) replayButton.getScene().getWindow();

            // Close the current window
            currentStage.close();

            Stage primaryStage=new Stage();
            // Start the game with the specified number of flies
            MainMenu menu = new MainMenu();
            menu.start(primaryStage);
        });

        VBox vbox = new VBox(10); // Vertical spacing between elements
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(replayButton,finalScoreText);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(bgImageView, vbox);
        root.getChildren().add(stackPane);  //dsiplays replay button
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
