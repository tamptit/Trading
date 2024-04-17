package stock.trading.order.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.*;

public class CallableExample {


    public static void main(String[] args) {
        // create a list to hold the Future object associated with Callable
        List<Future<Integer>> list = new ArrayList<>();

        // Get ExecutorService from Executors utility class, thread pool size is 5
        ExecutorService executor = Executors.newFixedThreadPool(5);

        Callable<Integer> callable;
        Future<Integer> future;
        for (int i = 1; i <= 10; i++) {
            callable = new ThreadReturnValue(i);

            // submit Callable tasks to be executed by thread pool
            future = executor.submit(callable);

            // add Future to the list, we can get return value using Future
            list.add(future);
        }

        // shut down the executor service now
        executor.shutdown();

        // Wait until all threads are finish
        while (!executor.isTerminated()) {
            // Running ...
        }

        int sum = 0;
        for (Future<Integer> f : list) {
            try {
                sum += f.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        ThreadTimer timer = new ThreadTimer();

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(0);
        scheduledExecutorService.schedule(timer, 1000, TimeUnit.MILLISECONDS);
        ScheduledFuture<String> scheduledFuture = (ScheduledFuture<String>) scheduledExecutorService.scheduleAtFixedRate(
                timer, System.currentTimeMillis(), 24*60*60, TimeUnit.MILLISECONDS);

        System.out.println("Sum all = " + sum);
        System.out.println("Finished all threads: ");
    }
}
