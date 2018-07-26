package org.tomn.ocr.opencv;

import org.opencv.core.Mat;

import java.io.File;

public class TableTool {
  private static final String BASE_PATH = "C:\\Users\\Administrator\\Desktop\\ocr";

  public static void saveImage() {

  }

  public static void readImage(String fileName, Mat image) {
    String outImagePath = BASE_PATH + File.separator + fileName;
    File file = new File(outImagePath);
    ensureDirExist(file.getParent());
  }
  private static void ensureDirExist(String dirPath) {
    File dir = new File(dirPath);
    if (!dir.exists()) {
      dir.mkdirs();
    }
  }

}
