package thread.bounded;

import java.util.ArrayList;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedMain {

    public static void main(String[] args) {
        //1.BoundedQueue 선택
        //버퍼 크기 = 2, 생산자가 2개를 넘어서 데이터를 추가로 저장하려고하면 문제 생김
        //버퍼에 데이터가 없는데도, 소비자가 데이터를 가져갈 때도 문제 생김
//        BoundedQueue queue = new BoundedQueueV1(2);
//        BoundedQueue queue = new BoundedQueueV2(2);
        BoundedQueue queue = new BoundedQueueV3(2);

        //2. 생산자, 소비자 실행 순서 선택, 반드시 하나만 선택!
//        producerFirst(queue); //생산자 먼저 실행
        consumerFirst(queue); //소비자 먼저 실행
    }

    private static void producerFirst(BoundedQueue queue) {
        log("== [생산자 먼저 실행] 시작, " + queue.getClass().getSimpleName() + " ==");
        ArrayList<Thread> threads = new ArrayList<>(); //스레드의 결과 상태를 한꺼번에 출력하기 위해 생성한 스레드를 보관해둠
        startProducer(queue, threads);
        printAllState(queue, threads);
        startConsumer(queue, threads);
        printAllState(queue, threads);
        log("== [생산자 먼저 실행] 종료, " + queue.getClass().getSimpleName() + " ==");
    }

    private static void consumerFirst(BoundedQueue queue) {
        log("== [소비자 먼저 실행] 시작, " + queue.getClass().getSimpleName() + " ==");
        ArrayList<Thread> threads = new ArrayList<>();
        startConsumer(queue, threads);
        printAllState(queue, threads);
        startProducer(queue, threads);
        printAllState(queue, threads);
        log("== [소비자 먼저 실행] 종료, " + queue.getClass().getSimpleName() + " ==");
    }

    private static void startProducer(BoundedQueue queue, ArrayList<Thread> threads) {
        System.out.println();
        log("생산자 시작");
        for (int i = 1; i <= 3; i++) {
            Thread producer = new Thread(new ProducerTask(queue, "data" + i), "producer" + i);
            threads.add(producer);
            producer.start();
            sleep(100);
        }
    }

    private static void startConsumer(BoundedQueue queue, ArrayList<Thread> threads) {
        System.out.println();
        log("소비자 시작");
        for (int i = 1; i <= 3; i++) {
            Thread consumer = new Thread(new ConsumerTask(queue), "consumer" + i);
            threads.add(consumer);
            consumer.start();
            sleep(100);
        }
    }

    private static void printAllState(BoundedQueue queue, ArrayList<Thread> threads) {
        System.out.println();
        log("현재 상태 출력, 큐 데이터: " + queue);
        for (Thread thread : threads) {
            log(thread.getName() + ": " + thread.getState());
        }
    }


}
