package edu.yonsei.csi2103.stack;

/**
 * Created by jaylim on 19/03/2017.
 */
public class ArrayStack<E> implements Stack<E> {

  /** Internal generic type array to store elements */
  private E[] A;

  /** Maximum size of stack. */
  private int size;

  /** Index for top of the stack. */
  private int top;

  /** Default constructor. */
  public ArrayStack() {
    this.A = (E[]) new Object[DEFAULT_SIZE];
    this.size = DEFAULT_SIZE;

    this.top = 0;
  }

  /** Constructor with size argument. */
  public ArrayStack(int size) {
    this.A = (E[]) new Object[size];
    this.size = size;

    this.top = 0;
  }

  /** Re-initialization method. */
  public void init() {
    this.top = 0;
  }

  @Override
  public E pop() {
    if (isEmpty()) throw new IllegalStateException("There is no item in the stack.");
    return A[--top];
  }

  @Override
  public E peek() {
    if (isEmpty()) throw new IllegalStateException("There is no item in the stack.");
    return A[top-1];
  }

  @Override
  public void push(E item) {
    if (isFull()) throw new IllegalStateException("The stack is full.");
    A[top++] = item;
  }

  @Override
  public boolean isEmpty() {
    return top == 0;
  }

  @Override
  public boolean isFull() {
    return size == top;
  }

  @Override
  public int length() {
    return top;
  }
}
