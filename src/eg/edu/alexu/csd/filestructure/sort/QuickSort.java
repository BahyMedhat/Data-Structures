package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;

public class QuickSort<T extends Comparable<T>> {

  private ArrayList<T> arr;

  public final void quickSort(final ArrayList<T> input, final int low,
      final int high) {

    if (low < high) {
      arr = input;
      int index = partition(arr, low, high);
      quickSort(arr, low, index - 1);
      quickSort(arr, index + 1, high);
    }
  }

  private int partition(final ArrayList<T> arr, final int low,
      final int high) {

    T pivot = arr.get(high);
    int iter = low - 1;
    for (int j = low; j < high; j++) {
      if (arr.get(j).compareTo(pivot) <= 0) {
        iter++;
        swap(iter, j);
      }
    }
    swap(iter + 1, high);
    return iter + 1;
  }

  private void swap(final int first, final int second) {
    T temp = arr.get(first);
    arr.set(first, arr.get(second));
    arr.set(second, temp);
  }

}
