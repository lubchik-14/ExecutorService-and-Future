package copying;

import java.io.*;

public class CopyMaker implements Runnable {
    private String path;

    public CopyMaker(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        String newPath = path.substring(0, path.lastIndexOf(".")) + "(copy)" + path.substring(path.lastIndexOf("."));
        try (InputStream in = new BufferedInputStream(new FileInputStream(path));
             OutputStream out = new BufferedOutputStream(new FileOutputStream(newPath))) {
            byte[] buffer = new byte[4096];
            int readCount;
            while (in.available() > 0 && !Thread.currentThread().isInterrupted()) {
                readCount = in.read(buffer);
                if (readCount > -1) {
                    out.write(buffer, 0, readCount);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Wrong path");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Failed with IOException");
            e.printStackTrace();
        }

    }
}
