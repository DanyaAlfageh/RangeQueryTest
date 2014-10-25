package com.workday;

import java.util.Arrays;


public class IdsFromTreeMap implements Ids {

  final Short[] idArr;

  private int i = 0;

  public IdsFromTreeMap(Short[] objects) {
    Arrays.sort(objects);
    this.idArr = objects;
  }

  @Override
  public short nextId() {
    if (idArr == null || idArr.length == 0 || i >= idArr.length) {
      return END_OF_IDS;
    }

    return idArr[i++];
  }
}
