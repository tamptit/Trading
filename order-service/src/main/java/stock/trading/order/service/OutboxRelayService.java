package stock.trading.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stock.trading.order.entity.OutboxEvent;
import stock.trading.order.repositories.OutboxEventRepository;

import java.util.List;

@Service
public class OutboxRelayService {

    @Autowired
    private OutboxEventRepository outboxRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Scheduled(fixedDelay = 1000)
    @Transactional
    public void relayEvents() {
        List<OutboxEvent> events = outboxRepository.findAll();
        for (OutboxEvent event : events) {
            // Gửi lên Kafka topic "order-events"
            kafkaTemplate.send("order-events", event.getAggregateId(), event.getPayload());
            // Xóa event sau khi gửi thành công để tránh gửi lặp
            outboxRepository.delete(event);
        }
    }
}
