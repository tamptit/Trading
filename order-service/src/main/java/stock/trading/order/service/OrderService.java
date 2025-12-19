package stock.trading.order.service;


import jakarta.persistence.LockModeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stock.trading.order.entity.OrderTrading;
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
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public OrderTrading createOrUpdateOrder(OrderTrading orderTrading, int channel){

        Float amCurrent = orderRepository.getReferenceById(orderTrading.getId()).getAmount();
        // Critical Sections
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
        return orderRepository.saveAndFlush(orderTrading);
    }

    public Float getOrderAmountById(int id){
        return orderRepository.getReferenceById(id).getAmount();
    }

}
