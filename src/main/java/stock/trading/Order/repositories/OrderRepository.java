package stock.trading.Order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stock.trading.Order.entity.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByStockId(String stockId);

    List<Order> findByStockIdAndSide(String stockId, short side);

    List<Order> findByStatus(String orderStatus);

}