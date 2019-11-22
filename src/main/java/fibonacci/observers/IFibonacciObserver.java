package fibonacci.observers;

import fibonacci.FibonacciMaker;

public interface IFibonacciObserver {
    void update(FibonacciMaker.State state);
}
