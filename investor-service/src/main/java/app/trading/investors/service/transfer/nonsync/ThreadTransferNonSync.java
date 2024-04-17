package app.trading.investors.service.transfer.nonsync;

import app.trading.investors.entity.TradingAccount;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadTransferNonSync extends Thread implements Runnable {

    private TradingAccount account;
    private boolean isSync = true;

    public ThreadTransferNonSync(TradingAccount p, boolean isSync) {
        this.account = p;
        this.isSync = isSync;
    }


    @Override
    public void run() {
        TransferNotSynchronized acc = new TransferNotSynchronized(account.getAccountId());
        for (int i = 0; i < 10; i++) {
            try {
                acc.withdraw(100);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadTransferNonSync.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (acc.getBal() < 0) {
                    System.out.println("account is overdrawn!");
                }
                acc.deposit(200);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Final Acc balance is Rs." + TransferNotSynchronized.getBal());
    }
}
