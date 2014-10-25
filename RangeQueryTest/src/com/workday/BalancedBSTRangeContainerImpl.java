package com.workday;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Balanced BST Container
 * 
 * @author rantao
 *
 */
final public class BalancedBSTRangeContainerImpl implements RangeContainer {

  final private BalancedBST bst;

  /**
   * Constructor
   * 
   * @param data
   */
  public BalancedBSTRangeContainerImpl(long[] data) {
    bst = new BalancedBST(data);
  }

  @Override
  public Ids findIdsInRange(long fromValue, long toValue, boolean fromInclusive, boolean toInclusive) {
    // validate input condition
    if (fromValue > toValue || (fromValue == toValue && fromInclusive != toInclusive)
        || (fromValue == toValue && fromInclusive == false && toInclusive == false)) {
      return new IdsFromBBSTImpl(new ArrayList<Short>());
    }

    List<Short> idList = bst.searchIdsInRange(fromValue, toValue, fromInclusive, toInclusive);
    Collections.sort(idList);
    return new IdsFromBBSTImpl(idList);
  }
}
