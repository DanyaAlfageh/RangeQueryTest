package com.workday;

/**
 * an iterator of Ids
 */

public interface RangeContainer {
  /**
   * @return the Ids of all instances found in the container that have data value between fromValue
   *         and toValue with optional inclusivity.
   */

  Ids findIdsInRange(long fromValue, long toValue, boolean fromInclusive, boolean toInclusive);
}
