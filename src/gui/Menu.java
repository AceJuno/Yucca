package gui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import yucca.ConfigReader;
import yucca.Gui;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import gui.Options;

/**
 * Klasa która opisuje pierwsze okno GUI czyli menu, dziedziczy po klasie Stage.
 * 
 * @author Kamil Cieszyński
 */

public class Menu extends Stage{
	VBox layout1 = new VBox();
	VBox layout2 = new VBox();
	Scene scene = new Scene(layout1, ConfigReader.getWidth(), ConfigReader.getHeight());
	
	String buttonSound = "src/gui/classic_hurt.mp3";
	Media sound1 = new Media(new File(buttonSound).toURI().toString());
	MediaPlayer mediaPlayer = new MediaPlayer(sound1);
	
	
	
    
	Label label = new Label();
	Button button1 = new Button("Start");
	Button button2 = new Button("Opcje");
	Button button3 = new Button("Ranking");
	Button button4 = new Button("Wyjdź");
	String buttonStyle = "-fx-background-color: #a6b5c9,linear-gradient(#303842 0%, #3e5577 20%, #375074 100%), linear-gradient(#768aa5 0%, #849cbb 5%, #5877a2 50%, #486a9a 51%, #4a6c9b 100%);-fx-background-insets: 0 0 -1 0,0,1;-fx-background-radius: 5,5,4;\n" + 
			"    -fx-padding: 7 30 7 30;\n" + 
			"    -fx-text-fill: #242d35;\n" + 
			"    -fx-font-family: \"Helvetica\";\n" + 
			"    -fx-font-size: 12px;\n" + 
			"    -fx-text-fill: white; ";
	
	/**
	 * Konstruktor klasy Menu bez parametrów.
	 */
	
	public Menu() {
		
	
		
		mediaPlayer.setOnEndOfMedia(() ->mediaPlayer.stop());
		mediaPlayer.setVolume(ConfigReader.getEffectsVolume()/100);
		button1.setStyle(buttonStyle);
		button2.setStyle(buttonStyle);
		button3.setStyle(buttonStyle);
		button4.setStyle(buttonStyle);
		String gameName = ConfigReader.getGameName();
		

		button1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				mediaPlayer.play();
				NameBox.display();
				new GameMap();
				Stage stage = (Stage) getScene().getWindow();
				stage.close();
			}
		});


		button2.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent t) {
				mediaPlayer.play();
	            new Options();
	            Stage stage = (Stage) getScene().getWindow();
	            stage.close();
	        }
	    });

		button3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				mediaPlayer.play();
				RankBox.display();
			}
		});
		button4.setOnAction(e -> System.exit(0));


        label.setText(gameName);
        label.setFont(new Font("Cambria", 120));

        this.setTitle(gameName);


		layout1.setSpacing(20);
		layout1.getChildren().add(label);
		layout1.setAlignment(Pos.CENTER);
		layout1.getChildren().add(button1);
		layout1.getChildren().add(button2);
		layout1.getChildren().add(button3);
		layout1.getChildren().add(button4);
		
		
		layout1.setBackground(new Background(new BackgroundFill(Color.rgb(ConfigReader.getColorR(),ConfigReader.getColorG(),ConfigReader.getColorB()), CornerRadii.EMPTY, Insets.EMPTY)));
		
		this.setScene(scene);
		
		this.show();

	}
	
}
