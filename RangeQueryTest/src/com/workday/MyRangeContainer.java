package com.workday;

import java.util.Collections;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class MyRangeContainer implements RangeContainer {

  final private NavigableMap<Long, Short> treeMap = Collections
      .synchronizedNavigableMap(new TreeMap<Long, Short>());;

  public void initTreeMap(long[] data) throws Exception {

    if (data == null || data.length == 0) {
      throw new Exception("Input data is invalid!");
    }

    for (short i = 0; i < data.length; i++) {
      this.treeMap.put(Long.valueOf(data[i]), Short.valueOf(i));
    }
  }

  @Override
  public Ids findIdsInRange(long fromValue, long toValue, boolean fromInclusive, boolean toInclusive) {

    MyIds ids = null;
    NavigableMap<Long, Short> subMap =
        this.treeMap.subMap(fromValue, fromInclusive, toValue, toInclusive);

    synchronized (this.treeMap) {
      Object[] objArr = subMap.values().toArray(new Short[subMap.size()]);
      if (objArr instanceof Short[]) {
        ids = new MyIds((Short[]) objArr);
      }
    }

    return ids;
  }

  public Map<Long, Short> getTreeMap() {
    return treeMap;
  }
}
