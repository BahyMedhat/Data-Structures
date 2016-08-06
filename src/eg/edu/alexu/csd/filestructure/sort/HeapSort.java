package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;

public class HeapSort<T extends Comparable<T>> {

  public final IHeap<T> heapSort(final ArrayList<T> unordered) {

    IHeap<T> heap = new MyHeap<T>();
    heap.build(unordered);
    int size = heap.size();
    for (int i = size - 1; i >= 0; i--) {
      unordered.set(i, heap.extract());
    }
    return heap;
  }
}
