package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;

//한정된 버퍼 역할을 하는 가장 단순한 구현체
public class BoundedQueueV1 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>(); //여러 스레드가 접근하므로 핵심 공유 자원임!!!
    private final int max; //버퍼에 저장할 수 있는 최대 크기

    public BoundedQueueV1(int max) {
        this.max = max;
    }

    /*
    queue(ArrayDeque) 핵심 공유 자원임.
    여러 스레드가 접근할 예정이므로 synchronized를 사용해서
    한번에 하나의 스레드만 put() or take() 실행할 수 있도록 안전한 임계영역 만들어야함
     */
    @Override
    public synchronized void put(String data) {
        if (queue.size() == max) {
            log("[put] 큐가 가득 참, 버림: " + data);
            return;
        }
        queue.offer(data);
    }

    @Override
    public synchronized String take() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
