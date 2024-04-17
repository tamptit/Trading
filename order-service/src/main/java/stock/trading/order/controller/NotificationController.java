package stock.trading.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @GetMapping("/notify/1000")
    String testRunSuccess(){
        return "OK";
    }
}
