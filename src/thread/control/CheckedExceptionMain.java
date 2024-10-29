package thread.control;

public class CheckedExceptionMain {

    public static void main(String[] args) throws Exception {
        throw new Exception();
    }

    static class CheckedRunnable implements Runnable {
        @Override
        public void run() /*throws Exception*/ {
            //주석 풀면 예외 발생함
            //throw new Exception(); //주석 풀면 예외 발생함
        }

        /*
         Runnable 인터페이스의 run() 메서드 구현할 때 InterruptedException 체크 예외를 밖으로 던질 수 없는 이유?
         재정의 메서드가 지켜야할 예외와 관련된 규칙때문임
         -체크예외
         1.자식 클래스에 재정의된 메서드는 부모 메서드가 던질 수 있는 체크 예외의 하위 타입만을 던질 수 있다.
         2.원래 메서드가 체크 예외를 던지지 않는 경우, 재정의된 메서드도 체크 예외를 던질 수 없다.
         -언체크예외
         1.예외처리를 강제하지 않으므로 상관없이 던질 수 있다.
         */
    }
}
