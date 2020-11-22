import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueHandler {
    private static class QueueInstance{
        public static final Queue<Image> imageInstance = new ConcurrentLinkedQueue();
    }

    public static Queue getInstance(){
        return QueueInstance.imageInstance;
    }
}
