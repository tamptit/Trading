package stock.trading.order.context;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;

import java.time.Instant;


public class TriggerForTest implements Trigger {

    @Override
    public Instant nextExecution(TriggerContext triggerContext) {
        //TO-DO : get time from rule table config
        return Instant.now().plusMillis(3000);
    }
}
