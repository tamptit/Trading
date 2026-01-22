package app.trading.users.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name="trading_account")
@PrimaryKeyJoinColumn(name = "p_id")
public class TradingAccount extends Citizen implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public TradingAccount() {
    }

    public TradingAccount(int accountId) {
        this.accountId = accountId;
    }

    @Column(name = "account_id", nullable = false)
    private int accountId;

    @Column(name="account_name")
    private String accountName;

    @Column(name="account_type")
    private String accountType;

    @Column(name="cash")
    private double cash;

    @Column(name="frozen_cash")
    private double frozenCash;

    @Column(name="stock_value")
    private String stockValue;

    @Column(name="margin_rate")
    private String marginRate;

    @Column(name="recommend_id")
    private String recommendId;

    @Column(name = "status")
    private String status = "ACTIVE"; // ACTIVE, INACTIVE (Soft delete)
}
