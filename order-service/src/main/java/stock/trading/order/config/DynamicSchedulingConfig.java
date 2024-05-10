package stock.trading.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import stock.trading.order.context.TriggerForTest;
import stock.trading.order.thread.ThreadSchedule;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

//@Configuration
//@EnableScheduling
public class DynamicSchedulingConfig implements SchedulingConfigurer {

    static long GetDelay = 1000;

    @Bean
    public Executor taskExecutor() {
        return Executors.newSingleThreadScheduledExecutor();
    }


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
        taskRegistrar.addTriggerTask(
                (Runnable) new ThreadSchedule(2023),
//                context -> {
//                    Optional<Instant> lastCompletionTime =
//                            Optional.ofNullable(context.lastCompletion());
//                    Instant nextExecutionTime =
//                            lastCompletionTime.orElseGet(Instant::now).plusMillis(GetDelay);
//                    return Instant.from(nextExecutionTime);
//                }
                new TriggerForTest()
        );
    }



}