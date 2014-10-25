package com.workday;

import java.util.List;

public class IdsFromBBSTImpl implements Ids {

  private List<Short> idList;
  private short i = 0;

  public IdsFromBBSTImpl(List<Short> idList) {
    this.idList = idList;
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
