package edu.yonsei.csi2103.bintree;

import java.util.function.Consumer;

/**
 * CSI2103-01 Data structures
 * Programming Homework #1
 *
 * A data structure that is a minor version of binary tree implemented to
 * sort on-line (sequential) input, and traverse them in in-order.
 * </p>
 * This data structure only provides the insert and traverse functionality.
 *
 * Created by Jisung Lim on 11/03/2017.
 */
public class BinTree<E extends Comparable<E>> {

  /**
   * Root node of the tree.
   */
  private BinNode<E> root;

  /**
   * The number of element in the tree.
   */
  private int size;

  /** Constructor */
  public BinTree() {
    root = null;
    size = 0;
  }

  /**
   * Reinitialize the tree.
   */
  public void clear() {
    root = null;
    size = 0;
  }

  /**
   * Return the number of elements in the tree.
   *
   * @return Size of the tree.
   */
  public int length() {
    return size;
  }

  /**
   * Insert a new item node into the tree.
   *
   * @param item An element to be stored in the tree.
   */
  public void insert(E item) {
    root = insert(root, item);
    size++;
  }

  /**
   * Traverse the tree in in-order, and execute specific task for each element encountered.
   *
   * @param action A task to be executed for each element.
   */
  public void inorder(Consumer<E> action) {
    inorder(root, action);
  }

  /**
   * An internal algorithm for storing a new element into a binary tree object.
   * <br>
   * For each element, which supports comparison, start traverse from the root node of the tree
   * and compare the new element with the one in the root node.
   * <br>
   * For each comparison, if the new element is greater than or equal to the one in the node,
   * execute same comparison for the righthand child node,
   * else if, execute the comparison for the lefthand child node.
   * <br>
   * For the base case condition, if the node to be compared is null, then create a new node with the element
   * and hand over the reference to the new node.
   *
   * @param node A node to be compared with a new element.
   * @param item A new element to be stored into a binary tree.
   * @param <E> A comparable element to be stored into the tree.
   * @return The node itself after executing all the proper algorithm.
   */
  private static <E extends Comparable<E>>
  BinNode<E> insert(BinNode<E> node, E item) {

    if (node == null)
      node = new BinNode<>(item);
    else if (item.compareTo(node.element()) < 0)
      node.setLeft(insert(node.left(), item));
    else if (item.compareTo(node.element()) >= 0)
      node.setRight(insert(node.right(), item));

    return node;
  }

  /**
   * Execute action for each element in node in in-order, sequentially.
   *
   * @param node A node whose element to be processed by specific task.
   * @param action A task to be executed for an element.
   * @param <E> A comparable element to be stored into the tree.
   */
  private static <E extends Comparable<E>>
  void inorder(BinNode<E> node, Consumer<E> action) {
    if (node == null)
      return;

    inorder(node.left(), action);
    action.accept(node.element());
    inorder(node.right(), action);
  }
}


