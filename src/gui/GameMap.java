package gui;

import java.io.File;

import javafx.scene.Cursor;
import javafx.util.Duration;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import yucca.ConfigReader;
import gui.NameBox;

/**
 * Klasa opisująca działanie gry.
 *
 * @author Kamil Cieszyński
 */




public class GameMap extends Stage {
    String nick = NameBox.getNick();
    int score = 0;
    int lives = 3;
    int ammo = 0;
    public static boolean endGame = false;



    boolean pause = false;

    BorderPane borderPane = new BorderPane();
    Stage gameMap = new Stage();
    Scene scene = new Scene(borderPane,ConfigReader.getWidth(),ConfigReader.getHeight());
    Rectangle rect = new Rectangle(100,100);
    Label nickLabel =  new Label(nick);
    Label scoreLabel = new Label("Ammo: " + ammo + " Score: " + Integer.toString(score));
    Canvas canvas = new Canvas( ConfigReader.getWidth()+1000,ConfigReader.getHeight() + 1000 );

    Image tank1 = new Image(new File("src/gui/tanks_tankDesert1.png").toURI().toString());
    Image tank2 = new Image(new File("src/gui/tanks_tankDesert2.png").toURI().toString());
    Image tank3 = new Image(new File("src/gui/tanks_tankDesert3.png").toURI().toString());
    Image ammoPack = new Image(new File("src/gui/tanks_crateAmmo.png").toURI().toString());
    Image lifePack = new Image(new File("src/gui/tanks_crateArmor.png").toURI().toString());
    Image baseFort = new Image(new File("src/gui/tanks_tankNavy_body3.png").toURI().toString());
    Image back = new Image(new File("src/gui/traw.png").toURI().toString());
    GraphicsContext gc = canvas.getGraphicsContext2D();

/**
 * Konstruktor bez parametrów obiektu gry.
 *
 */
    public GameMap(){
        this.setTitle(ConfigReader.getGameName());
        scene.setCursor(Cursor.CROSSHAIR);

        gameMap.setWidth(ConfigReader.getWidth());
        gameMap.setHeight(ConfigReader.getHeight());




        nickLabel.setFont(Font.font("Cambria", 60));
        scoreLabel.setFont(Font.font("Cambria", 18));
        nickLabel.setTextFill(Color.WHITE);
        scoreLabel.setTextFill(Color.WHITE);

        FlowPane flow = new FlowPane();


        flow.setVgap(8);
        flow.setHgap(4);

        flow.setAlignment(Pos.BASELINE_CENTER);
        flow.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));


        flow.getChildren().add(scoreLabel);
        flow.getChildren().add(nickLabel);

        Image image = new Image(new File("src/gui/heart.png").toURI().toString());
        ImageView imageView = new ImageView(image);
        ImageView imageView2 = new ImageView(image);
        ImageView imageView3 = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(20);
        imageView2.setPreserveRatio(true);
        imageView2.setFitWidth(20);
        imageView3.setPreserveRatio(true);
        imageView3.setFitWidth(20);


        final long startNanoTime = System.nanoTime();

        ArrayList<BoomObj> booms = new ArrayList<BoomObj>();
        ArrayList<EneObj> enemies  = new ArrayList<EneObj>();
        ArrayList<Image> tanks = new ArrayList<Image>();
        ArrayList<BonObj> bonuses = new ArrayList<BonObj>();

        tanks.add(tank1);
        tanks.add(tank2);
        tanks.add(tank3);


        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(0.75- (score/50)), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                int look=(int)Math.floor((Math.random()*3));

                if(!pause) {
                    System.out.println("spawn");
                    double tt = Math.random();
                    if (tt < 0.34) {
                        EneObj tempE = new EneObj(Math.random() * scene.getWidth(), 1, 1, 1, tanks.get(look));
                        enemies.add(tempE);
                    } else if (tt < 0.67) {
                        EneObj tempE = new EneObj(1, Math.random() * (scene.getHeight() - flow.getHeight() - 90), 1, 1, tanks.get(look));
                        enemies.add(tempE);
                    } else {
                        EneObj tempE = new EneObj(scene.getWidth() , Math.random() * (scene.getHeight() - flow.getHeight() - 90), 1, 1, tanks.get(look));
                        enemies.add(tempE);
                    }
                }
            }
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);

        fiveSecondsWonder.play();

        new AnimationTimer()
        {

            public void handle(long currentNanoTime) {


                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                double x = scene.getWidth() / 2 - baseFort.getWidth() / 2;
                double y = scene.getHeight()   -flow.getHeight() -baseFort.getHeight();






                gc.drawImage(back, 0, 0, ConfigReader.getWidth() + 1000, ConfigReader.getHeight() + 1000);
                gc.drawImage(baseFort, x,y);





                for(int i=0;i<bonuses.size();++i){
                    bonuses.get(i).render(gc);
                }
                for(int i=0;i<enemies.size();++i){
                    enemies.get(i).render(gc, scene.getHeight()/ConfigReader.getHeight(),scene.getWidth()/ConfigReader.getWidth());}
                for(int i=0;i<booms.size();++i){
                    if(!booms.get(i).ifDone()){booms.get(i).render(gc,t);}
                    else{booms.remove(i);}
                }


                if (!pause) {

                    for(int i=0;i<enemies.size();++i){

                        enemies.get(i).update(t,x,y,scene.getHeight()/ConfigReader.getHeight(),scene.getWidth()/ConfigReader.getWidth());

                        if( enemies.get(i).checkIfBase(x,y ,scene.getHeight()/ConfigReader.getHeight(),scene.getWidth()/ConfigReader.getWidth()))
                        {
                        	enemies.remove(i);
                        	switch(lives) {
                        	case 3:
                        		imageView.setVisible(false);
                        		lives--;
                        		break;
                        	case 2:
                        		imageView2.setVisible(false);
                        		lives--;
                        		break;
                        	case 1:
                        		imageView3.setVisible(false);
                        		lives--;


                                new ScoreBox(score,nick);
                                Stage stage = (Stage) getScene().getWindow();
                                stage.close();
                                fiveSecondsWonder.stop();


                        		break;
                        		
                        	}
                        }

                    }

                }

                scene.setOnKeyPressed(new EventHandler<KeyEvent>()
                {
                    @Override
                    public void handle(KeyEvent key)
                    {
                        if(key.getCode()==KeyCode.ESCAPE) {

                            System.out.println("Escape Test");
                            if(!pause) {
                                pause = true;
                                key.consume();
                                new PauseBox(score);
                                if(endGame){
                                    new Menu();
                                    Stage stage = (Stage) getScene().getWindow();
                                    stage.close();
                                    gameMap.close();
                                    enemies.clear();

                                    fiveSecondsWonder.stop();
                                }
                                pause=false;

                            }
                            else {
                                pause=false ;
                                key.consume();

                            }}
                    }
                });

                scene.setOnMouseClicked(new EventHandler<MouseEvent>() 
                {
                    @Override
                    public void handle(MouseEvent e) {

                        System.out.println("mouse click detected! " + e.getSource());
                        e.consume();

                        if (!pause) {
                            for (int i = 0; i < bonuses.size(); ++i) {
                                if (bonuses.get(i).checkIfClicked(e.getSceneX(), e.getSceneY())) {

                                    int tempType = bonuses.get(i).getType();
                                    bonuses.remove(i);
                                    if (tempType == 0) {
                                        if(lives<3){++lives;}

                                        switch (lives) {
                                            case 3:
                                                imageView.setVisible(true);
                                                continue;
                                            case 2:
                                                imageView2.setVisible(true);
                                                continue;
                                            case 1:
                                                imageView3.setVisible(true);
                                                break;
                                        }
                                    } else {
                                        //score += 20;
                                        ammo += 7;
                                        scoreLabel.setText("Ammo: "+ammo+" Score: " + Integer.toString(score));

                                    }
                                }
                            }
                            for (int i = 0; i < enemies.size(); ++i) {
                                if (enemies.get(i).checkIfDead(e.getSceneX(), e.getSceneY(),ammo, scene.getHeight()/ConfigReader.getHeight(),scene.getWidth()/ConfigReader.getWidth())) {
                                    if(ammo>0){--ammo;}
                                    if (0.10 > Math.random()) {
                                        BonObj bon = new BonObj(enemies.get(i).getPositionX()*(scene.getWidth()/ConfigReader.getWidth()), enemies.get(i).getPositionY()*(scene.getHeight()/ConfigReader.getHeight()));
                                        bonuses.add(bon);

                                    }

                                    BoomObj boom = new BoomObj(enemies.get(i).getPositionX()*(scene.getWidth()/ConfigReader.getWidth()), enemies.get(i).getPositionY()*(scene.getHeight()/ConfigReader.getHeight()),t);
                                    booms.add(boom);

                                    enemies.remove(i);
                                    score += 10;
                                    scoreLabel.setText("Ammo: "+ammo+" Score: " + Integer.toString(score));
                                    break;
                                }
                            }
                        }
                    }
                });
                
            }
        }.start();


        borderPane.getChildren().add(canvas);
        flow.getChildren().add(imageView);
        flow.getChildren().add(imageView2);
        flow.getChildren().add(imageView3);

        borderPane.setBottom(flow);

        this.setScene(scene);
        this.show();


    }
}
