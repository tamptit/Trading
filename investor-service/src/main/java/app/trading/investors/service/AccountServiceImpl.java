package app.trading.investors.service;

import app.trading.investors.entity.CashAccount;
import app.trading.investors.entity.TradingAccount;
import app.trading.investors.repository.TradingAccountRepository;
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
