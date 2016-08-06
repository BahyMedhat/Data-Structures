package eg.edu.alexu.csd.filestructure.sort;

public class MySort<T extends Comparable<T>> implements ISort<T> {

  public final IHeap<T> heapSort(final java.util.ArrayList<T> unordered) {

    HeapSort<T> sort = new HeapSort<T>();
    MyHeap<T> heap = new MyHeap<T>();
    heap = (MyHeap<T>) sort.heapSort(unordered);
    heap.setSize(unordered.size());
    return heap;
  }

  public final void sortSlow(final java.util.ArrayList<T> unordered) {

    BubbleSort<T> bubble = new BubbleSort<T>();
    bubble.bubbleSort(unordered);
  }

  public final void sortFast(final java.util.ArrayList<T> unordered) {

    QuickSort<T> quick = new QuickSort<T>();
    quick.quickSort(unordered, 0, unordered.size() - 1);
  }

}
