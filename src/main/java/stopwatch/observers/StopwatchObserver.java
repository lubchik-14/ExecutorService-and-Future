package stopwatch.observers;

public class StopwatchObserver implements IStopwatchObserver {
    @Override
    public void update(int tick) {
        System.out.print("\b\b" + tick + "\t");
    }
}
