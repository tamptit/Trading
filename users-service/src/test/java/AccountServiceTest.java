package app.trading.users.service;

import app.trading.users.entity.TradingAccount;
import app.trading.users.repository.TradingAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private TradingAccountRepository repository;

    @InjectMocks
    private AccountServiceImpl service;

    @Test
    void testRegister_Success() {
        TradingAccount account = new TradingAccount();
        account.setAccountName("Test User");
        
        when(repository.save(any(TradingAccount.class))).thenReturn(account);

        TradingAccount saved = service.register(account);
        
        assertEquals("ACTIVE", saved.getStatus());
        verify(repository, times(1)).save(account);
    }

    @Test
    void testFindAll_WithPagination() {
        PageRequest pageable = PageRequest.of(0, 10);
        TradingAccount acc = new TradingAccount();
        Page<TradingAccount> page = new PageImpl<>(List.of(acc));

        when(repository.searchActiveAccounts(eq("test"), any())).thenReturn(page);

        Page<TradingAccount> result = service.findAll("test", pageable);

        assertFalse(result.isEmpty());
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testSoftDelete_Success() {
        TradingAccount account = new TradingAccount();
        account.setAccountId(123);
        account.setStatus("ACTIVE");

        when(repository.findByAccountId(123)).thenReturn(Optional.of(account));

        service.softDelete(123);

        assertEquals("INACTIVE", account.getStatus());
        verify(repository, times(1)).save(account);
    }
}
