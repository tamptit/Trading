package app.trading.users.service.transfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
public class MoneyTransfer {
    private static final Logger logger = Logger.getLogger(MoneyTransfer.class.getName());

    private static int balance = 1000;
    private int balancePerId;
    private Integer accountId;
//    private AtomicInteger sumWithDraw;
//    private AtomicInteger sumDeposit;
    public static MoneyTransfer INSTANCE;
    private static ConcurrentHashMap<Integer, MoneyTransfer> accounts = new ConcurrentHashMap<>();

    private MoneyTransfer() {
    }
    public MoneyTransfer(Integer accountId) {
        this.accountId = accountId;
        this.balancePerId = accountId;
        accounts.put(accountId, this);
    }

    public static MoneyTransfer getMoneyTransfer(int accountId) {
        if(INSTANCE == null ){// || !accounts.contains(accountId)
            INSTANCE = new MoneyTransfer(accountId);
            System.out.println("CREATE ACCOUNT = " + accountId);
        }
//        while(accounts.get(accountId) == null){
//            return accounts.get(accountId);
//        }
        return accounts.get(accountId);
    }

    public static int getBal() {
        return balance;
    }


    public synchronized void withdraw(int bal) {
        try {
            if (balance >= bal) {

                balance = balance - bal;
                balancePerId = balancePerId - bal;
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                sumWithDraw.getAndAdd(bal);
            } else {
                System.out.println(this.accountId + " " + "doesn't have enough money for withdraw ");
            }
            logger.log(Level.INFO, this.accountId + " " + " withdraw = " + balance);
//            System.out.println(this.accountId + " " + " withdraw = " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void deposit(int bal) {
        try {
            if (bal > 0) {
                balance = balance + bal;
                balancePerId = balancePerId + bal;
//                sumDeposit.getAndAdd(bal);
            } else {
                System.out.println(this.accountId + " " + "doesn't have enough money for deposit");
            }
//            System.out.println(this.accountId + " " + " deposit = " + balance);
            logger.log(Level.INFO, this.accountId + " " + " deposit = " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
