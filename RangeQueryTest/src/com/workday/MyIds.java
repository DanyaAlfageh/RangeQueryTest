package com.workday;

import java.util.Arrays;


public class MyIds implements Ids {

  final Short[] it;

  private int i = 0;

  public MyIds(Short[] objects) {
    Arrays.sort(objects);
    this.it = objects;
  }



  @Override
  public short nextId() {
    if (it == null || it.length == 0 || i >= it.length) {
      return END_OF_IDS;
    }

    return it[i++];
  }
}
