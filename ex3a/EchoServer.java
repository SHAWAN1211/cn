package ex3a;

import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        int port = 12345;
        boolean running = true;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Echo Server started on port " + port);
            System.out.println("To exit the server, press Ctrl+C in the terminal.");

            while (running) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received: " + inputLine);

                    if (inputLine.equalsIgnoreCase("exit")) {
                        out.println("Server shutting down...");
                        running = false;
                        break;
                    }

                    out.println("Echo: " + inputLine);
                }

                clientSocket.close();
                System.out.println("Client disconnected.");
            }

            System.out.println("Server shutting down.");

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
