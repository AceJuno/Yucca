package yucca;

import java.io.*;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;


/**
 * Za pomocą tej klasy wczytujemy parametry do gry oraz je przechowujemy.
 * 
 * @author Kamil Cieszyńskis
 */



public class ConfigReader {

	//Zmienne parametryzowane wczytywane z pliku par.txt
	
	private static String nazwaGry;
	private static int liczbaPoziomów;
	private static String nazwaBazowaPlikuZOpisemPoziomu;
	private static int numeracjaPoziomówZaczynaSięOd;
	private static String rozszerzeniePlikuZOpisemPoziomu;
	private static int liczbaStopniTrudności;
	private static int zmianaStopniaTrudności;
	private static int początkowaSzerokośćPlanszy;
	private static int początkowaWysokośćPlanszy;
	private static float początkowaSzerokośćObiektuGryJakoProcentPoczątkowejSzerokościPlanszy;
	private  static Boolean czyJednolite;
	private static int kolorTłaR;
	private static int kolorTłaG;
	private static int kolorTłaB;
	private static Boolean czyPlikGraficzny;
	private static String plikObiektu;
	private static Boolean DEBUG = false;
	
	private static double effectsVolume = 50;
	public static DoubleProperty musicVolume = new SimpleDoubleProperty(50);
	private static double lvlDfclty = 2;
	/**
	 * Konstruktor bez paramtrów który wczytuje od razu zmienne z pliku.
	 */
	ConfigReader(){
		readFile();
	}
	
	
	/**
	 * Funkcja wczytująca zmienne parametryzowane z pliku.
	 */
public static void readFile() {	
	String fileName = "par.txt";
    String line = null;
    
    try {
        // FileReader reads text files in the default encoding.
        FileReader fileReader = 
            new FileReader(fileName);

        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = 
            new BufferedReader(fileReader);

        while((line = bufferedReader.readLine()) != null) {
        	String temp;
        	if (line.charAt(0) == '#') continue;
        	else if (line.contains("nazwaGry")) {
            	nazwaGry = line.substring(line.indexOf('=')+1);
            	if(DEBUG == true) System.out.println(nazwaGry);
            }
        	else if (line.contains("liczbaPoziomów")) {
        		temp = line.substring(line.indexOf('=')+1);
        		liczbaPoziomów = Integer.parseInt(temp.substring(0));
        		if(DEBUG == true) System.out.println(liczbaPoziomów);
        	}
        	else if (line.contains("nazwaBazowaPlikuZOpisemPoziomu")) {
        		nazwaBazowaPlikuZOpisemPoziomu = line.substring(line.indexOf('=')+1);
        		if(DEBUG == true) System.out.println(nazwaBazowaPlikuZOpisemPoziomu);
        	}
        	else if (line.contains("numeracjaPoziomówZaczynaSięOd")) {
        		temp = line.substring(line.indexOf('=')+1);
        		numeracjaPoziomówZaczynaSięOd = Integer.parseInt(temp.substring(0));
        		if(DEBUG == true) System.out.println(numeracjaPoziomówZaczynaSięOd);
        	}
        	else if (line.contains("rozszerzeniePlikuZOpisemPoziomu")) {
        		rozszerzeniePlikuZOpisemPoziomu = line.substring(line.indexOf('=')+1);
        		if(DEBUG == true) System.out.println(rozszerzeniePlikuZOpisemPoziomu);
        	}
        	else if (line.contains("liczbaStopniTrudności")) {
        		temp = line.substring(line.indexOf('=')+1);
        		liczbaStopniTrudności = Integer.parseInt(temp.substring(0));
        		if(DEBUG == true) System.out.println(liczbaStopniTrudności);
        	}
        	else if (line.contains("zmianaStopniaTrudności")) {
        		temp = line.substring(line.indexOf('=')+1);
        		zmianaStopniaTrudności = Integer.parseInt(temp.substring(0));
        		if(DEBUG == true) System.out.println(zmianaStopniaTrudności);
        	}
        	else if (line.contains("początkowaSzerokośćPlanszy")) {
        		temp = line.substring(line.indexOf('=')+1);
        		początkowaSzerokośćPlanszy = Integer.parseInt(temp.substring(0));
        		if(DEBUG == true) System.out.println(początkowaSzerokośćPlanszy);
        	}
        	else if (line.contains("początkowaWysokośćPlanszy")) {
        		temp = line.substring(line.indexOf('=')+1);
        		początkowaWysokośćPlanszy = Integer.parseInt(temp.substring(0));
        		if(DEBUG == true) System.out.println(początkowaWysokośćPlanszy);
        	}
        	else if (line.contains("początkowaSzerokośćObiektuGryJakoProcentPoczątkowejSzerokościPlanszy")) {
        		temp = line.substring(line.indexOf('=')+1);
        		początkowaSzerokośćObiektuGryJakoProcentPoczątkowejSzerokościPlanszy = Float.parseFloat(temp.substring(0));
        		if(DEBUG == true) System.out.println(początkowaSzerokośćObiektuGryJakoProcentPoczątkowejSzerokościPlanszy);
        	}
        	else if (line.contains("tło")) {
        		temp = line.substring(line.indexOf('=')+1);
        		if (temp.equals("jednolite")) czyJednolite = true;
        		else czyJednolite = false;
        		if(DEBUG == true) System.out.println(czyJednolite);
        	}
        	else if (line.contains("kolorTła")) {
        		temp = line.substring(line.indexOf('=')+1);
        		kolorTłaR = Integer.parseInt(temp.substring(0, temp.indexOf(' ')));
        		if(DEBUG == true) System.out.println(kolorTłaR);
        		temp = temp.substring(temp.indexOf(' ')+1);
        		kolorTłaG = Integer.parseInt(temp.substring(0, temp.indexOf(' ')));
        		if(DEBUG == true) System.out.println(kolorTłaG);
        		temp = temp.substring(temp.indexOf(' ')+1);
        		kolorTłaB = Integer.parseInt(temp);
        		if(DEBUG == true) System.out.println(kolorTłaB);
        	}
        	else if (line.contains("obiektyGry")) {
        		temp = line.substring(line.indexOf('=')+1);
        		if (temp.equals("plikGraficzny")) czyPlikGraficzny = true;
        		else czyPlikGraficzny = false;
        		if(DEBUG == true) System.out.println(czyPlikGraficzny);
        	}
        	else if (line.contains("figuraObiektuGry")) {
        		temp = line.substring(line.indexOf('=')+1);
        		plikObiektu = temp;
        		if(DEBUG == true) System.out.printf(plikObiektu);
			}
        	else if (line.contains("plikObiektu")) {
        		plikObiektu = line.substring(line.indexOf('=')+1);
        		if(DEBUG == true) System.out.println(plikObiektu);
        	}
            
        }   

        // Always close files.
        bufferedReader.close();         
    }
    catch(FileNotFoundException ex) {
        System.out.println(
            "Unable to open file '" + 
            fileName + "'");                
    }
    catch(IOException ex1) {
        System.out.println(
            "Error reading file '" 
            + fileName + "'");          
    }

}

/**
 * Funkcja zwracająca wysokość okna.
 * @return int
 */
public static int getHeight() {
	return początkowaWysokośćPlanszy;
}

/**
 * Funkcja zwracająca szerokość okna.
 * @return int
 */
public static int getWidth() {
	return początkowaSzerokośćPlanszy;
}

/**
 * Funkcja zwracająca czerwoną składową koloru tła.
 * @return int
 */
public static int getColorR() {
	return kolorTłaR;
}

/**
 * Funkcja zwracająca zieloną składową koloru tła.
 * @return int
 */
public static int getColorG() {
	return kolorTłaG;
}

/**
 * Funkcja zwracająca nazwe gry.
 * @return string
 */
public static String getGameName() {return nazwaGry;}

/**
 * Funkcja zwracająca niebieską składową koloru tła.
 * @return int
 */
public static int getColorB() {
	return kolorTłaB;
}

	/**
	 * Funkcja zwracająca liczbe poziomów.
	 * @return int
	 */
	public static int getNumLevel() {
	return liczbaStopniTrudności;
}
	/**
	 * Funkcja zwracająca poziom gloscnosci efektow.
	 * @return int
	 */
public static double getEffectsVolume() {
	return effectsVolume;
}
	/**
	 * Funkcja ustawiająca poziom głosności efektó.
	 * @param x double
	 */
public static void setEffectsVolume(double x) {
	effectsVolume = x;
}
	/**
	 * Funkcja zwracająca obecnie ustawiony poziom trudnosci.
	 * @return double
	 */
public static double getDfclty() {
	return lvlDfclty;
}
	/**
	 * Funkcja ustawiająca poziom trudnośći.
	 * @param x double
	 */
public static void setDfclty(double x) {
	lvlDfclty = x;
}
	/**
	 * Funkcja zwracająca poziom głośności muzyki.
	 * @return double
	 */
public static double getMusicVolume() {
	return musicVolume.get();
}
	/**
	 * Funkcja ustawiająca poziom głośności muzyki.
	 * @param x double
	 */
public static void setMusicVolume(double x) {
	musicVolume.setValue(x);;
}
	/**
	 * Funkcja zwracająca nazwe pliku tła.
	 * @return int
	 */
public static String getFileName(){return plikObiektu;}
	/**
	 * Funkcja zwracająca wartość logiczną zależną od tego czy wyświetlamy tło z pliku czy z RGB.
	 * @return int
	 */
public static Boolean getIfFile(){return czyPlikGraficzny;}




}