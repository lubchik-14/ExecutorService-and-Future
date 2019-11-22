package stopwatch;

import stopwatch.observers.IStopwatchObserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StopwatchMaker implements Runnable, StopwatchObservable {
    private int tick = 0;
    private final int limit;
    private List<IStopwatchObserver> observers = new ArrayList<>();
    private boolean changed = false;

    public StopwatchMaker(int limit) {
        this.limit = limit;
    }

    public StopwatchMaker() {
        this.limit = Integer.MAX_VALUE;
    }

    @Override
    public void run() {
        while ((!Thread.currentThread().isInterrupted()) && (tick < limit)) {
            tick++;
            setChanges();
            notifyObservers();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("(4)InterruptedException in StopwatchMaker");
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void addObserver(IStopwatchObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IStopwatchObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
       observers.forEach(o -> o.update(tick));
       changed = false;
    }

    @Override
    public void notifyObservers(IStopwatchObserver...observers) {
        if (changed) {
            Arrays.stream(observers).forEach(o -> o.update(tick));
        }
        changed = false;
    }

    @Override
    public void setChanges() {
        changed = true;
    }
}
