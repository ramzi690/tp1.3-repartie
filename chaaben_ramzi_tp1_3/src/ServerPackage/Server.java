package ServerPackage;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server is running and waiting for client connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                // Read the operation from the client
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String operation = reader.readLine();

                // Parse and perform the calculation
                double result = calculate(operation);

                // Send the result back to the client
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                writer.println("Result: " + result);

                clientSocket.close();
                System.out.println("Client disconnected.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double calculate(String operation) {
        String[] parts = operation.split(" ");
        if (parts.length != 3) {
            return Double.NaN; // Handle invalid input
        }

        double operand1 = Double.parseDouble(parts[0]);
        double operand2 = Double.parseDouble(parts[2]);
        String operator = parts[1];

        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    return Double.NaN; // Handle division by zero
                }
            default:
                return Double.NaN; // Handle invalid operator
        }
    }
}
