package app.trading.users.service;

import app.trading.users.entity.CashAccount;
import app.trading.users.entity.TradingAccount;

import java.util.Optional;

public interface AccountService {

//    void updateInformationAccount(TradingAccount account);
    int updateAccountBalance(TradingAccount account, CashAccount.TypeChange type, double numberOfChange);

    Optional<TradingAccount> getAccountById(Integer id);

}
