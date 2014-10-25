package com.workday;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class IdsFromTreeMapImpl implements Ids {

  final List<Short> idList;

  private short i = 0;

  /**
   * dummy constructor
   */
  public IdsFromTreeMapImpl() {
    idList = new ArrayList<Short>();
  }

  /**
   * Constructor
   * 
   * @param objArr
   */
  public IdsFromTreeMapImpl(List<Short> obj) {

    idList = new ArrayList<Short>();
    idList.addAll(obj);
    Collections.sort(idList);
  }

  @Override
  public short nextId() {
    if (this.idList != null && this.idList.size() > 0 && i < idList.size()) {

      return idList.get(i++);
    } else {
      // empty idList or index is out of bound
      return END_OF_IDS;
    }
  }
}
