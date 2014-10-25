package com.workday;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RangeQueryBasicTest {

  // Regualr Cases
  private RangeContainer rc1;
  private RangeContainer largeVolumnRC1;

  private RangeContainer rc2;
  private RangeContainer largeVolumnRC2;
  private long[] testData = new long[32000];

  // Edge Cases
  private RangeContainer erc1;
  private RangeContainer elargeVolumnRC1;

  private RangeContainer erc2;
  private RangeContainer elargeVolumnRC2;

  private RangeContainer erc3;
  private RangeContainer elargeVolumnRC3;

  private RangeContainer erc4;
  private RangeContainer elargeVolumnRC4;


  @Before
  public void setUp() {
    RangeContainerFactory rf1 = new TreeMapRangeContainerFactory();
    RangeContainerFactory rf2 = new BalancedBSTRangeContainerFactory();

    // simple cases
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

    // edge case1: duplicated data
    erc1 = rf1.createContainer(new long[] {10000, 10000, 10000, 10000});
    erc2 = rf2.createContainer(new long[] {10000, 10000, 10000, 10000});

    // edge case2: large amount of duplicated data
    tempData = 200000;
    for (int i = 0; i < 32000; i++) {
      testData[i] = tempData;
    }
    elargeVolumnRC1 = rf1.createContainer(testData);
    elargeVolumnRC2 = rf2.createContainer(testData);

    // edge case3: Invalid Input Condition


  }

  /**
   * Given basic unit tests
   */
  @Test
  public void runARangeQueryAgainstTreeMap() {
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
  public void runARangeQueryAgainstBBST() {
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


  /**
   * Test the TreeMap Implementation
   */
  @Test
  public void runLargeVolumRangeQueryAgainstTreeMap() {
    Ids ids = largeVolumnRC1.findIdsInRange(1300, 32000000, true, true);
    assertEquals(1, ids.nextId());
    assertEquals(2, ids.nextId());
    ids = largeVolumnRC1.findIdsInRange(31999999, 32000000, true, true);
    assertEquals(31999, ids.nextId());
    assertEquals(Ids.END_OF_IDS, ids.nextId());
  }

  @Test
  public void edgeCase1AgainstTreeMap() {
    Ids ids = erc1.findIdsInRange(100, 10000, true, true);
    assertEquals(0, ids.nextId());
    assertEquals(1, ids.nextId());
    assertEquals(2, ids.nextId());
    assertEquals(3, ids.nextId());
    assertEquals(Ids.END_OF_IDS, ids.nextId());
  }

  @Test
  public void edgeCase2AgainstTreeMap() {
    Ids ids = elargeVolumnRC1.findIdsInRange(199999, 32000000, true, true);
    assertEquals(0, ids.nextId());
    assertEquals(1, ids.nextId());
    ids = elargeVolumnRC1.findIdsInRange(200000, 32000000, true, true);
    assertEquals(0, ids.nextId());
    assertEquals(1, ids.nextId());
    ids = elargeVolumnRC1.findIdsInRange(200000, 200000, true, true);
    assertEquals(0, ids.nextId());
    assertEquals(1, ids.nextId());
    assertEquals(2, ids.nextId());
    assertEquals(3, ids.nextId());
  }

  /**
   * Test the BBST Implementation
   */
  @Test
  public void runLargeVolumRangeQueryAgainstBBST() {
    Ids ids = largeVolumnRC2.findIdsInRange(1300, 32000000, true, true);
    assertEquals(1, ids.nextId());
    assertEquals(2, ids.nextId());
    ids = largeVolumnRC2.findIdsInRange(31999999, 32000000, true, true);
    assertEquals(31999, ids.nextId());
    assertEquals(Ids.END_OF_IDS, ids.nextId());
  }

  @Test
  public void edgeCase1AgainstBBST() {
    Ids ids = erc2.findIdsInRange(100, 10000, true, true);
    assertEquals(0, ids.nextId());
    assertEquals(1, ids.nextId());
    assertEquals(2, ids.nextId());
    assertEquals(3, ids.nextId());
    assertEquals(Ids.END_OF_IDS, ids.nextId());
  }
}
