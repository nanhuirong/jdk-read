package org.tomn.common;

public class Utilities {
  public static boolean isNullOrEmpty(String value) {
    return (value == null || value.length() <= 0) ? true : false;
  }
}
