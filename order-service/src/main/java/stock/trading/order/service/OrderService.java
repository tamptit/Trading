package stock.trading.order.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stock.trading.order.model.OrderSign;
import stock.trading.order.model.Sign;
import stock.trading.order.repositories.OrderTradingRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrderService {

    @Autowired
    OrderTradingRepository orderRepository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


//    @KafkaListener(topics = "tp1", groupId = "C")
    public void listenGroupOrderCa_Nhan(String message) {
        OrderSign os = new OrderSign(Sign.C);
        System.out.println("=====Received Group " + os.getDisplaySign() + ": " + message +" =======");
    }

    public void insertOrderByStockId(String stockId){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        OrderInsertService orderBuy  = new OrderInsertService("OrderBuy", stockId, orderRepository);
        OrderInsertService orderSell  = new OrderInsertService("OrderSell", stockId, orderRepository);
        System.out.println("-------------Start run Buy at " + dtf.format(LocalDateTime.now()));
        orderBuy.init();
        System.out.println("-------------Start run Sell at " + dtf.format(LocalDateTime.now()));
        orderSell.init();
        System.out.println("======= End Service at " + dtf.format(LocalDateTime.now()));
        orderBuy.checkStatus();
        orderSell.checkStatus();
    }

}
