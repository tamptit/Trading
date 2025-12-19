package app.trading.stock.controller;

import app.trading.stock.repositories.SymbolsRepository;
import app.trading.stock.response.ProductsResponse;
import app.trading.stock.service.data.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stocks")
//@Api(value = "StockController in Stock Service", description = "Operations pertaining to stock management")
public class StockController {

    private final StockService stockService;

    @Autowired
    private SymbolsRepository symbolsRepository;


//    @Autowired //StockService stockService, StockData stockData
    public StockController( StockService stockService) {
        this.stockService = stockService;
//        this.stockData = stockData;
    }

    final String filePath = "D:\\Program Files\\CafeF.SolieuGD.Upto15012024\\CafeF.UPCOM.Upto15.01.2024.csv";

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ADMIN')")
    String testRunSuccess(){
        return "OK this login by ADMIN";
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    ProductsResponse getProducts(){
        ProductsResponse response = new ProductsResponse();
        response.setSymbolsList(symbolsRepository.findAll());
        return response;
    }


    @GetMapping("/stocks")
    @PreAuthorize("hasAuthority('ADMIN')")
    List<String> getAllStocks(@RequestParam List<String> inputList){
        return stockService.getSymbols(filePath, inputList);
//        return stockData.getAllSymbols(filePath);
    }
}
