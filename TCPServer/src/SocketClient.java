import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) {
        Scanner inputStream = null;
        PrintWriter outputStream = null;

        try (Socket clientSocket = new Socket("localhost", 2222);
                Scanner sc = new Scanner(System.in)) {
            inputStream = new Scanner(clientSocket.getInputStream());
            outputStream = new PrintWriter(clientSocket.getOutputStream(), true);

            System.out.println(inputStream.nextLine());
            outputStream.println(sc.nextLine());

            System.out.println(inputStream.nextLine());
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }
}
