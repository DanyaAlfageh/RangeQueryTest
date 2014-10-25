package com.workday;

/**
 * Class to store id and value. It should have better memory usage than HashMap.
 * 
 * @author rantao
 *
 */
final public class KeyValuePair implements Comparable<KeyValuePair> {

  final private short id;
  final private long value;

  public KeyValuePair(short id, long value) {
    this.id = id;
    this.value = value;
  }

  @Override
  public int compareTo(KeyValuePair kvp) {
    if (this.value > kvp.value)
      return 1;
    else if (this.value == kvp.value)
      return 0;
    else
      return -1;
  }

  public short getId() {
    return id;
  }


  public long getValue() {
    return value;
  }
}
