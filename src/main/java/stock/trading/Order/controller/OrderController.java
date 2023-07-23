package stock.trading.Order.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import stock.trading.Order.entity.Order;
import stock.trading.Order.repositories.OrderRepository;
import stock.trading.Order.service.OrderService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class OrderController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Value( "${my_name}" )
    private String my_name;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;

//    @Autowired
//    KafkaTemplate<String, Object> kafkaTemplate;

    public OrderController() {
    }

    @PostMapping("/order")
    Order newOrder(@RequestBody Order order) {
        //TODO : get User type
        order.setOrderTime(Timestamp.valueOf(LocalDateTime.now()));
        return orderRepository.save(order);
    }

    @GetMapping("/order/send")
    List<Order> sendOrderByKafka() {
        List<Order> orders = orderRepository.findByStatus("WAIT");
//        kafkaTemplate.send("tp1", orders);
        return orders;
    }
    @GetMapping("/properties")
    String getProperties() {
        return my_name;
    }

    @GetMapping("/order/insert/{stockId}")
    String insertOrderByStockId(@PathVariable String stockId) {
        Optional<List<Order>> orders = Optional.ofNullable(orderRepository.findByStockId(stockId));
        if(orders.isPresent()){
            orderService.insertOrderByStockId(stockId);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        System.out.println("--------------Thread Controller at " + dtf.format(LocalDateTime.now()));
        return stockId;
    }

    @GetMapping("/order/thread")
    String getAllThread() {
        Set<Thread> threads = Thread.getAllStackTraces().keySet();
        System.out.printf("%-15s \t %-15s \t %-15s \t %s\n", "Name", "State", "Priority", "isDaemon");
        StringBuilder threadNames = new StringBuilder();
        for (Thread t : threads) {
            System.out.printf("%-15s \t %-15s \t %-15d \t %s\n", t.getName(), t.getState(), t.getPriority(), t.isDaemon());
            threadNames.append(t.getName());
        }
        return threadNames.toString();
    }

    @GetMapping("/order/{stockId}")
    String getOrderByStockAndSide(@PathVariable String stockId) {
        int countBuy = orderRepository.findByStockIdAndSide(stockId, Short.parseShort("0")).size();
        int countSell = orderRepository.findByStockIdAndSide(stockId, Short.parseShort("1")).size();
        log.info("Number Buy Order:" + countBuy);
        log.info("Number Sell Order:" + countSell);
        return stockId + "( Buy= " + countBuy + ", Sell= " + countSell + ")";
    }

    @GetMapping("/order/consumer/{orderStatus}")
    List<Order> getOrderByStatus(@PathVariable String orderStatus) {
        return orderRepository.findByStatus(orderStatus);
    }


}
