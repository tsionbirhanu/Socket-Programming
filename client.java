import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 8083;

        try (Socket socket = new Socket(hostname, port)) {
            System.out.println("Connected to the server!");

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String text;

            System.out.println("Enter messages to send to the server (type 'exit' to quit):");
            while (true) {
                text = consoleReader.readLine();
                writer.println(text);
                if ("exit".equalsIgnoreCase(text)) {
                    break;
                }
                String serverMessage = reader.readLine();
                System.out.println(serverMessage);
            }

            socket.close();
            System.out.println("Connection closed.");
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Input/output error: " + ex.getMessage());
        }
    }
}
