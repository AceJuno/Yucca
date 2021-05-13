package gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

/**
 * Klasa opisująca obiekty wybuchów.
 *
 * @author Kamil Cieszyński
 */

public class BoomObj {
    private double positionX;
    private double positionY;
    //private ArrayList<Image> images;
    private Image image1= new Image(new File("src/gui/expPNG/tank_explosion1.png").toURI().toString());
    private Image image2= new Image(new File("src/gui/expPNG/tank_explosion2.png").toURI().toString());
    private Image image3= new Image(new File("src/gui/expPNG/tank_explosion3.png").toURI().toString());
    private Image image4= new Image(new File("src/gui/expPNG/tank_explosion4.png").toURI().toString());
    private Image image5= new Image(new File("src/gui/expPNG/tank_explosion5.png").toURI().toString());
    private Image image6= new Image(new File("src/gui/expPNG/tank_explosion6.png").toURI().toString());
    private Image image7= new Image(new File("src/gui/expPNG/tank_explosion7.png").toURI().toString());
    private Image image8= new Image(new File("src/gui/expPNG/tank_explosion8.png").toURI().toString());
    private Image image9= new Image(new File("src/gui/expPNG/tank_explosion9.png").toURI().toString());
    private Image image10= new Image(new File("src/gui/expPNG/tank_explosion10.png").toURI().toString());
    private Image image11= new Image(new File("src/gui/expPNG/tank_explosion11.png").toURI().toString());
    private Image image12= new Image(new File("src/gui/expPNG/tank_explosion12.png").toURI().toString());
    private Image[] images= new Image[] {image1,image2,image3,image4,image5,image6,image7,image8,image9,image10,image11,image12};


    private double seq;

    private boolean done=false;


    /**
     * Konstruktor z parametrami wybuchu.
     * @param x double
     * @param y double
     * @param t double
     */
    BoomObj(double x,double y,double t){
        positionX = x;
        positionY = y;
        seq=t;

    }
    /**
     * Funkcja rysująca wybuch.
     * @param gc GraphicContext
     * @param t double
     */
    public void render(GraphicsContext gc,double t)
    {
        double tem = (t- seq)*11;
        int i = (int) tem;
        if(i < 12){gc.drawImage( images[i], positionX, positionY );}

        if(tem>11){done=true;}
    }
    /**
     * Funkcja zwracająca wartość logiczną czy aniamcja wybuchu się zakończyła.
     * @return boolean
     */
    public boolean ifDone(){return done;}



}
