package fibonacci;

import fibonacci.observers.IFibonacciObserver;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FibonacciMaker implements Runnable, FibonacciObservable {
    private State state;
    private List<IFibonacciObserver> observers = new ArrayList<>();
    private boolean changed = false;

    public FibonacciMaker() {
        this.state = new State();
    }

    public FibonacciMaker(State state) {
        this.state = state;
    }

    public static class State {
        private BigInteger first = BigInteger.ZERO;
        private BigInteger second = BigInteger.ONE;

        public BigInteger getFirst() {
            return first;
        }

        public BigInteger getSecond() {
            return second;
        }
    }

    @Override
    public void run() {
        BigInteger sum;
        while(!Thread.currentThread().isInterrupted()) {
            sum = state.first.add(state.second);
            state.first = state.second;
            state.second = sum;
            setChanges();
            notifyObservers();
        }
    }

    public State getState() {
        return state;
    }

    @Override
    public void addObserver(IFibonacciObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IFibonacciObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(IFibonacciObserver... observers) {
        if (changed) {
            Arrays.stream(observers).forEach(o -> o.update(state));
        }
        changed = false;
    }

    @Override
    public void notifyObservers() {
        observers.forEach(o -> o.update(state));
        changed = false;
    }

    @Override
    public void setChanges() {
        changed = true;
    }
}
