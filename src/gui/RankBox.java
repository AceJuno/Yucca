package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.print.Collation;
import javafx.scene.control.ListView;
import yucca.Client;
import yucca.Test;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Klasa opisuje okno rankingu graczy.
 * 
 * @author Kamil Cieszyński
 */



public class RankBox {

    public static int extractDigits(String src){
        int ans;
        String temp[] = src.split(" ");

        ans = Integer.parseInt(temp[0]);

        return ans;
    }
    public static ArrayList<String> sort(ArrayList<String> T){
        int less = T.size()-1;
        int more = less -1 ;


        String temp;
        while(more >=0){
            if(extractDigits(T.get(more)) < extractDigits(T.get(less))){
                temp = T.get(more);
                T.set(more,T.get(less));
                T.set(less,temp);

                less = T.size()-1;
                more = less -1 ;

            }
            else{
                more--;
                less--;
            }
        }
        return T;

    }



	/**
	 * Funkcja otwierająca okienko rankingu.
	 */
    public static void display(){

        Stage stage = new Stage();

        Label label =  new Label();
        Button buttonOK =  new Button("OK, go back to Menu");
        VBox layout = new VBox(10);


        Client cl = new Client();
        cl.ClientStart();

        ListView<String> list = new ListView<String>();
        ArrayList <String> ranking = sort(cl.getRanking());

        ObservableList<String> items = FXCollections.observableArrayList(ranking);
        list.setItems(items);

        stage.initModality(Modality.APPLICATION_MODAL);
        label.setText("Top Players");

        buttonOK.setOnAction(e -> stage.close());


        layout.getChildren().addAll(label,list,buttonOK);
        layout.setAlignment(Pos.CENTER);


        Scene scene = new Scene(layout,600,400);
        stage.setScene(scene);

        stage.showAndWait();
    }





}
