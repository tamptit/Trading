package app.trading.stock.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class StockPriceClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to Stock Price Server...");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Read and print stock prices received from the server
            while (true) {
                String stockPrice = reader.readLine();
                System.out.println("Received: " + stockPrice);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
