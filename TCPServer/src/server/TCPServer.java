package server;

import java.net.*;

import requesthandler.RequestHandler;

public class TCPServer {
    private final int port;
    private volatile boolean running = true; // volatile keyword ensures visibility across threads

    public TCPServer(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket ss = new ServerSocket(port)) {
            while (running) { // add end condition to while loop
                System.out.println("Server Waiting...");
                Socket s = ss.accept();
                System.out.println("Server has accepted a client on port " + s.getPort());
                RequestHandler rh = new RequestHandler(s);
                new Thread(rh).start();

            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }

    public void stop() { // add method to stop server
        running = false;
    }

    public static void main(String[] args) {
        TCPServer server = new TCPServer(2222);
        server.start();
    }
}
