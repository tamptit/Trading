package app.trading.stock.board;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class PriceBoard {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Stock Price Server running on port 12345...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
//                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Start a new thread to handle each client
                Thread clientHandlerThread = new Thread(() -> handleClient(clientSocket));
                clientHandlerThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try {
            System.out.println("handleClient running ...");
            OutputStream outputStream = clientSocket.getOutputStream();

            // Send stock prices to the client every second
            while (true) {
                String stockPrice = generateRandomStockPrice();
                outputStream.write(stockPrice.getBytes());
                System.out.println("Sent to " + clientSocket.getInetAddress() + ": " + stockPrice);

                // Sleep for one second before sending the next update
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String generateRandomStockPrice() {
        Random random = new Random();
        double price = 100 + (random.nextDouble() * 50); // Random price between 100 and 150
        return String.format("Stock Price: %.2f%n", price);
    }
}
