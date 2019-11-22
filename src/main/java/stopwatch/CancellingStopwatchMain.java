package stopwatch;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CancellingStopwatchMain {
    public static void main(String[] args) {
        StopwatchRunner runner = new StopwatchRunner(Executors.newCachedThreadPool());

        runner.startStopwatch(3, TimeUnit.SECONDS);

        runner.stop();
    }
}
