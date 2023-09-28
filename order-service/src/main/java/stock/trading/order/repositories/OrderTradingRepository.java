package stock.trading.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stock.trading.order.entity.OrderTrading;

import java.util.List;

@Repository
public interface OrderTradingRepository extends JpaRepository<OrderTrading, Integer> {

    List<OrderTrading> findByStockId(String stockId);

    List<OrderTrading> findByStockIdAndSide(String stockId, short side);

    List<OrderTrading> findByStatus(String orderStatus);

}