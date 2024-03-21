package ch.zhaw.prog2.primechecker;

import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class PrimeCheckerFuture {

    private static final long LOWER_LIMIT = 10000L;
    private static final long UPPER_LIMIT = 1000000000L;
    private static final int NUM_PRIME = 500;

    public static void main(String[] args) throws ExecutionException {
        long starttime = System.currentTimeMillis();
        long duration;
        try {
            checkPrimes(NUM_PRIME);
        } catch (InterruptedException e) {
            System.out.println("Interrupted - " + e.getMessage());
        } finally {
            duration = System.currentTimeMillis() - starttime;
        }
        System.out.println("Finished in " + duration + " ms");
    }

    private static void checkPrimes(int numPrimes) throws InterruptedException, ExecutionException {
        // TODO: create ExecutorService
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        ArrayList<Future<PrimeTaskCallable.Result>> futures = new ArrayList<>();
        Future<PrimeTaskCallable.Result>[] numbers = new Future[numPrimes];
        // TODO: submit tasks to ExecutorService and collect the returned Futures in a List
        for (int i = 0; i < numPrimes; i++) {

                futures.add(executorService.submit(new PrimeTaskCallable(nextRandom())));
        }
        // TODO: Loop through List, wait for completion and print results
        for(Future<PrimeTaskCallable.Result> future: futures){
            PrimeTaskCallable.Result result = future.get();
            System.out.println("Number: " + result.candidate + "  -> " +
                ( result.isPrime ? "PRIME" : "Factor: "  + result.factor ));
        }

        // TODO: stop ExecutorService
        executorService.shutdown();
        // TODO: await termination with timeout 1 minute
        executorService.awaitTermination(1,TimeUnit.MINUTES);

    }

    private static long nextRandom() {
        return LOWER_LIMIT + (long)(Math.random() * (UPPER_LIMIT - LOWER_LIMIT));
    }
}
