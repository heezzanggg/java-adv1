package thread.start;

public class HelloThread extends Thread {
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": run()");
    }
}
