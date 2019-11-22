package factorial;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CancellingFactorialMain {
    public static void main(String[] args) {
        FactorialRunner runner = new FactorialRunner(Executors.newCachedThreadPool());

        System.out.println("Factorial: " + runner.findFactorial(500, 1, TimeUnit.SECONDS));

        runner.stop();
    }
}
