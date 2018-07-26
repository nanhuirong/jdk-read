package org.tomn.algorithm;

public class BitMap {
  private static final int _1MB = 1024 * 1024;
  private static byte[] FLAGS = new byte[512 * _1MB];

  public static void setFLAGS(int num) {
    // 255 = 0x11111111
    // (num & 0x07) = 0x111
    FLAGS[num >> 3] |= 0x01 << (num & (0x07));
  }

  public static boolean isDuplicates(int num) {
    return (FLAGS[num >> 3] >> (num & (0x07)) & 0x01) == 0x01;
  }
}
