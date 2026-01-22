package stock.trading.order.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import stock.trading.order.entity.OrderTrading;
import stock.trading.order.repositories.OrderTradingRepository;
import stock.trading.order.repositories.OutboxEventRepository;
import stock.trading.order.service.OrderService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderTradingRepository orderRepository;

    @Mock
    private OutboxEventRepository outboxRepository;

    @InjectMocks
    private OrderService orderService;

    private OrderTrading existingOrder;

    @BeforeEach
    void setUp() {
        existingOrder = new OrderTrading();
        existingOrder.setId(1);
        existingOrder.setAmount(100.0f);
        existingOrder.setStockId("FPT");
    }

    @Test
    void testCreateOrUpdateOrder_ExistingOrder() {
        // Arrange
        OrderTrading inputOrder = new OrderTrading();
        inputOrder.setId(1);
        inputOrder.setAmount(80.0f);

        OrderTrading existingOrder = new OrderTrading();
        existingOrder.setId(1);
        existingOrder.setAmount(100.0f);

        // Mock findById to return the existing order wrapped in Optional
        when(orderRepository.findById(1)).thenReturn(Optional.of(existingOrder));
        
        // Mock saveAndFlush to return the modified order
        when(orderRepository.saveAndFlush(any(OrderTrading.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        OrderTrading result = orderService.createOrUpdateOrder(inputOrder, 1);

        // Assert
        assertNotNull(result);
        assertEquals(20.0f, result.getAmount(), "Amount should be updated (100 - 80)");
        verify(orderRepository).findById(1);
        verify(orderRepository).saveAndFlush(any());
        verify(outboxRepository).save(any());
    }

    @Test
    void testCreateOrUpdateOrder_NewOrder() {
        // Arrange
        OrderTrading inputOrder = new OrderTrading();
        inputOrder.setId(99);
        inputOrder.setAmount(10.0f);

        // Mock findById to return empty for a new order
        when(orderRepository.findById(99)).thenReturn(Optional.empty());
        when(orderRepository.saveAndFlush(any(OrderTrading.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        OrderTrading result = orderService.createOrUpdateOrder(inputOrder, 1);

        // Assert
        assertNotNull(result);
        assertEquals(10.0f, result.getAmount());
        verify(orderRepository).findById(99);
    }
}
