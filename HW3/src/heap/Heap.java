package heap;

/**
 * Created by jaylim on 06/04/2017.
 */
public class Heap<E extends Comparable<E>> {

  private final static int ROOT = 1;

  private E[] hArray;
  private int curSize;

  public Heap(E[] hArr, int cur) {
    hArray = hArr;
    curSize = cur;

    if (cur != 0) {
      Heapify();
    }
  }

  private void Heapify() {
    for (int i = 1; i <= curSize / 2; i++) {
      downHeap(i);
    }
  }

  public int length() {
    return curSize;
  }

  public void enqueue(E el) {
    hArray[++curSize] = el;
    upHeap();
  }

  public E dequeue() {
    swap(ROOT, curSize--);
    downHeap();
    return hArray[curSize+1];
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

  private void swap(int i, int j) {
    E temp = hArray[i];
    hArray[i] = hArray[j];
    hArray[j] = temp;
  }

  private boolean priority(int high, int low) {
    return hArray[high].compareTo(hArray[low]) > 0;
  }
}

