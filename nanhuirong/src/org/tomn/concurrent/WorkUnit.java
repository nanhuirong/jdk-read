package org.tomn.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// add components
public class WorkUnit<T> {
  private final T workUnit;

  public WorkUnit(T workUnit_) {
    workUnit = workUnit_;
  }

  public T getWorkUnit() {
    return workUnit;
  }

  // for test
  public static void main(String[] args) {
    BlockingQueue<WorkUnit<String>> queue = new LinkedBlockingQueue<WorkUnit<String>>();
  }
}
