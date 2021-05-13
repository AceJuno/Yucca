package gui;

import javafx.geometry.Pos;
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

/**
 * Klasa która opisuje okienko pauzy.
 *
 * @author Kamil Cieszyński
 */

public class PauseBox {
    Stage stage = new Stage();
    Label label = new Label();
    Button buttonEnd = new Button("End Game");
    /**
     * Konstruktor klasy PauseBox z parametrem.
     * @param score int
     */

    public PauseBox(int score) {

        stage.setTitle("Pause");
        stage.setWidth(300);
        stage.setHeight(300);
        stage.initModality(Modality.APPLICATION_MODAL);

        buttonEnd.setMinSize(130,60);



        label.setText("Your score is " + score +"\nPress ESC to continue");




        VBox layout = new VBox(20);
        layout.getChildren().addAll(label,buttonEnd);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout,300,300);



        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent key)
            {if(key.getCode()==KeyCode.ESCAPE) {
                stage.close();}
            }
        });




        buttonEnd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {

                GameMap.endGame=true;
               stage.close();




            }
        });


        stage.setScene(scene);
        stage.showAndWait();
    }



}
