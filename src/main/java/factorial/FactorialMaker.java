package factorial;

import java.math.BigInteger;
import java.util.concurrent.Callable;

public class FactorialMaker implements Callable<BigInteger> {
    private int limit;

    public FactorialMaker(int limit) {
        this.limit = limit;
    }

    @Override
    public BigInteger call() {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= limit; i++) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("isInterrupted");
                break;
            }
            result = result.multiply(BigInteger.valueOf(i));
            System.out.println("Step: " + i);
        }
        return result;
    }
}
