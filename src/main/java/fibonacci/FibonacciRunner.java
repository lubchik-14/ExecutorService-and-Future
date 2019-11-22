package fibonacci;

import java.util.Optional;
import java.util.concurrent.*;

public class FibonacciRunner {
    ExecutorService service;

    public FibonacciRunner(ExecutorService service) {
        this.service = service;
    }

    public FibonacciMaker.State findFibonacci(long timeout, TimeUnit unit, Optional<FibonacciMaker.State> state) {
        FibonacciMaker fibonacciMaker = state.map(FibonacciMaker::new).orElseGet(FibonacciMaker::new);
        return runFibonacci(fibonacciMaker, timeout, unit);
    }

    private FibonacciMaker.State runFibonacci(FibonacciMaker fibonacciMaker, long timeout, TimeUnit unit) {
        Future<?> fibonacciResult = service.submit(fibonacciMaker);
        try {
            fibonacciResult.get(timeout, unit);
        } catch (InterruptedException e) {
            System.out.println("Current thread was interrupted/cancelled");
            fibonacciResult.cancel(true);
            System.out.println("FibonacciMaker has been cancelled");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.err.println("Internal fibonacci.FibonacciMaker exception: " + e.getMessage());
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("FibonacciMaker has timed out and cancelled");
            fibonacciResult.cancel(true);
            e.printStackTrace();
        }
        return fibonacciMaker.getState();
    }

    public void stop() {
        service.shutdown();
    }
}
