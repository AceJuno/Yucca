package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Klasa która opisuje działanie serwera. Ma własnego maina do startu serwera
 *
 * @author Kamil Cieszyński
 */
public class configserver {


	
	
	
	
	public static void main(String[] args) throws IOException {
        String lsep = System.getProperty("line.separator");
        // test array
	    ArrayList<String> ranking =  new ArrayList<String>();
        ranking.add("12000 TestyBoiUno");
        ranking.add("6969696 XdTEST");
        ranking.add("123 THisISTEST");
        ranking.add("10000000 StillTEST");
        ranking.add("122 Noice");
        String lineT;
        BufferedReader reader = new BufferedReader(new FileReader("top2.txt"));
        int lines = 0;
        String line;
        while((line = reader.readLine()) != null){
            System.out.println(line);
            ranking.add(line);

        }





        String temp;


        try (
                ServerSocket listener = new ServerSocket(59091)) {
            System.out.println("The date server is running...");
            while (true) {
                try (

                        Socket socket = listener.accept()) {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("Connection Accepted");

                    InputStream is = socket.getInputStream();
                    Scanner in = new Scanner(socket.getInputStream());





                    out.println(ranking.size());

                    for(int i = 0; i<ranking.size(); i++){
                        out.println(ranking.get(i));


                    }
                    temp = in.nextLine();
                    if (!(temp.equals("ACK"))){
                        ranking.add(temp);
                        System.out.println("Player added " + temp);



                        RandomAccessFile raf = new RandomAccessFile("top2.txt","rw");
                        raf.seek(raf.length());

                        raf.writeBytes(temp + lsep);

                        raf.close();
                    }

                }
            }
        }
    }
}
