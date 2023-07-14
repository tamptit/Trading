package stock.trading.Order.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import stock.trading.Order.model.OrderSign;
import stock.trading.Order.model.Sign;

@Service
public class OrderService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = "tp1", groupId = "C")
    public void listenGroupOrderCa_Nhan(String message) {
        OrderSign os = new OrderSign(Sign.C);
        System.out.println("Received in group " + os.getDisplaySign() + ": " + message);
    }

    @KafkaListener(topics = "tp1", groupId = "P")
    public void listenGroupOrderThanh_vien(String message) {
        OrderSign os = new OrderSign(Sign.P);
        System.out.println("Received in group " + os.getDisplaySign() + ": " + message);
    }


}
