package stock.trading.Order.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import stock.trading.Order.entity.Order;
import stock.trading.Order.repositories.OrderRepository;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    public OrderController() {
    }

    @PostMapping("/order")
    Order newEmployee(@RequestBody Order order) {
        kafkaTemplate.send("", order);
        return orderRepository.save(order);
    }

    @GetMapping("/order/{stockId}")
    Order getOrdersByStock(@PathVariable String stockId) {
        return orderRepository.findByStockId(stockId).get(0);
    }




}
