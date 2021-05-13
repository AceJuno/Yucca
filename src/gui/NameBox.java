package gui;
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
import javafx.geometry.*;

/**
 * Klasa opisująca okienko w którym gracz wpisuje nick.
 *
 * @author Kamil Cieszyński
 */


public class NameBox {
    private static String nick;
    public static String getNick(){return nick;}


    /**
     * Funkcja otwierająca okienko nicku.
     */
    public static void display(){
        Stage stage = new Stage();

        TextField txtNick = new TextField();

        txtNick.setMaxWidth(200);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Podaj Nick");
        stage.setWidth(250);
        stage.setHeight(140);

        Label label = new Label();
        label.setText("Enter your nick:");
        Button buttonOK = new Button("OK");
        buttonOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                nick=txtNick.getText();
                stage.close();
            }
        });
        
        

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,txtNick,buttonOK);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent key)
            {
                if(key.getCode()==KeyCode.ENTER) 
                {
                	nick=txtNick.getText();
                    stage.close();
                }	
            }
        });
        
        stage.setScene(scene);

        stage.showAndWait();

    }


}
