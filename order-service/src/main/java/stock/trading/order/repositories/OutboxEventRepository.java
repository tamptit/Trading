package stock.trading.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import stock.trading.order.entity.OutboxEvent;
import java.util.UUID;

public interface OutboxEventRepository extends JpaRepository<OutboxEvent, UUID> {
}
