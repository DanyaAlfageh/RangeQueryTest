package com.workday;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Randomized BST to store all the data
 * 
 * @author rantao
 *
 */
final public class BalancedBST {

  private TreeNode root;

  /**
   * Constructor
   * 
   * @param data
   */
  public BalancedBST(long[] data) {

    KeyValuePair[] kvpArr = new KeyValuePair[data.length];
    for (short i = 0; i < data.length; i++) {
      kvpArr[i] = new KeyValuePair(i, data[i]);
    }

    Arrays.sort(kvpArr);
    this.root = createBBST(kvpArr);
  }

  private TreeNode createBBST(KeyValuePair[] sortedKVPArr) {

    if (sortedKVPArr == null || sortedKVPArr.length == 0) {
      return null;
    }

    int begin = 0;
    int end = sortedKVPArr.length;
    int mid = begin + (end - begin) / 2;

    KeyValuePair[] left = Arrays.copyOfRange(sortedKVPArr, begin, mid);
    KeyValuePair[] right = Arrays.copyOfRange(sortedKVPArr, mid + 1, end);

    TreeNode node = new TreeNode(sortedKVPArr[mid].id, sortedKVPArr[mid].value);

    if (left.length > 0)
      node.left = createBBST(left);
    if (right.length > 0)
      node.right = createBBST(right);

    this.root = node;

    return this.root;
  }

  // search all the ids in the given range
  public List<Short> searchIdsInRange(long fromValue, long toValue, boolean fromInclusive,
      boolean toInclusive) {

    List<Short> list = new ArrayList<Short>();
    searchIdsInRange(root, fromValue, toValue, fromInclusive, toInclusive, list);
    return list;
  }

  private void searchIdsInRange(TreeNode root, long fromValue, long toValue, boolean fromInclusive,
      boolean toInclusive, List<Short> list) {

    if (root == null) {
      return;
    }

    if (root.value > fromValue && root.value < toValue) {
      list.add(root.id);
      searchIdsInRange(root.left, fromValue, toValue, fromInclusive, toInclusive, list);
      searchIdsInRange(root.right, fromValue, toValue, fromInclusive, toInclusive, list);
    }

    if (fromValue != toValue) {
      if (root.value == fromValue && fromInclusive) {
        list.add(root.id);
        searchIdsInRange(root.right, fromValue, toValue, fromInclusive, toInclusive, list);
      }

      if (root.value == toValue && toInclusive) {
        list.add(root.id);
        searchIdsInRange(root.left, fromValue, toValue, fromInclusive, toInclusive, list);
      }
    } else if (root.value == fromValue) {
      list.add(root.id);
      searchIdsInRange(root.left, fromValue, toValue, fromInclusive, toInclusive, list);
      searchIdsInRange(root.right, fromValue, toValue, fromInclusive, toInclusive, list);
    }

    // no need to search left tree
    if (root.value < fromValue) {
      searchIdsInRange(root.right, fromValue, toValue, fromInclusive, toInclusive, list);
    }

    // no need to search right tree
    if (root.value > toValue) {
      searchIdsInRange(root.left, fromValue, toValue, fromInclusive, toInclusive, list);
    }
  }
  /*
   * // insert node to the tree private TreeNode insert(TreeNode node, short id, long value) { if
   * (node == null) { return new TreeNode(id, value); }
   * 
   * if (this.random.nextBoolean()) { return insertRoot(node, id, value); }
   * 
   * if (node.value >= value) { node.left = insert(node.left, id, value); } else { node.right =
   * insert(node.right, id, value); } return node; }
   * 
   * private TreeNode insertRoot(TreeNode node, short id, long value) { if (node == null) { return
   * new TreeNode(id, value); } if (node.value >= value) { node.left = insertRoot(node.left, id,
   * value); node = rotateRight(node); } else { node.right = insertRoot(node.right, id, value); node
   * = rotateLeft(node); } return node; }
   * 
   * // rotate the tree private TreeNode rotateRight(TreeNode node) { TreeNode top = node.left;
   * node.left = top.right; top.right = node; return top; }
   * 
   * private TreeNode rotateLeft(TreeNode node) { TreeNode top = node.right; node.right = top.left;
   * top.left = node; return top; }
   */
}
