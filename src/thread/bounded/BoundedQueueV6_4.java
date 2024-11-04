package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static util.MyLogger.log;

public class BoundedQueueV6_4 implements BoundedQueue {

    private BlockingQueue<String> queue;

    public BoundedQueueV6_4(int max) {
        queue = new ArrayBlockingQueue<>(max);
    }


    @Override
    public void put(String data) {
        queue.add(data); //성공하면 true, 버퍼 가득차면 예외 발생 java.lang.IllegalStateException: Queue full
    }

    @Override
    public String take() {
        return queue.remove(); //버퍼에 데이터 없으면 예외 발생 java.util.NoSuchElementException
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
