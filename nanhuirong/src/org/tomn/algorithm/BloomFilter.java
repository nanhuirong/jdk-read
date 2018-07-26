package org.tomn.algorithm;

import org.tomn.common.Utilities;

import java.util.BitSet;

public class BloomFilter {
  private static final int _1MB = 1024 * 1024;
  private static final int BITMAP_SIZE = 521 * _1MB;
  private BitSet bits = new BitSet(BITMAP_SIZE);
  private SimpleHash[] func = new SimpleHash[seeds.length];

  public BloomFilter() {
    for (int i = 0; i < seeds.length; i++) {
      func[i] = new SimpleHash(BITMAP_SIZE, seeds[i]);
    }
  }

  public void add(String value) {
    for (SimpleHash f: func) {
      bits.set(f.hash(value), true);
    }
  }

  public boolean contains(String value) {
    boolean result = false;
    if (Utilities.isNullOrEmpty(value)) {
      return result;
    }
    result = true;
    for (SimpleHash f : func) {
      result = result && bits.get(f.hash(value));
    }
    return result;
  }

  // hash seeds
  private static final int[] seeds = new int[] {5, 7, 11, 13, 31, 37, 61};

  public static class SimpleHash {
    private int cap;
    private int seed;

    public SimpleHash(int cap, int seed) {
      this.cap = cap;
      this.seed = seed;
    }

    public int hash(String value) {
      int result = 0;
      for (int i = 0; i < value.length(); i++) {
        result += seed * result + value.charAt(i);
      }
      return (cap - 1) & result;
    }
  }
}
