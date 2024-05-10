package stock.trading.order.thread;

import java.time.Instant;
import java.time.temporal.ChronoField;

public class TestTime {
    public static void main(String[] args) {
        // create a Instant object
        Instant instant = Instant.now();
        System.out.println("ins: " + instant.toEpochMilli());
        // get all enum of chronofield
        // and iterate through all enum values
        for (ChronoField field : ChronoField.values()) {

            try {
                // get long value of field
                long value = instant.getLong(field);
                System.out.println("field : " + field
                        + " || value : " + value);
            }
            catch (Exception e) {

                System.out.println("field : " + field
                        + " is not supported");
            }
        }
    }
}
