package org.tomn.aop.test;

import org.tomn.aop.*;
import org.tomn.aop.impl.AfterHandlerImpl;
import org.tomn.aop.impl.BeforeHandlerImpl;

import java.util.ArrayList;
import java.util.List;

public class Test {
  public static void main(String[] args) {
    CalculatorImpl calcImpl = new CalculatorImpl();
    BeforeHandler before = new BeforeHandlerImpl();
    AfterHandler after = new AfterHandlerImpl();
    List<AbstractHandler> handlers = new ArrayList<AbstractHandler>();
    handlers.add(before);
    handlers.add(after);
    Calculator proxy = (Calculator) ProxyFactory.getProxy(calcImpl,
        handlers);
    int result = proxy.calculate(20, 10);
    System.out.println("FInal Result :::" + result);
  }
}
