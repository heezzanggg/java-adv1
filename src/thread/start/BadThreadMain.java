package thread.start;

public class BadThreadMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");
        HelloThread helloThread = new HelloThread();
        System.out.println(Thread.currentThread().getName() + ": run() 호출 전");
        helloThread.run(); //start()가 아니라 run() 직접실행 => 별도의 스레드가 run() 실행 x , main스레드가 run() 메서드 호출함
        System.out.println(Thread.currentThread().getName() + ": run() 호출 후");
        System.out.println(Thread.currentThread().getName() + ": main() end");
    }
}
