import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RunnableOCR implements Runnable {
    public void run() {
        while (true) {
            try {
                if(!Main.queue.isEmpty()){
                    Image image = (Image) Main.queue.poll();
                    File imageFile = new File(image.getFilename());
                    File extractFile = new File(image.getOutPath());
                    ITesseract instance = new Tesseract();

                    instance.setLanguage("eng+kor");
//                    instance.setDatapath("C:\\Users\\krpw1\\Desktop\\Backjoon\\CleanArchitecture\\src\\main\\resources\\tessdata");
                    File tessDataFolder = LoadLibs.extractTessResources("tessdata");
                    instance.setDatapath(tessDataFolder.getAbsolutePath());
                    String result = instance.doOCR(imageFile);
                    if(extractFile.createNewFile()){
                        BufferedWriter writer = new BufferedWriter(new FileWriter(extractFile));
                        writer.append(result);
                        writer.flush();
                        writer.close();
                        System.out.println("Done!!! : " + image.getOutPath());
                    }
                }
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TesseractException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
