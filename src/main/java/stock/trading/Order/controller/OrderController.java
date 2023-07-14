package stock.trading.Order.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import stock.trading.Order.entity.Order;
import stock.trading.Order.repositories.OrderRepository;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    public OrderController() {
    }

    @PostMapping("/order")
    Order newOrder(@RequestBody Order order) {
        //TODO : get User type
        kafkaTemplate.send("tp1", order);
        order.setOrderTime(Timestamp.valueOf(LocalDateTime.now()));
        return orderRepository.save(order);
    }

    @GetMapping("/order/{stockId}")
    Order getOrdersByStock(@PathVariable String stockId) {
        return orderRepository.findByStockId(stockId).get(0);
    }


    @GetMapping("/order/consumer/{orderStatus}")
    List<Order> getOrderByStatus(@PathVariable String orderStatus) {
        return orderRepository.findByStatus(orderStatus);
    }





}
