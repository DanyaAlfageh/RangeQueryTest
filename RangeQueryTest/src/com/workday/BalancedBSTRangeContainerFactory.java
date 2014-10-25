package com.workday;

public class BalancedBSTRangeContainerFactory implements RangeContainerFactory {

  /**
   * Factory method to create container
   */
  @Override
  public BalancedBSTRangeContainer createContainer(long[] data) {
    if (data == null || data.length == 0)
      return null;

    BalancedBSTRangeContainer bstRangeContainer = new BalancedBSTRangeContainer(data);
    return bstRangeContainer;
  }
}
