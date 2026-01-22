package app.trading.users.service;

import app.trading.users.entity.CashAccount;
import app.trading.users.entity.TradingAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AccountService {

    int updateAccountBalance(TradingAccount account, CashAccount.TypeChange type, double numberOfChange);

    Optional<TradingAccount> getAccountById(Integer id);

    TradingAccount register(TradingAccount account);

    Page<TradingAccount> findAll(String query, Pageable pageable);

    Optional<TradingAccount> getAccountByIdAndStatus(Integer id);

    void softDelete(Integer id);

}
