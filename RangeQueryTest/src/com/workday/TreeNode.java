package com.workday;

/**
 * definition of node
 * 
 * @author rantao
 *
 */
final public class TreeNode {

  long value;
  short id;
  TreeNode left, right;

  public TreeNode(short id, long value) {
    this.id = id;
    this.value = value;
  }
}
