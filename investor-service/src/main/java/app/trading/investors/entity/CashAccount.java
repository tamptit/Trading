package app.trading.investors.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class CashAccount {
    private double cashCurrent;
    private double numberChange;
    public  enum TypeChange {
        INCREMENT, DECREASE
    }
}
