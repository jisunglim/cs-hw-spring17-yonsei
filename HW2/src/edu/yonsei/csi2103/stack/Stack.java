package edu.yonsei.csi2103.stack;

/**
 * Created by jaylim on 19/03/2017.
 */
public interface Stack<E> {

  int DEFAULT_SIZE = 100;

  /**
   * Remove an element at the (top-1) of the stack, and decrease top by 1.
   *
   * @return An element to be removed from the stack.
   */
  E pop();

  /**
   * Seek to the element at the (top-1) of the stack.
   *
   * @return An element at the (top-1) of the stack.
   */
  E peek();

  /**
   * Push a new item at the top of the stack, and increase top by 1.
   *
   * @param item A new element to be pushed at the top of the stack.
   */
  void push(E item);

  /**
   * Return true if the stack is empty, elsewhere return false.
   */
  boolean isEmpty();

  /**
   * Return true if the stack is full, elsewhere return false.
   */
  boolean isFull();

  /**
   * Return current length of the stack.
   */
  int length();

}
