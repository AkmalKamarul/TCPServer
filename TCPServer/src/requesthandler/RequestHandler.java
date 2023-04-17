package requesthandler;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class RequestHandler implements Runnable {
    private final Socket s;

    public RequestHandler(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        System.out.println("RequestHandler started for " + s.getPort());
        try (Scanner sc = new Scanner(s.getInputStream());
                PrintWriter pw = new PrintWriter(s.getOutputStream(), true)) {
            pw.println("Server ready. Type your name: ");

            while (sc.hasNextLine()) {
                String name = sc.nextLine();
                pw.println("Hey " + name + ", have a wonderful day!");
            }
            System.out.println("RequestHandler done!");
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }
}
