package app.trading.users.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.trading.users.entity.TradingAccount;
import app.trading.users.repository.TradingAccountRepository;
import app.trading.users.exception.InsufficientFundsException;

@Service
public class OrderEventListener {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private TradingAccountRepository accountRepository;
    
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "order-events", groupId = "users-service-group")
    @Transactional
    public void handleOrderCreatedEvent(String message) {
        try {
            JsonNode payload = objectMapper.readTree(message);
            int accountId = payload.get("accountId").asInt(); // Giả định payload có accountId
            double amountToLock = payload.get("amount").asDouble();

            TradingAccount account = accountRepository.findByAccountId(accountId)
                    .orElseThrow(() -> new RuntimeException("Account not found"));

            // Logic: Tạm khóa tiền
            if (account.getCash() < amountToLock) {
                log.error("Insufficient balance for account {}", accountId);
                throw new InsufficientFundsException("Insufficient funds for order execution");
            }

            account.setFrozenCash(account.getFrozenCash() + amountToLock);
            log.info("Locked {} for account {}", amountToLock, accountId);
            accountRepository.save(account);
        } catch (Exception e) {
            log.error("Error processing order event", e);
        }
    }
}
