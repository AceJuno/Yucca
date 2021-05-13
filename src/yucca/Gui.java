package yucca;

import java.io.File;

import gui.Menu;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * Klasa uruchamiająca GUI, dziedziczy po klasie Application.
 * 
 * @author Kamil Cieszyński
 */


public class Gui extends Application {
	static double x = 600;
	static double y = 300;
	String music = "src/gui/admiralbob77_-_Stayin_Dynamite_.mp3";
	Media sound2 = new Media(new File(music).toURI().toString());
	public MediaPlayer musicPlayer = new MediaPlayer(sound2);
	
	
	/**
	 * Funkcja otwierająca pierwszą scenę z GUI czyli menu.
	 */
	
	@Override 
	public void start(Stage primaryStage) throws Exception {
	
		new Menu();
		ConfigReader.musicVolume.addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				musicPlayer.setVolume((ConfigReader.getMusicVolume())/100);
			}
			
		});
		musicPlayer.setOnEndOfMedia(new Runnable() {
	        @Override
	        public void run() {
	            musicPlayer.seek(Duration.ZERO);
	            musicPlayer.play();
	        }
	    });
		musicPlayer.setAutoPlay(true);
	}

}