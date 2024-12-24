import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) {
        int port = 8083; 
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            String clientMessage;
            while ((clientMessage = reader.readLine()) != null) {
                System.out.println("Client: " + clientMessage);
                writer.println("Server: Received -> " + clientMessage);
                if ("exit".equalsIgnoreCase(clientMessage)) {
                    System.out.println("Client disconnected.");
                    break;
                }
            }

            socket.close();
            System.out.println("Server shut down.");
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}