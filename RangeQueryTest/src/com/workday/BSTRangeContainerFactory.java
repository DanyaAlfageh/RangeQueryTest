package com.workday;

public class BSTRangeContainerFactory implements RangeContainerFactory {

  @Override
  public BSTRangeContainer createContainer(long[] data) {
    if (data == null || data.length == 0)
      return null;

    BSTRangeContainer bstRangeContainer = new BSTRangeContainer(data);
    return bstRangeContainer;
  }
}
