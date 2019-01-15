package br.com.wgcisotto.concurrency;

import java.util.concurrent.TimeUnit;

public class ThreadsMain {

    public static void main(String[] args) {

        /*
         * Java supports Threads since JDK 1.0.
         * Before starting a new thread you have to specify the code to be executed by this thread, often called the task.
         * This is done by implementing Runnable - a functional interface defining a single void no-args method run()
         * as demonstrated in the following example:
         *
         */

        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };

        task.run();

        Thread thread = new Thread(task);

        thread.start();

        System.out.println("Done !");

        /*
         *
         * Since Runnable is a functional interface
         * we can utilize Java 8 lambda expressions to print the current threads name to the console.
         * First we execute the runnable directly on the main thread before starting a new thread.
         *
         */

        Runnable runnable = () -> {
            try {
                String name = Thread.currentThread().getName();
                System.out.println("Foo " + name);
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Bar " + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread_01 = new Thread(runnable);

        thread_01.start();
    }

}


