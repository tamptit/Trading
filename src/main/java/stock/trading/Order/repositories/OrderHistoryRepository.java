package stock.trading.Order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stock.trading.Order.entity.OrderHistory;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Integer> {
}