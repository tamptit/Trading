package stock.trading.Order.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the order_history database table.
 * 
 */
@Entity
@Table(name="order_history")
public class OrderHistory implements Serializable {
	private static final long serialVersionUID = 2L;

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

	private int channel;

	@Column(name="match_price")
	private BigDecimal matchPrice;

	@Column(name="match_value")
	private BigDecimal matchValue;

	@Column(name="match_vol")
	private int matchVol;

	@Column(name="no_order")
	private String noOrder;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="trading_date")
	private Date tradingDate;

	public OrderHistory() {
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

	public int getChannel() {
		return this.channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public BigDecimal getMatchPrice() {
		return this.matchPrice;
	}

	public void setMatchPrice(BigDecimal matchPrice) {
		this.matchPrice = matchPrice;
	}

	public BigDecimal getMatchValue() {
		return this.matchValue;
	}

	public void setMatchValue(BigDecimal matchValue) {
		this.matchValue = matchValue;
	}

	public int getMatchVol() {
		return this.matchVol;
	}

	public void setMatchVol(int matchVol) {
		this.matchVol = matchVol;
	}

	public String getNoOrder() {
		return this.noOrder;
	}

	public void setNoOrder(String noOrder) {
		this.noOrder = noOrder;
	}

	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getOrderSign() {
		return orderSign;
	}

	public void setOrderSign(String orderSign) {
		this.orderSign = orderSign;
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

	public Date getTradingDate() {
		return this.tradingDate;
	}

	public void setTradingDate(Date tradingDate) {
		this.tradingDate = tradingDate;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}