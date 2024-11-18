package thread.start.test2;

import org.w3c.dom.css.Counter;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class StartTest2Main {

    public static void main(String[] args) {
        CounterRunnable counterRunnable = new CounterRunnable();
        Thread thread = new Thread(counterRunnable, "counter");
        thread.start();
    }

    private static class CounterRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                log("value:" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
