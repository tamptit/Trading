package app.trading.users.controller;


import app.trading.users.entity.CashAccount;
import app.trading.users.entity.TradingAccount;
import app.trading.users.service.transfer.MultiThreadTestTransfer;
import app.trading.users.service.transfer.nonsync.ThreadTransferNonSync;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import app.trading.users.service.AccountService;

import java.util.Optional;

@RestController
@RequestMapping("/api/public")
public class TradingAccountController {

    private final AccountService accountService;

    public TradingAccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/test")
    String testRunSuccess(){
        return "OK";
    }

    @PostMapping("/accounts/{accountId}")
    String updateAccountBalance(@PathVariable Integer accountId, @RequestBody CashAccount cashAccount){
        Optional<TradingAccount> account = accountService.getAccountById(accountId);
        Optional<TradingAccount> account1 = accountService.getAccountById(accountId+1);
        Optional<TradingAccount> account2 = accountService.getAccountById(accountId+2);
        if (!account.isPresent()){
            return "not null";
        }
        TradingAccount p = account.get();
        TradingAccount p1 = account1.get();
        TradingAccount p2 = account2.get();
        MultiThreadTestTransfer ts1 = new MultiThreadTestTransfer(p);
        MultiThreadTestTransfer ts2 = new MultiThreadTestTransfer(p1);
        MultiThreadTestTransfer ts3 = new MultiThreadTestTransfer(p2);
        ts1.setName("ts1");
        ts2.setName("ts2");
        ts3.setName("ts3");
        ts1.start();
        ts2.start();
//        ts3.start();
        return "SUCCESS";
    }

    @PostMapping("/accounts-non/{accountId}")
    String updateAccountBalanceNotSync(@PathVariable Integer accountId, @RequestBody CashAccount cashAccount){
        Optional<TradingAccount> account = accountService.getAccountById(accountId);
        Optional<TradingAccount> account1 = accountService.getAccountById(accountId+1);
        Optional<TradingAccount> account2 = accountService.getAccountById(accountId+2);
        if (!account.isPresent()){
            return "not null";
        }
        TradingAccount p = account.get();
        TradingAccount p1 = account1.get();
        TradingAccount p2 = account2.get();
        ThreadTransferNonSync ts = new ThreadTransferNonSync(p, false);
        ThreadTransferNonSync ts2 = new ThreadTransferNonSync(p1, false);
        ThreadTransferNonSync ts3 = new ThreadTransferNonSync(p2, false);
        ts.start();
//        ts2.start();
//        ts3.start();
        return "SUCCESS";
    }

    @GetMapping("/accounts/{accountId}")
    TradingAccount getAccountInformation(@PathVariable Integer accountId){
        Optional<TradingAccount> account = accountService.getAccountById(accountId);
        if (!account.isPresent()){
            return null;
        }
        return account.get();
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public TradingAccount register(@RequestBody TradingAccount account) {
        return accountService.register(account);
    }

    @GetMapping
    public Page<TradingAccount> list(@RequestParam(required = false) String query, Pageable pageable) {
        return accountService.findAll(query, pageable);
    }

    @DeleteMapping("/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer accountId) {
        accountService.softDelete(accountId);
    }


}
