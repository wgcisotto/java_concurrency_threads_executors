package br.com.wgcisotto.concurrency;

public class Main {

    public static void main (String[] args){

        System.out.println("Preparar Job");

        JobInterface job = () -> {
            System.out.println("Aqui eu defino que deve ser Executado");
        };

        ThreadExecutor.execute(job);

    }

}
