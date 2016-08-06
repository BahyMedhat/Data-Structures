package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;

public class BubbleSort<T extends Comparable<T>> {

  public final void bubbleSort(final ArrayList<T> unordered) {

    int size = unordered.size();
    for (int i = 0; i < size - 1; i++) {
      for (int j = 0; j < size - i - 1; j++) {
        if (unordered.get(j).compareTo(unordered.get(j + 1)) == 1) {
          T swap = unordered.get(j);
          unordered.set(j, unordered.get(j + 1));
          unordered.set(j + 1, swap);
        }
      }
    }
  }
}
