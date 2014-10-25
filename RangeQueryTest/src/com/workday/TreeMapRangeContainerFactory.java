package com.workday;

import java.util.Collections;
import java.util.TreeMap;

public class TreeMapRangeContainerFactory implements RangeContainerFactory {

  @Override
  public RangeContainer createContainer(long[] data) {

    TreeMapRangeContainer myRangeContainer =
        new TreeMapRangeContainer(Collections.synchronizedNavigableMap(new TreeMap<Long, Short>()));
    try {
      myRangeContainer.initTreeMap(data);
    } catch (Exception e) {

      e.printStackTrace();
    }
    return myRangeContainer;
  }
}
