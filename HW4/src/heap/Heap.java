package heap;

import java.util.Comparator;

/**
 * Heap object which provides
 * 1. User-defined priority
 * 2. O(1) access and update
 *
 * To specify the implementation to the homework assignment,
 * make the generic E to contain integer Key field.
 *
 * Created by jaylim on 06/04/2017.
 */
public class Heap<E extends Key> {

  private final static int ROOT = 1;  // Root index

  private E[] hArray;     // array containing elements
  private int curSize;    // current size of the heap (the # of elements in the heap)

  private Comparator<E> cp;   // Comparator to support user-defined priority

  private int[] accessHelper; // AccessHelper to support O(1) access and update

  /** Constructor */
  public Heap(E[] hArr, int cur, Comparator<E> comparator, int maxKey) {
    hArray = hArr;
    curSize = cur;

    if (cur != 0) {
      Heapify();
    }

    cp = comparator;

    accessHelper = new int[maxKey];
  }

  /** Constructor */
  public Heap(E[] hArr, int cur, int maxKey) {
    hArray = hArr;
    curSize = cur;

    if (cur != 0) {
      Heapify();
    }

    cp = Comparator.comparingInt(Key::key);

    accessHelper = new int[maxKey];
  }

  /**
   *
   * @param item to be checked whether the item is in the heap or not.
   * @return If any, return the index of the item, if not, return the vacuous index 0.
   */
  private int contains(E item) {
    if (item == null) return 0;  // return the vacuous index
    return accessHelper[item.key()];
  }

  /**
   * Update the existing item with new one which has the same key.
   *
   * @param item new item.
   * @param idx Target index, index of item to be updated.
   */
  private void update(E item, int idx) {
    if (hArray[idx].key() != item.key()) {
      System.out.println("Error: update()");
      System.out.println(hArray[idx]);
      System.out.println(item);
      return;
    }
    hArray[idx] = item;
  }

  /** Heapify */
  private void Heapify() {
    for (int i = 1; i <= curSize / 2; i++) {
      downHeap(i);
    }
  }

  /** Peek the top element in the priority queue */
  public E peek() {
    if (isEmpty()) return null;

    return hArray[ROOT];
  }

  /** return the length */
  public int length() {
    return curSize;
  }

  public boolean isEmpty() {
    return curSize == 0;
  }

  public boolean isFull() {
    return hArray.length == curSize;
  }

  /**
   * Enqueue the element into the heap.
   * If already exist in the heap, just update it.
   *
   * @param el Element to be enqueued.
   */
  public void enqueue(E el) {
    if (isFull()) return;

    int idx;
    if ((idx = contains(el)) > 0) {
      update(el, idx);
      return;
    }

    hArray[++curSize] = el;
    accessHelper[el.key()] = curSize;
    upHeap();
  }

  /**
   * Dequeue the element from the heap.
   * If doesn't exist in the heap, just return null.
   *
   * @return the element to be deleted. If doesn't exist, return null.
   */
  public E dequeue() {
    if (isEmpty()) return null;

    swap(ROOT, curSize--);
    downHeap();

    E temp = hArray[curSize+1];
    accessHelper[temp.key()] = 0;
    return temp;
  }

  private void downHeap() {
    downHeap(ROOT);
  }

  private void downHeap(int current) {
    if (current * 2 > curSize) return;

    int child = current * 2;
    if (child < curSize && priority(child + 1, child)) child++;
    if (priority(child, current)) {
      swap(child, current);
      current = child;
      downHeap(current);
    }
  }

  private void upHeap() {
    upHeap(curSize);
  }

  private void upHeap(int current) {
    if (current == 1) return;

    int parent = current / 2;
    if (priority(current, parent)) {
      swap(current, parent);
      current = parent;
      upHeap(current);
    }
  }

  /**
   * Swapping two element in the i and j position in the hArray.
   * @param i
   * @param j
   */
  private void swap(int i, int j) {
    E temp = hArray[i];
    hArray[i] = hArray[j];
    hArray[j] = temp;

    accessHelper[hArray[i].key()] = i;
    accessHelper[hArray[j].key()] = j;
  }

  /**
   *
   * @param high
   * @param low
   * @return true if high > low, false if high <= low.
   */
  private boolean priority(int high, int low) {
    return cp.compare(hArray[high], hArray[low]) > 0;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("[ ");
    for (int i = 1; i <= curSize; i++) {
      sb.append(hArray[i]).append(" ");
    }
    sb.append("]");
    return sb.toString();
  }

  public String helper() {
    StringBuilder sb = new StringBuilder("[ ");
    for (int i = 0; i <= 10; i++) {
      sb.append(accessHelper[i]).append(" ");
    }
    sb.append("]");
    return sb.toString();
  }
}

