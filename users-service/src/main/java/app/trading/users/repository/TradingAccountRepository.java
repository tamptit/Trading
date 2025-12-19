package app.trading.users.repository;

import app.trading.users.entity.TradingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TradingAccountRepository extends JpaRepository<TradingAccount, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update trading_account set cash = ?, recommend_id = ? where account_id = ?", nativeQuery = true)
    int updateCashValue(double numberChange, String rcmId, int accountId );

    Optional<TradingAccount> findByAccountId(int accountId);
}
