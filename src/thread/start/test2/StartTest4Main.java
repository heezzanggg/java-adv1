package thread.start.test2;

import static util.MyLogger.log;

public class StartTest4Main {
    //Thread=A 1초에 한번씩, Thread-B 0.5초에 한번씩
    public static void main(String[] args) {
        PrintWork printWorkA = new PrintWork(1000, "A");
        PrintWork printWorkB = new PrintWork(500, "B");

        Thread threadA = new Thread(printWorkA, "Thread-A");
        Thread threadB = new Thread(printWorkB, "Thread-B");

        threadA.start();
        threadB.start();

    }

    private static class PrintWork implements Runnable {

        //time
        private int sleepMs;
        private String content;

        public PrintWork(int sleepMs, String content) {
            this.sleepMs = sleepMs;
            this.content = content;
        }

        //문자
        @Override
        public void run() {

            while (true) {
                log(content);
                try {
                    Thread.sleep(sleepMs);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
