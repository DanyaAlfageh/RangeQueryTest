package com.workday;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Container Object by using TreeMap.
 * 
 * @author rantao
 *
 */
final public class TreeMapRangeContainerImpl implements RangeContainer {

  // final private NavigableMap<Long, Short> treeMap;
  final private NavigableMap<Long, List<Short>> multipleIdsMap;

  /**
   * Constructor
   * 
   * @param data
   */
  public TreeMapRangeContainerImpl(long[] data) {

    // this.treeMap = Collections.synchronizedNavigableMap(new TreeMap<Long, Short>());
    this.multipleIdsMap = Collections.synchronizedNavigableMap(new TreeMap<Long, List<Short>>());

    if (data != null && data.length != 0) {
      for (short i = 0; i < data.length; i++) {
        if (!multipleIdsMap.containsKey(data[i])) {
          List<Short> ids = new ArrayList<Short>();
          ids.add(i);
          multipleIdsMap.put(data[i], ids);
        } else {
          multipleIdsMap.get(data[i]).add(i);
        }
      }
    }
  }

  @Override
  public Ids findIdsInRange(long fromValue, long toValue, boolean fromInclusive, boolean toInclusive) {

    IdsFromTreeMapImpl ids = null;

    // validate input condition
    if (fromValue > toValue || (fromValue == toValue && fromInclusive != toInclusive)
        || (fromValue == toValue && fromInclusive == false && toInclusive == false)) {

      ids = new IdsFromTreeMapImpl();
    } else if (this.multipleIdsMap == null || this.multipleIdsMap.size() == 0) {

      ids = new IdsFromTreeMapImpl();
    } else {

      NavigableMap<Long, List<Short>> subMap =
          multipleIdsMap.subMap(fromValue, fromInclusive, toValue, toInclusive);

      synchronized (this.multipleIdsMap) {
        List<Short> temp = new ArrayList<Short>();
        Iterator<List<Short>> it = subMap.values().iterator();
        while (it.hasNext()) {
          temp.addAll(it.next());
        }
        ids = new IdsFromTreeMapImpl(temp);
      }
    }

    return ids;
  }
}
