package app.trading.investors.service.transfer;

import app.trading.investors.entity.TradingAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
@NoArgsConstructor
public class MultiThreadTestTransfer extends Thread implements Runnable {

    private TradingAccount account;

    public MultiThreadTestTransfer(TradingAccount p) {
        this.account = p;
    }

//    public static void main(String[] args) {
//
//        MultiThreadTestTransfer ts1 = new MultiThreadTestTransfer(new TradingAccount(103));
//        ts1.start();
//        MultiThreadTestTransfer ts2 = new MultiThreadTestTransfer(new TradingAccount(104));
//        ts2.start();
//        MultiThreadTestTransfer ts3 = new MultiThreadTestTransfer(new TradingAccount(105));
//        ts3.start();
//
//    }

    @Override
    public void run() {
        System.out.println(this.getName() + " running with ID =" + account.getAccountId() );
        MoneyTransfer acc = MoneyTransfer.getMoneyTransfer(account.getAccountId());
        for (int i = 0; i < 5; i++) {
            try {
                assert acc != null;
                if (MoneyTransfer.getBal() < 0) {
                    System.out.println("account is overdrawn!");
                }
                acc.withdraw(100);
                acc.deposit(200);
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(MultiThreadTestTransfer.class.getName()).log(Level.SEVERE, null, ex);
//                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        System.out.println("AccID: " + acc.getAccountId() + "{WD= " + acc.getSumWithDraw() + "; DP= " + acc.getSumDeposit()
//                +"} => Final= " + MoneyTransfer.getBal());
        System.out.println("AccID: " + acc.getAccountId() +"= " + acc.getBalancePerId() +
                "=> Final= " + MoneyTransfer.getBal());
    }
}
