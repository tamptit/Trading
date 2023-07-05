package stock.trading.Order.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "order_trading")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="amount")
    private int amount;

    @Column(name="channel")
    private String channel;

    @Column(name="order_price")
    private BigDecimal orderPrice;

    @Column(name="order_time")
    private Timestamp orderTime;

    @Column(name="side")
    private short side;

    @Column(name="status")
    private String status;

    @Column(name="stock_id")
    private String stockId;

    @Column(name="trigger_conditions")
    private String triggerConditions;

    @Column(name="type")
    private String type;

    public Order() {
    }
    public Order(String stockId, int id) {
        this.stockId = stockId;
        this.id = id;
    }

    public Order(String stockId) {
        this.stockId = stockId;
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public BigDecimal getOrderPrice() {
        return this.orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Timestamp getOrderTime() {
        return this.orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public short getSide() {
        return this.side;
    }

    public void setSide(short side) {
        this.side = side;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStockId() {
        return this.stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getTriggerConditions() {
        return this.triggerConditions;
    }

    public void setTriggerConditions(String triggerConditions) {
        this.triggerConditions = triggerConditions;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
