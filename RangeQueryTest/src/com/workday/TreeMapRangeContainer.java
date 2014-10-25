package com.workday;

import java.util.NavigableMap;

/**
 * Container Object by using TreeMap.
 * 
 * @author rantao
 *
 */
final public class TreeMapRangeContainer implements RangeContainer {

  final private NavigableMap<Long, Short> treeMap;

  public TreeMapRangeContainer(NavigableMap<Long, Short> treeMap) {
    this.treeMap = treeMap;
  }

  // Create the TreeMap
  public void initTreeMap(long[] data) throws Exception {
    // validate input data
    if (data == null || data.length == 0) {
      throw new Exception("Input data is invalid!");
    }

    for (short i = 0; i < data.length; i++) {
      this.treeMap.put(Long.valueOf(data[i]), Short.valueOf(i));
    }
  }

  @Override
  public Ids findIdsInRange(long fromValue, long toValue, boolean fromInclusive, boolean toInclusive) {

    IdsFromTreeMap ids = null;
    // validate input condition
    if (fromValue > toValue || (fromValue == toValue && fromInclusive != toInclusive)
        || (fromValue == toValue && fromInclusive == toInclusive == false)) {
      Short[] test = new Short[0];
      ids = new IdsFromTreeMap(test);
      return ids;
    }

    NavigableMap<Long, Short> subMap =
        this.treeMap.subMap(fromValue, fromInclusive, toValue, toInclusive);

    synchronized (this.treeMap) {
      Object[] objArr = subMap.values().toArray(new Short[subMap.size()]);
      if (objArr instanceof Short[]) {
        ids = new IdsFromTreeMap((Short[]) objArr);
      }
    }

    return ids;
  }
}
