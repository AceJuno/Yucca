package gui;

import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import yucca.Client;


/**
 * Klasa która opisuje okienko z wynikiem gracza po przegraniu.
 *
 * @author Kamil Cieszyński
 */
public class ScoreBox extends Stage {


    Stage stage = new Stage();


    Label label = new Label();
    Button buttonOK = new Button("Ok");

    /**
            * Konstruktor klasy ScoreBox z parametrami.
     * @param i int
     * @param j String
     */
    public ScoreBox(int i,String j) {
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("You lost");
        stage.setWidth(600);
        stage.setHeight(500);



        label.setText(j + "\nYour score is " + i);

        label.setFont(new Font("Cambria", 60));

        Client cl = new Client();
        cl.ClientSend(i + " " + j);

        buttonOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                new Menu();
                Stage stage = (Stage) getScene().getWindow();
                stage.close();
            }
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, buttonOK);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout,600,400);

        stage.setScene(scene);

        this.setScene(scene);
        this.show();






    }


}
