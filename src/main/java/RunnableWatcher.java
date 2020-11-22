import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

public class RunnableWatcher implements Runnable {

    private String watchDir = "";
    private static Set<String> set = new HashSet<>();

    RunnableWatcher(String watchDir){
        this.watchDir = watchDir;
    }

    //이미지 깨짐여부 및 파일이 이미지인지 확인
    private boolean isImage(String filepath){
        boolean result = false;
        File f = new File(filepath);
        try {
            BufferedImage buf = ImageIO.read(f);
            if(buf == null){
                result = false;
            } else {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void getAllFileNamesRecursive(String path) {
        try {
            Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .forEach(item -> {
                        String filepath = item.toString();

                        if (isImage(filepath)) {
                            if (!set.contains(filepath)) {
                                int lastIndex = filepath.lastIndexOf('.');
                                QueueHandler.getInstance().add(new Image(filepath, filepath.substring(0, lastIndex) + ".txt"));
                                set.add(filepath);
                            }
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                getAllFileNamesRecursive(watchDir);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
