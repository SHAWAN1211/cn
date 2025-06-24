import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        int port = 5000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("File Server started on port " + port);
            
            // Wait for client connection
            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress());

            // Set up streams to receive file
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            // Read filename and file size
            String fileName = dis.readUTF();
            long fileSize = dis.readLong();
            System.out.println("Receiving file: " + fileName + " (" + fileSize + " bytes)");

            // Create output file stream
            FileOutputStream fos = new FileOutputStream("received_" + fileName);
            byte[] buffer = new byte[4096];
            int read;
            long remaining = fileSize;

            while ((read = dis.read(buffer, 0, (int) Math.min(buffer.length, remaining))) > 0) {
                fos.write(buffer, 0, read);
                remaining -= read;
            }

            System.out.println("File received successfully: received_" + fileName);

            // Cleanup
            fos.close();
            dis.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}
