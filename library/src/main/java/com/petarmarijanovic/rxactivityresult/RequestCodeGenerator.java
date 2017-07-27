package com.petarmarijanovic.rxactivityresult;

import java.util.concurrent.atomic.AtomicInteger;

/** Created by petar on 27/07/2017. */
public class RequestCodeGenerator {

  private static final AtomicInteger seed = new AtomicInteger(500);

  public static int generate() {
    return seed.incrementAndGet();
  }
}
