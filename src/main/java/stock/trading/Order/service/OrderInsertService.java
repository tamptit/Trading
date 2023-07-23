package stock.trading.Order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import stock.trading.Order.entity.Order;
import stock.trading.Order.repositories.OrderRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    OrderRepository orderRepository;

    public OrderInsertService() {
    }

    public OrderInsertService(String threadName, String stockId, OrderRepository orderRepository) {
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
        log.error("Start save for side " + side + " at "+ dtf.format(LocalDateTime.now()));
        List<Order> orderList = new ArrayList<Order>();
        for (int i = 1; i <= 30; i++) {
            Order order = new Order();
            order.setAccountId("10" + i%5);
            order.setSide(side);
            order.setAmount(i*10);
            order.setStockId(stockId);
            order.setOrderPrice("20" + String.valueOf((i%5)*100));
            order.setType("LO"); order.setStatus("WAIT"); order.setOrderSign("CN");
            order.setOrderTime(Timestamp.valueOf(LocalDateTime.now()));
            orderList.add(order);
            if (i % 5 == 0){
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
