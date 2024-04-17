package app.trading.stock.service.data;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class StockDataImpl implements StockData{


    @Override
    public List<String> getAllSymbols(String pathFile) {
        System.out.println(pathFile);
        List<List<String>> records = new ArrayList<>();
        List<String> symbols = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {
            String line;
            String datetime = "20240112";
            int lineNum = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[1].equals(datetime)) break;
//                records.add(Arrays.asList(values));
                symbols.add(values[0]);
            }
            System.out.println("----- END -----");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return symbols;
    }
}
