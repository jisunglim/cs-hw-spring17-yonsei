package queue;

/**
 * Created by jaylim on 07/04/2017.
 */
public interface Queue<E> {
  /**
   * Re-initialize the queue. The user is responsible for reclaiming the storage used by the queue elements.
   */
  public void clear();

  /**
   * Place an element at the rear of the queue.
   *
   * @param item The element being enqueued.
   */
  public void enqueue(E item);

  /** Remove and return element at the front of the queue. */
  public E dequeue();

  /** @return The front element. */
  public E frontValue();

  /** @return The number of elements in the queue. */
  public int length();


}
