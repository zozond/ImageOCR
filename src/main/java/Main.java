import java.util.concurrent.*;

public class Main{
    public static ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue<Image>();

    public static void main(String[] args) {

//        String watchDir = args[0];
        String watchDir = "C:/Users/krpw1/Desktop/img";

        int nThreads = Runtime.getRuntime().availableProcessors(); // 현재 사용 가능한 프로세서 갯수
        nThreads = 3;
        ExecutorService threadPool = Executors.newFixedThreadPool(nThreads);

        RunnableWatcher watcher = new RunnableWatcher(watchDir);
        threadPool.submit(watcher);

        for(int i = 1 ; i< nThreads; i++){
            RunnableOCR ocr = new RunnableOCR();
            threadPool.submit(ocr);
        }

        threadPool.shutdown();
    }
}