package thread.executor.test;

import java.util.concurrent.ExecutionException;

public class NewOrderServiceTestMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String orderNo = "order#1234";
//        NewOrderService newOrderService = new NewOrderService();
        NewOrderService2 newOrderService = new NewOrderService2();
        newOrderService.order(orderNo);
    }
}
