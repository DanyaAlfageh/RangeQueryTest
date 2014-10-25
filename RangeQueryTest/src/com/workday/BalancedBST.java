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

  /**
   * Create Balanced Binary Tree
   * 
   * @param sortedKVPArr Sorted KVP array
   * @return
   */
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

  // Search all the ids in the given range
  public List<Short> searchIdsInRange(long fromValue, long toValue, boolean fromInclusive,
      boolean toInclusive) {

    List<Short> list = new ArrayList<Short>();
    searchIdsInRange(root, fromValue, toValue, fromInclusive, toInclusive, list);
    return list;
  }

  //@formatter:off
  /**
   * Search Ids in Range. following steps as below:
   * 1. check whether value equals range boundaries.
   * 2. check whether value is within range. If it is, recursively search left and right
   * 3. check whether value is out of range. If it is, cut the search tree
   * 
   * @param root
   * @param fromValue
   * @param toValue
   * @param fromInclusive
   * @param toInclusive
   * @param list
   */
  //@formatter:on
  private void searchIdsInRange(TreeNode root, long fromValue, long toValue, boolean fromInclusive,
      boolean toInclusive, List<Short> list) {

    if (root == null) {
      return;
    }

    // fromValue != toValue
    if (fromValue != toValue) {
      // if root.value equals to fromValue
      if (root.value == fromValue) {

        if (fromInclusive) {
          list.add(root.id);
        }
        searchIdsInRange(root.left, fromValue, toValue, fromInclusive, toInclusive, list);
        searchIdsInRange(root.right, fromValue, toValue, fromInclusive, toInclusive, list);
      }

      // if root.value equals to toValue
      if (root.value == toValue) {
        if (toInclusive) {
          list.add(root.id);
        }
        searchIdsInRange(root.left, fromValue, toValue, fromInclusive, toInclusive, list);
        searchIdsInRange(root.right, fromValue, toValue, fromInclusive, toInclusive, list);
      }

      // fromValue == toValue and rootValue equals to fromValue, need to search both left and right
      // trees in case of duplicated values
    } else if (root.value == fromValue) {
      list.add(root.id);
      searchIdsInRange(root.left, fromValue, toValue, fromInclusive, toInclusive, list);
      searchIdsInRange(root.right, fromValue, toValue, fromInclusive, toInclusive, list);
    }

    // if root.value is in the range, search left and right tree
    if (root.value > fromValue && root.value < toValue) {
      list.add(root.id);
      searchIdsInRange(root.left, fromValue, toValue, fromInclusive, toInclusive, list);
      searchIdsInRange(root.right, fromValue, toValue, fromInclusive, toInclusive, list);

    } // no need to search left tree
    else if (root.value < fromValue) {
      searchIdsInRange(root.right, fromValue, toValue, fromInclusive, toInclusive, list);
    }
    // no need to search right tree
    else if (root.value > toValue) {
      searchIdsInRange(root.left, fromValue, toValue, fromInclusive, toInclusive, list);
    }
  }
}
