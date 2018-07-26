package org.tomn.tesseract;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Word;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class ConvertHandler {
  private static final String FILE_NAME = "C:\\Users\\Administrator\\Desktop\\tesseract\\test.jpg";
  private static final String TESS_DATA_FOLDER = "C:\\Users\\Administrator\\Desktop\\book\\tessdata";
  public static void main(String[] args) throws Exception {
    BufferedImage bufferedImage = ImageIO.read(new File(FILE_NAME));
    Tesseract instance = new Tesseract();
    instance.setDatapath(TESS_DATA_FOLDER);
    System.out.println(instance.doOCR(new File(FILE_NAME)));
    /*
    List<Word> words = instance.getWords(bufferedImage, 2);
    for (Word word: words) {
      System.out.println("text: " + word.getText() + "\nBoundingBox: " + word.getBoundingBox() + "\nConfidence: " + word.getConfidence());
      System.out.println("--------------------------------------------------------------------------");
    }
    */
  }
}
