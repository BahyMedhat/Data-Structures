package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collection;

public class MyHeap<T extends Comparable<T>> implements IHeap<T> {

  private ArrayList<INode<T>> heap = new ArrayList<INode<T>>();
  private int size = 0;

  @Override
  public final INode<T> getRoot() {
    // TODO Auto-generated method stub

    if (size <= 0) {
      return null;
    }
    return heap.get(0);
  }

  @Override
  public final int size() {
    // TODO Auto-generated method stub

    return size;
  }

  @Override
  public final void heapify(final INode<T> node) {
    // TODO Auto-generated method stub

    INode<T> left = node.getLeftChild();
    INode<T> right = node.getRightChild();
    INode<T> largest = null;
    if (left != null && left.getValue().compareTo(node.getValue()) > 0) {
      largest = left;
    } else {
      largest = node;
    }
    if (right != null && right.getValue().compareTo(largest.getValue()) > 0) {
      largest = right;
    }
    if (!largest.equals(node)) {
      swap(node, largest);
      heapify(largest);
    }
  }

  @Override
  public final T extract() {
    // TODO Auto-generated method stub

    if (size <= 0) {
      return null;
    }
    T max = getRoot().getValue();
    swap(getRoot(), heap.get(size - 1));
    size--;

    if (size > 0) {
      heapify(getRoot());
    }
    return max;
  }

  @Override
  public final void insert(final T element) {
    // TODO Auto-generated method stub

    if (size == heap.size()) {
      heap.add(new MyNode(element, size));
    } else {
      heap.set(size, new MyNode(element, size));
    }
    size++;
    int iter = size - 1;

    while (iter > 0
        && heap.get(iter).getValue()
            .compareTo(heap.get(iter).getParent().getValue()) > 0) {
      swap(heap.get(iter), heap.get(iter).getParent());
      iter = (iter - 1) / 2;
    }
  }

  @Override
  public final void build(final Collection<T> unordered) {
    // TODO Auto-generated method stub

    size = unordered.size();
    int iter = 0;
    for (T value : unordered) {
      heap.add(new MyNode(value, iter));
      iter++;
    }
    for (int i = (size - 1) / 2; i >= 0; i--) {
      if (size > 0) {
        heapify(heap.get(i));
      }
    }
  }

  public final void setSize(final int size) {

    this.size = size;
  }

  private void swap(final INode<T> first, final INode<T> second) {
    T temp = first.getValue();
    first.setValue(second.getValue());
    second.setValue(temp);
  }

  private class MyNode implements INode<T> {

    private int index;
    private T value;

    MyNode(final T value, final int index) {
      this.value = value;
      this.index = index;
    }

    @Override
    public final INode<T> getLeftChild() {
      // TODO Auto-generated method stub

      int ind = 2 * index + 1;
      if (ind >= size || ind < 0) {
        return null;
      }
      return heap.get(ind);
    }

    @Override
    public final INode<T> getRightChild() {
      // TODO Auto-generated method stub

      int ind = 2 * index + 2;
      if (ind >= size || ind < 0) {
        return null;
      }
      return heap.get(ind);
    }

    @Override
    public final INode<T> getParent() {
      // TODO Auto-generated method stub

      int ind = (index - 1) / 2;
      if (ind >= size || ind < 0) {
        return null;
      }
      return heap.get(ind);
    }

    @Override
    public final T getValue() {
      // TODO Auto-generated method stub

      return value;
    }

    @Override
    public final void setValue(final T value) {
      // TODO Auto-generated method stub

      this.value = value;
    }

  }

}
