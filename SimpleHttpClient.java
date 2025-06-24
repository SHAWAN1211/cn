package ex3a;
import java.io.*;
import java.net.*;

public class SimpleHttpClient {
    public static void main(String[] args) {
        String hostname = "example.com"; // Change to any site
        int port = 80; // Default HTTP port
        String path = "/"; // Path to the page (root)

        try (Socket socket = new Socket(hostname, port)) {

            // Output stream to send HTTP GET request
            PrintWriter request = new PrintWriter(socket.getOutputStream(), true);

            // Send GET request to the server
            request.println("GET " + path + " HTTP/1.1");
            request.println("Host: " + hostname);
            request.println("Connection: close");
            request.println(); // Blank line to end request

            // Input stream to read server response
            BufferedReader response = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Print the response
            String line;
            while ((line = response.readLine()) != null) {
                System.out.println(line);
            }

        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + hostname);
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}
