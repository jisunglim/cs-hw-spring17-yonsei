package heap;

import java.lang.reflect.Array;

/**
 * Created by jaylim on 03/05/2017.
 */
public class MedianHeap<E extends Key> {

  public Heap<E> lHeap; // lowerHeap
  public Heap<E> uHeap; // upperHeap


  /**
   * Construct to heap with same size.
   *
   * There are two cases:
   *  case 1) l: k, u: k     (bf = 0)
   *  case 2) l: k, u: k-1   (bf = 1)
   *
   * @param size The size of the heap
   * @param klass The class type of the element
   * @param maxKey The maximum value of the integer key.
   */
  @SuppressWarnings("unchecked")
  public MedianHeap(int size, Class<E> klass, int maxKey) {
    int lSize = size - size/2;
    int uSize = size/2;

    E[] lArray = (E[]) Array.newInstance(klass, lSize);
    E[] uArray = (E[]) Array.newInstance(klass, uSize);

    // Construct two heap; lower heap and upper heap.
    lHeap = new Heap<>(lArray, 0, (o1, o2) ->   (o1.key() - o2.key()), maxKey); // lowerHeap := maxHeap
    uHeap = new Heap<>(uArray, 0, (o1, o2) -> - (o1.key() - o2.key()), maxKey); // upperHeap := minHeap
  }

  /** Peek the root of the median heap */
  public E root() {
    return lHeap.peek();
  }

  /** Enqueue the element into the median heap. */
  public void enqueue(E item) {
    // Nullity
    if (item == null) return;

    // Full
    if (lHeap.isFull() && uHeap.isFull()) return;

    // Balance factor
    int bf = lHeap.length() - uHeap.length();

    if (bf == 0) { // l: k // u: k
      uHeap.enqueue(item);
      if (lHeap.length() - uHeap.length() == -1)
        lHeap.enqueue(uHeap.dequeue());
    } else if (bf == 1) { // l: k // u: k - 1
      lHeap.enqueue(item);
      if (lHeap.length() - uHeap.length() == 2)
        uHeap.enqueue(lHeap.dequeue());
    }
  }

  /** Dequeue the element from the median heap. */
  public E dequeue() {
    // Empty
    if (lHeap.isEmpty()) return null;

    E temp = lHeap.dequeue();
    balancing();
    return temp;
  }

  /**
   * Balancing the median heap.
   *
   * Valid case.
   * case 1) l: k, u: k     (bf = 0)
   * case 2) l: k, u: k-1   (bf = 1)
   */
  public void balancing() {
    // Balance factor
    int bf = lHeap.length() - uHeap.length();

    if (bf < 0) { // l: k // u: k+1
      lHeap.enqueue(uHeap.dequeue());
    } else if (bf > 1) { // l: k // u: k - 2
      uHeap.enqueue(lHeap.dequeue());
    }

  }
}
