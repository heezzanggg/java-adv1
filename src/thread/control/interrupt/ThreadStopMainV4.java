package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV4 {
/*스레드의 인터럽트는 스레드에 중단 요청을 전달하는 방법입니다.
그러나 요청만으로는 스레드가 자동으로 종료되지 않으며,
스레드 내부에서 주기적으로 이 인터럽트 상태를 확인하고 적절히 처리하도록 해야 합니다.
 */
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(100);
        log("작업 중단 지시 - thread.interrupt()");
        /*
        work 스레드에 인터럽트 검 => work 스레드 인터럽트 상태(true), 
        이 상태만으로 work 스레드가 바로 종료 되는것이 아님. 스레드가 직접 인터럽트 상태를 확인하고 처리해야함
         */
        thread.interrupt();
        log("work 스레드 인터럽트 상태1= " + thread.isInterrupted());
    }

    private static class MyTask implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted()) { 
                /*
                Thread.interrupted() :현재 스레드의 인터럽트 상태를 확인하고, 그 값을 반환한 후에 상태를 다시 false 로 초기화함
                work 스레드가 이 조건문에서 true를 확인하게 되면 반복문 종료하고 정리 작업으로 넘어감
                 */
                log("작업중");
            }
            /*
            Thread.interrupted() vs Thread.currentThread().isInterrupted()
            -Thread.interrupted(): 메서드 호출될 때마다 현재 스레드의 인터럽트 상태를 확인하면서 동시에 인터럽트 상태를 false 초기화시킴
            -Thread.currentThread().isInterrupted(): 인터럽트 상태를 초기화하지 않고 인터럽트 상태 확인만 함
             */
            log("work 스레드 인터럽트 상태2= " + Thread.currentThread().isInterrupted());

            try {
                log("자원 정리 시도");
                Thread.sleep(1000);
                log("자원 정리 완료");
            } catch (InterruptedException e) {
                /*
                인터럽트 상태에서 인터럽트 예외 발생하면 스레드는 다시 작동하는 상태가 됨
                 */
                log("자원 정리 실패 - 자원 정리 중 인터럽트 발생");
                log("work 스레드 인터럽트 상태3= " + Thread.currentThread().isInterrupted());
            }

            log("작업 종료");
        }
    }
}
