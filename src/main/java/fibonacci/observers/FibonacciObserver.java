package fibonacci.observers;

import fibonacci.FibonacciMaker;

public class FibonacciObserver implements IFibonacciObserver {
    @Override
    public void update(FibonacciMaker.State state) {
        System.out.printf("State : \nfirst : %s\nsecond : %s\n", state.getFirst(), state.getSecond());
    }
}
