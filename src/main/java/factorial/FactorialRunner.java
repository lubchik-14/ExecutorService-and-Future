package factorial;

import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.*;

public class FactorialRunner {
    ExecutorService service;

    public FactorialRunner(ExecutorService service) {
        this.service = service;
    }

    public Optional<BigInteger> findFactorial(int limit, long timeout, TimeUnit unit) {
        Future<BigInteger> factorialResult = service.submit(new FactorialMaker(limit));
        BigInteger factorial = null;
        try {
             factorial = factorialResult.get(timeout, unit);
        } catch (InterruptedException e) {
            System.out.println("Current thread was interrupted/cancelled");
            factorialResult.cancel(true);
            System.out.println("FactorialMaker has been cancelled");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.err.println("Internal FactorialMaker exception: " + e.getMessage());
            factorialResult.cancel(true);
            e.printStackTrace();
        } catch (TimeoutException e) {
            factorialResult.cancel(true);
            System.out.println("FactorialMaker has timed out and cancelled");
            e.printStackTrace();
        }
        return Optional.ofNullable(factorial);
    }

    public void stop() {
        service.shutdown();
    }
}
