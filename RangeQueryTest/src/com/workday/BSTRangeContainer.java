package com.workday;

import java.util.Collections;
import java.util.List;

final public class BSTRangeContainer implements RangeContainer {

  private BalancedBST bst;


  public BSTRangeContainer(long[] data) {
    bst = new BalancedBST(data);
  }

  @Override
  public Ids findIdsInRange(long fromValue, long toValue, boolean fromInclusive, boolean toInclusive) {
    List<Short> idList = bst.searchIdsInRange(fromValue, toValue, fromInclusive, toInclusive);
    Collections.sort(idList);
    return new IdsFromBST(idList);
  }
}
