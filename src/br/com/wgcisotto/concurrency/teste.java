package br.com.wgcisotto.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class teste {
    public static void main(String[] args){

        System.out.println("Executar Job");

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(()-> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(10);

                System.out.println("Depois de acordar!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        shutDown(executor);

    }

    private static void shutDown(ExecutorService executor) {
        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        }
        finally {
            if (executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
                executor.shutdownNow();
                System.out.println("shutdown finished");
            }else{
                shutDown(executor);
            }
        }
    }

}
