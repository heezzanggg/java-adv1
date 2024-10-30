package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV3 {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(100);
        thread.interrupt();
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
    }

    private static class MyTask implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {// 인터럽트 상태 변경x
                log("작업중");
            }
            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());

            try {
                log("자원 정리 시도");
                log("sleep 이전 인터럽트  = " + Thread.currentThread().isInterrupted());
                Thread.sleep(1000);
                log("sleep 이후 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());
                log("자원 정리 종료");
            } catch (InterruptedException e) {
                /*
                인터럽트 상태에서 인터럽트 예외 발생하면 스레드는 다시 작동하는 상태가 됨
                 */
                log("자원 정리 실패 - 자원 정리 중 인터럽트 발생");
                log("work 스레드 인터럽트 상태3 = " + Thread.currentThread().isInterrupted());
            }
            log("작업 종료");
        }
    }
}
