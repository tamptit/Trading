package app.trading.stock.service.data;

import java.util.List;
import java.util.Set;

public interface StockService {
    List<String> getSymbols(String pathFile, List<String> stockId);
}
