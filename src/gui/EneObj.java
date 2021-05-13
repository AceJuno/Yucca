package gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import yucca.ConfigReader;

import java.io.File;

/**
 * Klasa opisująca obiekty przeciwników.
 *
 * @author Kamil Cieszyński
 */
public class EneObj {
    private Image image;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    private double itime;
    private Image imageLife= new Image(new File("src/gui/tanks_life.png").toURI().toString());

    private int lifes;

    /**
     * Konstruktor z parametrami przeciwnika.
     * @param X double
     * @param Y double
     * @param VX double
     * @param VY double
     * @param im Image
     */
    public EneObj(double X, double Y,double VX,double VY,Image im)
    {
        positionX = X;
        positionY = Y;
        velocityX = VX;
        velocityY = VY;
        image = im;
        width = im.getWidth();
        height = im.getHeight();
        itime = 0;
        lifes = (int) (Math.ceil(Math.random()*3));


    }

    /**
     * Funkcja uaktualniająca położenie przeciwnika.
     * @param time double
     * @param x double
     * @param y double
     * @param scaleH double
     * @param scaleW double
     */
    public void update(double time,double x,double y, double scaleH, double scaleW) {
        if (itime == 0) {
            itime = time;
        } else {
            double ty = y - positionY*scaleH;
            double tx = x - positionX*scaleW;
            double ta = ty / tx;
            double t = time - itime;
            double angle = Math.atan(1/ta);


            //positionX += ((1/ta)*velocityX * 10) / 10;
            //positionY += (velocityY * 10) / 10;

            positionX += (Math.sin(angle)*velocityX  ) *(0.5+ ConfigReader.getDfclty());
            positionY += (Math.cos(angle)*velocityY ) *(0.5+ ConfigReader.getDfclty());
        }
    }


    /**
     * Funkcja rysująca obiekt przeciwnika.
     * @param gc GraphicContext
     * @param scaleH double
     * @param scaleW double
     */
    public void render(GraphicsContext gc, double scaleH, double scaleW)
    {

        if (ConfigReader.getIfFile()) gc.drawImage( image, positionX * scaleW, positionY * scaleH, image.getWidth()*scaleW, image.getHeight() * scaleH);
        else if(ConfigReader.getFileName().equals("kwadraty")) gc.fillRect(positionX * scaleW,positionY * scaleH,image.getHeight(),image.getHeight());
        else if (ConfigReader.getFileName().equals("prostokąty")) gc.fillRect(positionX * scaleW,positionY * scaleH,image.getHeight(),image.getWidth());
        else if(ConfigReader.getFileName().equals("trójkąty")) gc.fillPolygon(new double[]{positionX,positionX + 50.0, positionX + 100.0},new double[]{positionY + 100.0,positionY + 50.0,positionY + 100.0}, 3);
        else if(ConfigReader.getFileName().equals("kółka")) gc.fillOval(positionX*scaleW,positionY*scaleH,image.getWidth()*scaleH,image.getWidth()*scaleH);
        switch(lifes){
            case 3:
                gc.drawImage(imageLife,positionX * scaleW,(positionY+70)*scaleH, imageLife.getWidth() * scaleW,imageLife.getHeight() * scaleH);

            case 2:
                gc.drawImage(imageLife,(positionX +30)*scaleW,(positionY+70)*scaleH, imageLife.getWidth() * scaleW,imageLife.getHeight() * scaleH);
            case 1:
                gc.drawImage(imageLife,(positionX + 60)*scaleW,(positionY+70)*scaleH, imageLife.getWidth() * scaleW,imageLife.getHeight() * scaleH);

        }
    }

    /**
     * Funkcja ustawiająca obrazek przeciwnika.
     * @param i Image
     */
    public void setImage(Image i)
    {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }
    /**
     * Funkcja ustawiająca obrazek przeciwnika.
     * @param filename String
     */
    public void setImage(String filename)
    {
        Image i = new Image(filename);
        setImage(i);
    }
    /**
     * Funkcja ustawiająca pozycje przeciwnika.
     * @param x double
     * @param y double
     */
    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
    }
    /**
     * Funkcja sprawdzająca czy przeciwnik dotarł do bazy.
     * @param x double
     * @param y double
     * @param scaleH double
     * @param scaleW double
     * @return boolean
     */
    public boolean checkIfBase(double x,double y, double scaleH, double scaleW){
        boolean temp = x-40*scaleW<positionX*scaleW && x+40*scaleW>positionX*scaleW && y-40*scaleH <positionY*scaleH && y+40*scaleH >positionY*scaleH;
        return temp;

    }
    /**
     * Funkcja sprawdzająca czy przeciwnik zginął.
     * @param x double
     * @param y double
     * @param ammo int
     * @param scaleW double
     * @param scaleH double
     * @return boolean
     */
    public boolean checkIfDead(double x,double y,int ammo, double scaleH, double scaleW){
        boolean temp = (x-80*scaleW)<positionX*scaleW && x>positionX*scaleW && (y-80*scaleH) <positionY*scaleH && y >positionY*scaleH;
        if(temp){--lifes;}
        temp=false;
        if(lifes==0){temp=true;}
        if(ammo>0 && (x-80*scaleW)<positionX*scaleW && x>positionX*scaleW && (y-80*scaleH) <positionY*scaleH && y >positionY*scaleH){temp=true;}
        return temp;

    }
    /**
     * Funkcja ustawiająca prędkość przeciwnika.
     * @param x double
     * @param y double
     */
    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }
    /**
     * Funkcja zwiększająca prędkość przeciwnika.
     * @param x double
     * @param y double
     */
    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }

    /**
     * Funkcja zwracająca położenie X przeciwnika.
     * @return  double
     */
    public double getPositionX(){
        return positionX;
    }
    /**
     * Funkcja zwracająca położenie Y przeciwnika.
     * @return  double
     */
    public double getPositionY(){
        return positionY;
    }




}
