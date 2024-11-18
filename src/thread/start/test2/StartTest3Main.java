package thread.start.test2;

import static util.MyLogger.log;

//익명클래스
public class StartTest3Main {

    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
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
        };

        Thread thread = new Thread(runnable,"counter");

        thread.start();
    }
}
