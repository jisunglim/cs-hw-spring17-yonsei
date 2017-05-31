package edu.yonsei.csi2103.bintree;

/**
 * CSI2103-01 Data structures
 * Programming Homework #1
 *
 * Binary node implemented as a part of the implementation of {@link BinTree}.
 *
 * Created by Jisung Lim on 11/03/2017.
 */
public class BinNode<E extends Comparable<E>> {

  /**
   * A comparable element.
   */
  final private E element;

  /**
   * Two child node, which is conceptually posed left and right.
   */
  private BinNode<E> left;
  private BinNode<E> right;

  /** Constructor */
  public BinNode(E el) {
    left = right = null;
    this.element = el;
  }

  // Getter and Setter
  public E element() {
    return element;
  }

  public BinNode<E> left() {
    return left;
  }

  public void setLeft(BinNode<E> left) {
    this.left = left;
  }

  public BinNode<E> right() {
    return right;
  }

  public void setRight(BinNode<E> right) {
    this.right = right;
  }

}
