package copying;

import java.util.concurrent.*;

public class CopyRunner {
    ExecutorService service;

    public CopyRunner(ExecutorService service) {
        this.service = service;
    }

    public void copyFile(String path, long timeout, TimeUnit unit) {
        CopyMaker copyMaker = new CopyMaker(path);
        Future<?> copyingResult = service.submit(copyMaker);
        try {
            copyingResult.get(timeout, unit);
        } catch (InterruptedException e) {
            copyingResult.cancel(true);
            System.out.println("Current thread was interrupted/cancelled");
            e.printStackTrace();
        } catch (ExecutionException e) {
            copyingResult.cancel(true);
            System.err.println("Internal stopwatch exception: " + e.getMessage());
            e.printStackTrace();
        } catch (TimeoutException e) {
            copyingResult.cancel(true);
            System.out.println("Copying has timed out and cancelled");
            e.printStackTrace();
        }
    }

    public void stop() {
        service.shutdown();
    }
}
