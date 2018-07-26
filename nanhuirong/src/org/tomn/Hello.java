package org.tomn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Hello {

  public static void main(String[] args) {
    File file  = new File("C:\\ProgramData\\RSSBus\\connect");
    System.out.println(file.exists());
    System.out.println(file.canRead() + "\t" + file.canWrite());



  }
}
