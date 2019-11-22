package stopwatch;

import stopwatch.observers.StopwatchObserver;

import java.util.concurrent.*;

public class StopwatchRunner {
    ExecutorService service;

    public StopwatchRunner(ExecutorService service) {
        this.service = service;
    }

    public void startStopwatch(long timeout, TimeUnit unit) {
        StopwatchMaker stopwatchMaker = new StopwatchMaker(1);
        stopwatchMaker.addObserver(new StopwatchObserver());
        Future<?> stopwatchResult = service.submit(stopwatchMaker);
        try {
            stopwatchResult.get(timeout, unit);
        } catch (InterruptedException e) {
            System.out.println("(1)Current thread was interrupted/cancelled");
            stopwatchResult.cancel(true);
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.err.println("(2)Internal stopwatch exception: " + e.getMessage());
            stopwatchResult.cancel(true);
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("(3)StopwatchMaker has timed out and cancelled");
            stopwatchResult.cancel(true);
            e.printStackTrace();
        }
    }

    public void stop() {
        service.shutdown();
    }
}
