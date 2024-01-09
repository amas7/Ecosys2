package org.openjfx.ecosys2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("FrogMania - Main Menu");

        // Create the button to start the game
        Button startButton = new Button("Start Game");
        startButton.setFont(Font.font("Verdana"));
        startButton.setOpacity(0.85);

        // Create the text field for entering the number of flies
        TextField numberOfFliesTextField = new TextField();
        TextField numberOfFrogsTextField = new TextField();

        numberOfFliesTextField.setMaxWidth(150);
        numberOfFrogsTextField.setMaxWidth(150);

        // Set the default value to 0
        numberOfFliesTextField.setText("0");
        numberOfFliesTextField.setOpacity(0.85);

        numberOfFliesTextField.setText("0");
        numberOfFrogsTextField.setOpacity(0.85);


        // create a Text node
        Text textFlies = new Text("Enter number of flies");
        textFlies.setFill(Color.LIGHTBLUE);
        textFlies.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        textFlies.setOpacity(0.85);

        Text textFrog = new Text("Number of frogs (max 10)");
        textFrog.setFill(Color.LIGHTBLUE);
        textFrog.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        textFrog.setOpacity(0.85);

        // Load the image for the background
        Image backgroundImage = new Image(getClass().getResource("/images/menu_bg.png").toExternalForm());

        // Create an ImageView for the background image
        ImageView backgroundImageView = new ImageView(backgroundImage);

        // Set the size of the ImageView to fit the window (400x400)
        backgroundImageView.setFitWidth(400);
        backgroundImageView.setFitHeight(400);

        // Create a VBox for button and text field
        VBox vbox = new VBox(10); // Vertical spacing between elements
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(startButton,textFlies, numberOfFliesTextField,textFrog,numberOfFrogsTextField);

        // Create a StackPane for the background and VBox
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImageView, vbox);

        // Event handler for the button click
        startButton.setOnAction(event -> {
                    // Retrieve the number of flies from the text field
                    try {
                        int nFlies = Integer.parseInt(numberOfFliesTextField.getText());
                        int nFrogs = Integer.parseInt((numberOfFrogsTextField.getText()));

                        if (nFrogs > 10) {

                            nFrogs = 10;
                        }

                        // Start the game with the specified number of flies
                        Game game = new Game(nFlies, nFrogs);
                        game.start(primaryStage);
                    } catch (NumberFormatException e) {
                        // Text entered is not an int
                        numberOfFliesTextField.clear(); // Vider la textbox
                        numberOfFrogsTextField.clear(); // Vider la textbox
                        // Afficher un message d'erreur à l'utilisateur, par exemple :
                        System.out.println("Veuillez entrer des nombres entiers valides pour le nombre de mouches et de grenouilles.");

                    }

        });

        // Create the scene
        Scene scene = new Scene(stackPane, 400, 400); // Adjust the scene size as needed
        primaryStage.getIcons().add(new javafx.scene.image.Image("file:src/main/resources/images/icon.jpg"));


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
