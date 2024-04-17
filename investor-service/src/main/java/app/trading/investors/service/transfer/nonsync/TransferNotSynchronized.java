package app.trading.investors.service.transfer.nonsync;

import app.trading.investors.service.transfer.MoneyTransfer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
@AllArgsConstructor
public class TransferNotSynchronized {
    private static int balance = 1000;
    private int accountId;
    private static final Logger logger = Logger.getLogger(TransferNotSynchronized.class.getName());

    private TransferNotSynchronized() {
    }

    public static int getBal() {
        return balance;
    }

    public void withdraw(int bal) {
        try {
            if (balance >= bal) {
                balance = balance - bal;
            } else {
                System.out.println(this.accountId + " " + "doesn't have enough money for withdraw ");
            }
//            System.out.println(this.accountId + " " + " withdraw Rs." + balance);
            logger.log(Level.INFO, this.accountId + " " + " withdraw = " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deposit(int bal) {
        try {
            if (bal > 0) {
                balance = balance + bal;
            } else {
                System.out.println(this.accountId + " " + "doesn't have enough money for deposit");
            }
//            System.out.println(this.accountId + " " + " deposit Rs." + balance);
            logger.log(Level.INFO, this.accountId + " " + " deposit = " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
