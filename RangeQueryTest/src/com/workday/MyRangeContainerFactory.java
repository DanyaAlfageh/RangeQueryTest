package com.workday;

public class MyRangeContainerFactory implements RangeContainerFactory {

  @Override
  public RangeContainer createContainer(long[] data) {
    MyRangeContainer myRangeContainer = new MyRangeContainer();
    try {
      myRangeContainer.initTreeMap(data);
    } catch (Exception e) {

      e.printStackTrace();
    }
    return myRangeContainer;
  }
}
