package stock.trading.Order.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import stock.trading.Order.entity.Order;
import stock.trading.Order.repositories.OrderRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class OrderController {

    @Value( "${my_name}" )
    private String my_name;

    @Autowired
    OrderRepository orderRepository;
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

    @GetMapping("/order/{stockId}")
    Order getOrdersByStock(@PathVariable String stockId) {
        return orderRepository.findByStockId(stockId).get(0);
    }


    @GetMapping("/order/consumer/{orderStatus}")
    List<Order> getOrderByStatus(@PathVariable String orderStatus) {
        return orderRepository.findByStatus(orderStatus);
    }





}
