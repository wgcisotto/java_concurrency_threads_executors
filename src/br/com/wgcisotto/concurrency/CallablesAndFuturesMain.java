package br.com.wgcisotto.concurrency;

import java.util.concurrent.*;

public class CallablesAndFuturesMain {


    public static void main(String[] args) {
        /*
         * In addition to Runnable executors support another kind of task named Callable. Callables are functional interfaces
         * just like runnables but instead of being void they return a value.
         *
         * This lambda expression defines a callable returning an integer after sleeping for one second:
         */

        Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return 123;
            } catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        /*
         * Callables can be submitted to executor services just like runnables.
         * But what about the callables result? Since submit() doesn't wait until the task completes,
         * the executor service cannot return the result of the callable directly.
         * Instead the executor returns a special result of type Future which
         * can be used to retrieve the actual result at a later point in time.
         */
        try{
            ExecutorService executor = Executors.newFixedThreadPool(1);

            Future<Integer> future = executor.submit(task);

            System.out.println("future done? "+future.isDone());

            executor.shutdownNow();

            Integer result = future.get();

            System.out.println("future done? "+future.isDone());

            System.out.print("result: "+result);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
