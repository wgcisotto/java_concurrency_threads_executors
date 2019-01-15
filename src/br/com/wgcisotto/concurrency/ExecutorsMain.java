package br.com.wgcisotto.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsMain {

    public static void main (String[] args){

        /*
        *
        * The Concurrency API introduces the concept of an ExecutorService as a higher level
        * replacement for working with threads directly.
        * Executors are capable of running asynchronous tasks and typically manage a pool of threads,
        * so we don't have to create new threads manually.
        * All threads of the internal pool will be reused under the hood for revenant tasks,
        * so we can run as many concurrent tasks as we want throughout the life-cycle of our application
        * with a single executor service.
        *
        * This is how the first thread-example looks like using executors:
        *
         */

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(()-> {
           String name = Thread.currentThread().getName();
            System.out.println("Hello: " + name);
        });

        /*
        * The class Executors provides convenient factory methods for creating different kinds of executor services.
        * In this sample we use an executor with a thread pool of size one.

        * The result looks similar to the above sample but when running the code you'll notice an important difference:
        * the java process never stops! Executors have to be stopped explicitly - otherwise they keep listening for new tasks.

        * An ExecutorService provides two methods for that purpose: shutdown()
        *  waits for currently running tasks to finish while shutdownNow() interrupts all running tasks and
        *  shut the executor down immediately.

        * This is the preferred way how I typically shutdown executors:
         */

        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }

        /*
        *The executor shuts down softly by waiting a certain amount of time for termination of currently running tasks.
        * After a maximum of five seconds the executor finally shuts down by interrupting all running tasks.
        *
         */


    }

}
