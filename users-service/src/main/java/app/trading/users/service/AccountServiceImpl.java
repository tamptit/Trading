package app.trading.users.service;

import app.trading.users.entity.CashAccount;
import app.trading.users.entity.TradingAccount;
import app.trading.users.repository.TradingAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    final TradingAccountRepository tradingAccountRepository;

    public AccountServiceImpl( TradingAccountRepository tradingAccountRepository) {
        this.tradingAccountRepository = tradingAccountRepository;
    }

    public void updateInformationAccount(TradingAccount account) {

    }

    @Override
    public int updateAccountBalance(TradingAccount account, CashAccount.TypeChange typeChange, double numberOfChange) {
        CashAccount.TypeChange increment = CashAccount.TypeChange.INCREMENT;
        CashAccount.TypeChange decrease  = CashAccount.TypeChange.DECREASE;
        System.out.println(increment  + " & " + decrease);
        //TODO: get cash current from cache
        double cashAfterChange =  account.getCash() - numberOfChange;
        // check so du account
        if(typeChange.equals(decrease)){

        }
//        int update = tradingAccountRepository.updateCashValue(cashAfterChange,"ta",account.getAccountId());
        //TODO: update cache from database
        return 0;

    }

    public Optional<TradingAccount> getAccountById(Integer id){
        return tradingAccountRepository.findByAccountId(id);
    }


}
