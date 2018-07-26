package org.tomn.aop;

public class CalculatorImpl implements Calculator {
  @Override
  public int calculate(int a, int b) {
    return a / b;
  }
}
