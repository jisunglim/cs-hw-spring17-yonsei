package queue;

/**
 * Created by jaylim on 07/04/2017.
 */
public class ArrayQueue<E> implements Queue<E> {
  private static final int DEFAULT_SIZE = 20;
  private int max;
  private int f;
  private int r;
  private E[] A;

  public ArrayQueue() {
    this(DEFAULT_SIZE);
  }

  public ArrayQueue(int size) {
    max = size + 1;
    f = 1;
    r = 0;

    //noinspection unchecked
    A = (E[]) new Object[max];
  }

  public void clear() {
    f = 1;
    r = 0;
  }

  public void enqueue(E item) {
    if ( (r + 2) % max == f % max) {
      System.out.println("Queue is full");
      return;
    }
    r = (r + 1) % max;
    A[r] = item;
  }

  public E dequeue() {
    if (length() == 0) {
      System.out.println("Queue is empty");
      return null;
    }
    E tempEl = A[f];
    f = (f + 1) % max;
    return tempEl;
  }

  public E frontValue() {
    if (length() == 0) {
      System.out.println("Queue is empty");
      return null;
    }
    return A[f];
  }

  public int length() {
    return ((r + max) - f + 1) % max;
  }
}
