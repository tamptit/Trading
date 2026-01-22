package stock.trading.order.service;


import jakarta.persistence.LockModeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stock.trading.order.entity.OrderTrading;
import stock.trading.order.entity.OutboxEvent;
import stock.trading.order.model.OrderSign;
import stock.trading.order.model.Sign;
import stock.trading.order.repositories.OrderTradingRepository;
import stock.trading.order.repositories.OutboxEventRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderTradingRepository orderRepository;

    @Autowired
    OutboxEventRepository outboxRepository;

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
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional
    public OrderTrading createOrUpdateOrder(OrderTrading orderTrading, int channel){
        Optional<OrderTrading> existOrderTradingOpt = orderRepository.findById(orderTrading.getId());
        if (existOrderTradingOpt.isPresent()) {
            OrderTrading existOrderTrading = existOrderTradingOpt.get();
            float amCurrent = existOrderTrading.getAmount();
            // change amount
            if (amCurrent - orderTrading.getAmount() > 0){
                orderTrading.setChannel(String.valueOf(channel));
                float amAfterUpdate = amCurrent - orderTrading.getAmount();
                orderTrading.setAmount(amAfterUpdate);
                log.info("channel= " + channel + ", amAfterUpdate = " + amAfterUpdate);
            }else {
                log.info("---amCurrent= " + amCurrent + ", channel = " + channel);
                OrderTrading rsError = new OrderTrading();
                rsError.setStatus("ERROR");
                return rsError;
            }
        }
        // Critical Sections

        OrderTrading savedOrder = orderRepository.saveAndFlush(orderTrading);

        // Transactional Outbox: Save event to the same DB in the same transaction
        String payload = String.format("{\"orderId\":\"%s\", \"accountId\":\"%s\", \"amount\":%s, \"createdAt\":\"%s\"}",
                savedOrder.getId(), savedOrder.getAccountId(), savedOrder.getAmount(), Instant.now());
        
        OutboxEvent event = new OutboxEvent(
                "Order",
                String.valueOf(savedOrder.getId()),
                "OrderCreatedEvent",
                payload
        );
        outboxRepository.save(event);

        return savedOrder;
    }

    public Float getOrderAmountById(int id){
        return orderRepository.getReferenceById(id).getAmount();
    }

}
