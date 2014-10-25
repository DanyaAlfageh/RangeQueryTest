package com.workday;


public class TreeMapRangeContainerFactoryImpl implements RangeContainerFactory {

  @Override
  public RangeContainer createContainer(long[] data) {

    return new TreeMapRangeContainerImpl(data);
  }
}
