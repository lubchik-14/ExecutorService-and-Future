package stopwatch;

import stopwatch.observers.IStopwatchObserver;

public interface StopwatchObservable {
    void addObserver(IStopwatchObserver observer);
    void removeObserver(IStopwatchObserver observer);
    void notifyObservers(IStopwatchObserver ... observers);
    void notifyObservers();
    void setChanges();
}
