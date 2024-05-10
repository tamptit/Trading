package stock.trading.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import stock.trading.order.entity.OrderTrading;
import stock.trading.order.repositories.OrderTradingRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class OrderInsertService implements Runnable{

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    static AtomicInteger counter = new AtomicInteger(0);

    private Thread threadInsert;
    private String threadName;
    private String stockId;
    private short side;
    final int record = 100; // 1M
    final int batch = 10; // 10K

    @Autowired
    OrderTradingRepository orderRepository;

    public OrderInsertService() {
    }

    public OrderInsertService(String threadName, String stockId, OrderTradingRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.threadName = threadName;
        this.stockId = stockId;
        if (threadName.contains("Buy")){
            this.side = 0;
        }else{
            this.side = 1;
        }
    }

    @Override
    public void run() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        // Init list Order
        Instant instant = Instant.now();
        List<OrderTrading> orderList = new ArrayList<OrderTrading>();
        for (int i = 1; i <= record; i++) {
            OrderTrading order = new OrderTrading();
            int r = (int) ((Math.random() * (100 - 10)) + 10);
            order.setAccountId(String.valueOf(100+r));
            order.setSide(side);
            order.setAmount(r); // x10
            order.setStockId(stockId);
            order.setOrderPrice(String.valueOf(r)); // x 1000
            order.setType("LO"); order.setStatus("WAIT"); order.setOrderSign("CN");
            order.setOrderTime(Timestamp.from(instant));
            order.setOrderMicroTime(instant.get(ChronoField.MICRO_OF_SECOND));
            orderList.add(order);
            if (i % batch == 0){
                orderRepository.saveAllAndFlush(orderList);
                counter.incrementAndGet();
                orderList.clear();
                log.error("side " + side + ", counter= " + counter + " at "+ dtf.format(LocalDateTime.now()));
            }
        }
        // save list
        log.error("End save for side " + side  + " at "+dtf.format(LocalDateTime.now()));
        threadInsert.interrupt();
        log.info(threadName + " interrupted at " + dtf.format(LocalDateTime.now()));
    }

    public void init(){
        if(threadInsert == null){
            threadInsert = new Thread(this, threadName);
            threadInsert.start();
        }
    }

    public void checkStatus(){
        log.info("StatusThread: " + threadName + " live: " + threadInsert.isAlive());
    }

    public String getStockId() {
        return stockId;
    }
    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
}
