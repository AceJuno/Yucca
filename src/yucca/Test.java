package yucca;

import javafx.application.Application;

import java.util.ArrayList;
import java.util.Scanner;
import java.net.Socket;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;


/**
 * Klasa w której znajduje się main(), używana również do testowania.
 * 
 * @author Kamil Cieszyński
 */


public class Test {


	public static void main(String[] args) throws IOException {

		
		
		@SuppressWarnings("unused")
		ConfigReader cg = new ConfigReader();

        Application.launch(Gui.class, args);

	}
	
}
