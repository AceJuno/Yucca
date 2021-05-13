package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.util.StringConverter;
import yucca.ConfigReader;

/**
 * Klasa która opisuje okienko opcji.
 * 
 * @author Kamil Cieszyński
 */


public class Options extends Stage {
	
	double musicVolume = ConfigReader.musicVolume.get();
	double effectsVolume = ConfigReader.getEffectsVolume();
	
	
	Stage options = new Stage();
	
	VBox layout = new VBox(10);
	Slider sliderMusic = new Slider();

	Label labelM = new Label("Music volume");
	Slider sliderEfect = new Slider();
	Label labelE = new Label("Sound effects volume");
	Slider sliderDfclty = new Slider();
	Label labelD = new Label("Difficulty");
	Button buttonBack = new Button("Powrót do menu");


	/**
	 * Konstruktor klasy, inicjuje okno.
	 */
	
	public Options() {
		
		layout.setSpacing(20);
		layout.setAlignment(Pos.CENTER);



		buttonBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				new Menu();
				Stage stage = (Stage) getScene().getWindow();
				stage.close();
			}
		});
		//Music slider
		sliderMusic.setMin(0);
		sliderMusic.setMax(100);
		sliderMusic.setValue(musicVolume);
		sliderMusic.setMaxWidth(400);
		//Effects slider
		sliderEfect.setValue(effectsVolume);
		sliderEfect.setMin(0);
		sliderEfect.setMax(100);
		sliderEfect.setMaxWidth(400);
		//Difficulty slider
		sliderDfclty.setMaxWidth(400);
        sliderDfclty.setMin(0);
        sliderDfclty.setMax(ConfigReader.getNumLevel() - 1);
        sliderDfclty.setSnapToTicks(true);
        sliderDfclty.setBlockIncrement(1);
        sliderDfclty.setMajorTickUnit(1);
        sliderDfclty.setMinorTickCount(0);
        sliderDfclty.setShowTickMarks(true);
        sliderDfclty.setShowTickLabels(true);
		sliderDfclty.setValue(ConfigReader.getDfclty());
        sliderDfclty.setLabelFormatter(new StringConverter<Double>() {
			@Override
			public String toString(Double n) {

				if (n < 0.5) return "Very Easy";
				if (n < 1.5) return "Easy";
				if (n < 2.5) return "Normal";
				if (n < 3.5) return "Hard";
				 return "Very Hard";
			}

			@Override
			public Double fromString(String string) {
				return null;
			}
		});
		//layout
		layout.getChildren().add(labelM);
		layout.getChildren().add(sliderMusic);
		layout.getChildren().add(labelE);
		layout.getChildren().add(sliderEfect);
		layout.getChildren().add(labelD);
		layout.getChildren().add(sliderDfclty);
		layout.getChildren().add(buttonBack);
		
		Scene scene = new Scene(layout,ConfigReader.getWidth(),ConfigReader.getHeight());
		
		sliderMusic.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				ConfigReader.musicVolume.setValue(new_val);
			}
			
		});
		
		sliderEfect.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				ConfigReader.setEffectsVolume(new_val.doubleValue());
			}
			
		});
		sliderDfclty.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				ConfigReader.setDfclty(new_val.doubleValue());
			}
			
		});
		

		this.setScene(scene);
		this.show();
	}

}
