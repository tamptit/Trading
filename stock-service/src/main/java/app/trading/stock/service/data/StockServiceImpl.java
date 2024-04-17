package app.trading.stock.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService{
    private final StockData stockData;

    @Autowired
    public StockServiceImpl(StockData stockData) {
        this.stockData = stockData;
    }

    @Override
    public List<String> getSymbols(String pathFile, List<String> stockIds) {
        List<String> symbols = stockData.getAllSymbols(pathFile);
        if (stockIds != null) {
            List<String> filteredStocks = stockIds.stream()
            .filter(symbols::contains).toList();
            return  filteredStocks;
        }
        return symbols;
    }
}
