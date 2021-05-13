package gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;

/**
 * Klasa opisująca obiekt bonusowy.
 *
 * @author Kamil Cieszyński
 */


public class BonObj {
    private Image ammoPack = new Image(new File("src/gui/tanks_crateAmmo.png").toURI().toString());
    private Image lifePack = new Image(new File("src/gui/tanks_crateArmor.png").toURI().toString());
    private Image image;
    private double positionX;
    private double positionY;
    private double width;
    private double height;
    private double itime;
    private int type;

    /**
     * Konstruktor z parametrami obiektu bonusowego.
     * @param X double
     * @param Y double
     */
    public BonObj(double X, double Y) {
        positionX = X;
        positionY = Y;


        itime = 0;
        type =  (int)Math.round(Math.random());

        if(type==0){image=lifePack;}
        else{image=ammoPack;}

        width = image.getWidth();
        height = image.getHeight();
    }
    /**
     * Funkcja rysująca obiekt bonusowy.
     * @param gc GraphicContext
     */
    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX , positionY );
    }
    /**
     * Funkcja sprawdzająca czy bonus został naciśnięty.
     * @param x double
     * @param y double
     * @return boolean
     */
    public boolean checkIfClicked(double x,double y){
        boolean temp = x-80<positionX && x>positionX && y-80 <positionY && y >positionY;
        return temp;

    }

    public int getType(){return type;}






}
