package fibonacci;

import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CancellingFibonacciMain {
    public static void main(String[] args) {
        FibonacciRunner runner = new FibonacciRunner(Executors.newCachedThreadPool());

        FibonacciMaker.State state;
        state = runner.findFibonacci(2, TimeUnit.NANOSECONDS, Optional.empty());
        System.out.printf("%-10s%s\n%-10s%s\n", "first :", state.getFirst(), "second :", state.getSecond());
        System.out.println("Resuming finding Fibonacci");
        state = runner.findFibonacci(1, TimeUnit.NANOSECONDS, Optional.of(state));
        System.out.printf("%-10s%s\n%-10s%s\n", "first :", state.getFirst(), "second :", state.getSecond());
        runner.stop();
    }
}
