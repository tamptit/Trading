package stock.trading.order.thread;



public class ThreadSchedule implements Runnable{

    Integer numT;

    public ThreadSchedule(Integer numT) {
        this.numT = numT;
    }

    @Override
    public void run() {
        System.out.println("num = " + this.numT);
    }
}
