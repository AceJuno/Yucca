package yucca;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ta klasa służy do połączenia aplikacji z serwerem.
 *
 * @author Kamil Cieszyński
 */



public class Client {
    static ArrayList<String> ranking = new ArrayList<String>();
    static String ip;

    public Client() {
    }
    /**
     * Funkcja obsługująca połączenie z serwerem podczas startu aplikacji, pobiera najlepsze wyniki.
     *
     */
    public void ClientStart() {
        try {
            Socket socket = new Socket("localhost", 59091);
            Scanner in = new Scanner(socket.getInputStream());
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);

            System.out.println("Server response: " + in.nextLine());

            ranking.clear();
            int sizelist = Integer.parseInt(in.nextLine());
            for (int i = 0; i < sizelist; i++) {
                String temp = in.nextLine();
                System.out.println("Zawodnik: " + temp);
                ranking.add(temp);


            }
            System.out.println(ranking);
            pw.println("ACK");




        } catch (Exception e) {
            System.err.println("Client exception: " + e);
        }
    }
    /**
     * Funkcja wysyłająca wynik gracza do serwera po przegranej.
     * @param score String
     */
    public void ClientSend(String score) {
        try {
            Socket socket = new Socket("localhost", 59091);
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);

            pw.println(score);

        } catch (Exception e) {
            System.err.println("Client exception: " + e);
        }


    }
    /**
     * Funkcja zwracająca ranking z wynikami.
     * @return ArrayList String
     */
    public ArrayList<String> getRanking() {
        return ranking;
    }
}
