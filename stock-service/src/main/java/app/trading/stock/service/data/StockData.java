package app.trading.stock.service.data;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public interface StockData {
    List<String> getAllSymbols(String pathFile);
}
