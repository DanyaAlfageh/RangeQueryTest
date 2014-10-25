package com.workday;

public class BalancedBSTRangeContainerFactoryImpl implements RangeContainerFactory {

  /**
   * Factory method to create container
   */
  @Override
  public BalancedBSTRangeContainerImpl createContainer(long[] data) {
    if (data == null || data.length == 0)
      return null;

    BalancedBSTRangeContainerImpl bstRangeContainer = new BalancedBSTRangeContainerImpl(data);
    return bstRangeContainer;
  }
}
