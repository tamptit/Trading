package app.trading.stock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    @GetMapping("/stocks")
    String testRunSuccess(){
        return "OK";
    }
}
