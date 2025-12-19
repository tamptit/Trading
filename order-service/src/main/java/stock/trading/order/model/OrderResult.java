package stock.trading.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderResult {
    private String channel;
    private float amount;
    private String log;

}
