package org.tomn.tesseract;

import net.sourceforge.tess4j.*;
import net.sourceforge.tess4j.ITesseract.RenderedFormat;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TesseractExample {
  public static void main(String[] args) throws Exception, IOException {
    String tessDataPath = "C:\\Users\\Administrator\\Desktop\\book\\tessdata";
    String fileName = "C:\\Users\\Administrator\\Desktop\\tesseract\\IRA.VDA4905_000031_13.png";
    String outFileName = "C:\\Users\\Administrator\\Desktop\\tesseract\\out\\IRA.VDA4905_000031_12";
    Tesseract instance = new Tesseract();
    /*
    instance.setConfigs();
    instance.setOcrEngineMode();
    instance.setPageSegMode();
    */
    instance.setDatapath(tessDataPath);
    long start = System.currentTimeMillis();
    long end = System.currentTimeMillis();
    instance.doOCR(new File(fileName));
    System.out.println((end - start) / (1000 * 60));

//    List<RenderedFormat> lists = new ArrayList<>();
//    lists.add(RenderedFormat.BOX);
//    lists.add(RenderedFormat.HOCR);
//    lists.add(RenderedFormat.PDF);
//    lists.add(RenderedFormat.TEXT);
//    lists.add(RenderedFormat.UNLV);
//    instance.createDocuments(fileName, outFileName,  lists);
//    System.out.println(instance.doOCR(new File(fileName), new Rectangle(374, 378, 1348, 306)));
    System.out.println(instance.doOCR(new File(fileName)));


    // get the content from the image
    int level = 2;

    /*
    BufferedImage bufferedImage = ImageIO.read(new File(fileName));

    List<Word> words = instance.getWords(bufferedImage, level);
    for (Word word: words) {
      System.out.println("text: " + word.getText());
//      System.out.println("text: " + word.getText() + "\nBoundingBox: " + word.getBoundingBox() + "\nConfidence: " + word.getConfidence());
      System.out.println("--------------------------------------------------------------------------");
    }

    // get the region, similar with the getWords
    List<Rectangle> rectangles = instance.getSegmentedRegions(bufferedImage, level);
    for (Rectangle rectangle: rectangles) {
      System.out.println(rectangle);
      System.out.println("--------------------------------------------------------------------------");
    }
    */




  }
}
