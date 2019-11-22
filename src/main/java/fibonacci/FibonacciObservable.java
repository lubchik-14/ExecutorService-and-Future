package fibonacci;

import fibonacci.observers.IFibonacciObserver;

public interface FibonacciObservable {
    void addObserver(IFibonacciObserver observer);
    void removeObserver(IFibonacciObserver observer);
    void notifyObservers(IFibonacciObserver ... observers);
    void notifyObservers();
    void setChanges();
}
