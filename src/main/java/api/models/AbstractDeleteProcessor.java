package api.models;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public abstract class AbstractDeleteProcessor {

    abstract public void deleteEntity(Object... requiredParameters);

    public void markForDelete(Object... requiredParameters) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ExecutorService threadPool = Executors.newFixedThreadPool(1);
            threadPool.submit(() -> deleteEntity(requiredParameters));
            awaitTerminationAfterShutdown(threadPool);
        }));
    }

    public void awaitTerminationAfterShutdown(ExecutorService threadPool) {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(1, TimeUnit.HOURS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
