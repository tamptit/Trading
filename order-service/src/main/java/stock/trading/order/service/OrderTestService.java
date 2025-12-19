package stock.trading.order.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import stock.trading.order.entity.OrderTrading;
import stock.trading.order.model.OrderResult;
import stock.trading.order.repositories.OrderTradingRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class OrderTestService  {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private OrderService orderService;
    private OrderTradingRepository orderRepository;
    private OrderTrading order;

    AtomicInteger atomicInteger = new AtomicInteger(1);
    ExecutorService executor = Executors.newFixedThreadPool(10);
//    public OrderTestService() {
//    }
    public OrderTestService( OrderTrading order, OrderService orderService, OrderTradingRepository orderRepository) {
        this.order = order;
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    Callable<OrderResult> callableTask = () -> {
        OrderResult rs;
        int ato = atomicInteger.getAndIncrement();
        Instant instant = Instant.now();
        order.setOrderTime(Timestamp.from(instant));
//        ZonedDateTime vnTime = instant.atZone(ZoneId.of("Asia/Ho_Chi_Minh"));

        log.info("timeout: " + instant + " - " + ato);
        OrderTrading rsUpdate = orderService.createOrUpdateOrder(order, ato);
        // assume get kq from queue
        TimeUnit.MILLISECONDS.sleep(200);

        if (rsUpdate.getStatus().equals("ERROR")){
            rs = new OrderResult("-1", -99, "QUA SL");
            return rs;
        }
        rs = new OrderResult(rsUpdate.getChannel(), rsUpdate.getAmount(), "da tru tien");
        return rs;
    };

    Callable<Integer> callableGetAto = () -> {
//        TimeUnit.MILLISECONDS.sleep(100);
        return atomicInteger.getAndIncrement();
    };

    public List<Future<OrderResult>> startTest() throws ExecutionException, InterruptedException {
        List<Callable<OrderResult>> callableTasks = new ArrayList<>();
        List<Future<OrderResult>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
//            Future<OrderResult> future = executor.submit(callableTask);
//            callableTasks.add(callableTask);
            futures.add(executor.submit(callableTask));

        }
//        List<Future<OrderResult>> futures = executor.invokeAll(callableTasks);
        TimeUnit.MILLISECONDS.sleep(500);
        atomicInteger.set(0);
        return futures;
    }

}
