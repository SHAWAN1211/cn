package ex3a;

import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // or IP address
        int port = 12345;

        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("Connected to Echo Server");

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String inputLine;
            while (true) {
                System.out.print("Enter message (type 'bye' to quit): ");
                inputLine = userInput.readLine();

                if (inputLine.equalsIgnoreCase("bye")) {
                    break;
                }

                out.println(inputLine); // Send to server
                String response = in.readLine(); // Get echo
                System.out.println("Server replied: " + response);
            }

            System.out.println("Client disconnected.");
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
