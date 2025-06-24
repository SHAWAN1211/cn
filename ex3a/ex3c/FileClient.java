import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";  // Change to server IP if remote
        int port = 5000;

        // File to send (make sure it exists in the same folder)
        File file = new File("sample.txt");

        // Check if the file exists
        if (!file.exists()) {
            System.out.println("File not found: " + file.getName());
            return;
        }

        try (
            Socket socket = new Socket(serverAddress, port);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            FileInputStream fis = new FileInputStream(file)
        ) {
            System.out.println("Connected to server. Sending file: " + file.getName());

            // Send filename and size
            dos.writeUTF(file.getName());
            dos.writeLong(file.length());

            // Send file data
            byte[] buffer = new byte[4096];
            int read;
            while ((read = fis.read(buffer)) > 0) {
                dos.write(buffer, 0, read);
            }

            System.out.println("File sent successfully.");

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}
