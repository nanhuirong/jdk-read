package org.tomn.aop.impl;

import org.tomn.aop.AfterHandler;

import java.lang.reflect.Method;

public class AfterHandlerImpl extends AfterHandler {
  @Override
  public void handleAfter(Object proxy, Method method, Object[] args) {
    System.out.println("Handling after actual method execution ........");
  }
}
