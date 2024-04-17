package app.trading.stock.price;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StockPriceHistoryRepository extends JpaRepository<StockPriceHistory, String> {

}
