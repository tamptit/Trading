package app.trading.stock.price;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stock_price_history")
public class StockPriceHistory {

    @Id
    private String ticket_id;
    private String price_open;
    private String high_price;
    private String low_price;
    private String price_close;
    private String volume;
    private String price_change;
    private String time;
    private String beta;
    private String capital_market;
    private String average_price;
}