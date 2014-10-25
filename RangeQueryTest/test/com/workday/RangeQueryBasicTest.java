package com.workday;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RangeQueryBasicTest {

  private RangeContainer rc1;
  private RangeContainer largeVolumnRC1;

  private RangeContainer rc2;
  private RangeContainer largeVolumnRC2;
  private long[] testData = new long[32000];


  @Before
  public void setUp() {
    RangeContainerFactory rf1 = new TreeMapRangeContainerFactory();
    RangeContainerFactory rf2 = new BSTRangeContainerFactory();

    // simple case
    rc1 = rf1.createContainer(new long[] {10, 12, 17, 21, 2, 15, 16});
    rc2 = rf2.createContainer(new long[] {10, 12, 17, 21, 2, 15, 16});

    // large Volume
    int tempData = 1000;
    for (int i = 0; i < 32000; i++) {
      testData[i] = tempData;
      tempData += 1000;
    }
    largeVolumnRC1 = rf1.createContainer(testData);
    largeVolumnRC2 = rf2.createContainer(testData);
  }

  @Test
  public void runARangeQueryOnTreeMapImpl() {
    Ids ids = rc1.findIdsInRange(14, 17, true, true);
    assertEquals(2, ids.nextId());
    assertEquals(5, ids.nextId());
    assertEquals(6, ids.nextId());
    assertEquals(Ids.END_OF_IDS, ids.nextId());
    ids = rc1.findIdsInRange(14, 17, true, false);
    assertEquals(5, ids.nextId());
    assertEquals(6, ids.nextId());
    assertEquals(Ids.END_OF_IDS, ids.nextId());
    ids = rc1.findIdsInRange(20, Long.MAX_VALUE, false, true);
    assertEquals(3, ids.nextId());
    assertEquals(Ids.END_OF_IDS, ids.nextId());
  }

  @Test
  public void runLargeVolumRangeQueryOnTreeMap() {
    Ids ids = largeVolumnRC1.findIdsInRange(1300, 32000000, true, true);
    assertEquals(1, ids.nextId());
  }

  @Test
  public void runARangeQueryOnBSTImpl() {
    Ids ids = rc2.findIdsInRange(14, 17, true, true);
    assertEquals(2, ids.nextId());
    assertEquals(5, ids.nextId());
    assertEquals(6, ids.nextId());
    assertEquals(Ids.END_OF_IDS, ids.nextId());
    ids = rc2.findIdsInRange(14, 17, true, false);
    assertEquals(5, ids.nextId());
    assertEquals(6, ids.nextId());
    assertEquals(Ids.END_OF_IDS, ids.nextId());
    ids = rc2.findIdsInRange(20, Long.MAX_VALUE, false, true);
    assertEquals(3, ids.nextId());
    assertEquals(Ids.END_OF_IDS, ids.nextId());
  }

  @Test
  public void runLargeVolumRangeQuery() {
    Ids ids = largeVolumnRC2.findIdsInRange(1300, 32000000, true, true);
    assertEquals(1, ids.nextId());
  }
}
