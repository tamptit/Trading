package app.trading.investors.service;

import app.trading.investors.entity.CashAccount;
import app.trading.investors.entity.TradingAccount;

import java.util.Optional;

public interface AccountService {

//    void updateInformationAccount(TradingAccount account);
    int updateAccountBalance(TradingAccount account, CashAccount.TypeChange type, double numberOfChange);

    Optional<TradingAccount> getAccountById(Integer id);

}
