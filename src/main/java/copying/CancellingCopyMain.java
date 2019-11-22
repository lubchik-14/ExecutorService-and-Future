package copying;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CancellingCopyMain {
    public static void main(String[] args) {
        CopyRunner runner = new CopyRunner(Executors.newCachedThreadPool());

        if (args.length == 0) {
            System.out.println("Cannot resolve path");
            System.exit(-1);
        }

        runner.copyFile(args[0], 3, TimeUnit.SECONDS);
        runner.stop();
    }
}
