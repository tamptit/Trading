package stock.trading.order.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "order_trading")
public class OrderTrading implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="side")
    private short side;

    @Column(name="stock_id")
    private String stockId;

    @Column(name="amount")
    private float amount;

    @Column(name="order_price")
    private String orderPrice;

    @Column(name="account_id")
    @Size
    private String accountId;

    @Column(name="type")
    private String type;

    @Column(name="order_sign")
    private String orderSign; // ki hieu lenh quy dinh HOSE

    @Column(name="channel")
    private String channel;

    @Column(name="order_time")
    private Timestamp orderTime;

    @Column(name="order_micro_time")
    private int orderMicroTime;

    @Column(name="status")
    private String status;

    @Column(name="trigger_conditions")
    private String triggerConditions;

    public OrderTrading() {
    }
    public OrderTrading(String stockId, int id) {
        this.stockId = stockId;
        this.id = id;
    }

    public OrderTrading(String stockId) {
        this.stockId = stockId;
    }


}
