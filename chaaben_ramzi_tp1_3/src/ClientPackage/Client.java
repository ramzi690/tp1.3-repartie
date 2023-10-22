package ClientPackage;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);

            // Read the operation from the user
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter an operation (e.g., 34 * 5): ");
            String operation = reader.readLine();

            // Send the operation to the server
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(operation);

            // Receive and print the result from the server
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String result = serverReader.readLine();
            System.out.println("Server response: " + result);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
