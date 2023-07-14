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

    @Column(name="side")
    private short side;

    @Column(name="stock_id")
    private String stockId;

    @Column(name="amount")
    private int amount;

    @Column(name="order_price")
    private String orderPrice;

    @Column(name="account_id")
    private String accountId;

    @Column(name="type")
    private String type;

    @Column(name="order_sign")
    private String orderSign; // ki hieu lenh quy dinh HOSE

    @Column(name="channel")
    private String channel;

    @Column(name="order_time")
    private Timestamp orderTime;

    @Column(name="status")
    private String status;

    @Column(name="trigger_conditions")
    private String triggerConditions;

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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderSign() {
        return orderSign;
    }

    public void setOrderSign(String orderSign) {
        this.orderSign = orderSign;
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
