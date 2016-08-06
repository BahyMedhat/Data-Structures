package eg.edu.alexu.csd.filestructure.avl;

public class MyAVLTree<T extends Comparable<T>> implements IAVLTree<T> {

  private MyNode root;
  private int size;
  private boolean deleted;

  public MyAVLTree() {

    root = null;
    size = 0;
    deleted = false;
  }

  @Override
  public void insert(T key) {
    // TODO Auto-generated method stub

    root = insert((MyNode) getTree(), key);
    size++;
  }

  @Override
  public boolean delete(T key) {
    // TODO Auto-generated method stub

    deleted = false;
    root = delete((MyNode) getTree(), key);
    if (deleted) {
      size--;
    }
    return deleted;
  }

  @Override
  public boolean search(T key) {
    // TODO Auto-generated method stub

    return search((MyNode) getTree(), key);
  }

  @Override
  public int height() {
    // TODO Auto-generated method stub

    return (size == 0) ? 0 : root.height;
  }

  @Override
  public INode<T> getTree() {
    // TODO Auto-generated method stub

    return (size == 0) ? null : root;
  }

  public int getSize() {

    return size;
  }

  private int height(MyNode node) {

    return (node == null) ? 0 : node.height;
  }

  private MyNode rotateWithLeftChild(MyNode nodeX) {

    MyNode nodeY = nodeX.left;
    nodeX.left = nodeY.right;
    nodeY.right = nodeX;
    nodeX.height = Math.max(height(nodeX.left), height(nodeX.right)) + 1;
    nodeY.height = Math.max(height(nodeY.left), height(nodeY.right)) + 1;
    return nodeY;
  }

  private MyNode rotateWithRightChild(MyNode nodeX) {

    MyNode nodeY = nodeX.right;
    nodeX.right = nodeY.left;
    nodeY.left = nodeX;
    nodeX.height = Math.max(height(nodeX.left), height(nodeX.right)) + 1;
    nodeY.height = Math.max(height(nodeY.left), height(nodeY.right)) + 1;
    return nodeY;
  }

  private MyNode balance(MyNode node) {

    if (node == null) {
      return null;
    }

    if (height(node.left) - height(node.right) > 1) {
      // Left Left Case
      if (height(node.left.left) >= height(node.left.right)) {
        node = rotateWithLeftChild(node);
      }
      // Left Right Case
      else {
        node.left = rotateWithRightChild(node.left);
        node = rotateWithLeftChild(node);
      }
    }

    else {
      if (height(node.right) - height(node.left) > 1) {
        // Right Right Case
        if (height(node.right.right) >= height(node.right.left)) {
          node = rotateWithRightChild(node);
        }
        // Right Left Case
        else {
          node.right = rotateWithLeftChild(node.right);
          node = rotateWithRightChild(node);
        }
      }
    }
    node.height = Math.max(height(node.left), height(node.right)) + 1;
    return node;
  }

  private MyNode insert(MyNode node, T key) {

    if (node == null) {
      return (new MyNode(key));
    }

    if (key.compareTo(node.key) < 0) {
      node.left = insert(node.left, key);
    }

    else {
      node.right = insert(node.right, key);
    }
    return balance(node);
  }

  private boolean search(MyNode node, T key) {

    if (node == null) {
      return false;
    }

    if (key.compareTo(node.key) < 0) {
      return search(node.left, key);
    }

    else if (key.compareTo(node.key) > 0) {
      return search(node.right, key);
    }

    else {
      return true;
    }
  }

  private MyNode treeMin(MyNode node) {

    while (node.left != null) {
      node = node.left;
    }
    return node;
  }

  private MyNode delete(MyNode node, T key) {

    if (node == null) {
      return null;
    }

    int compareResult = key.compareTo(node.key);

    if (compareResult < 0) {
      node.left = delete(node.left, key);
    }

    else if (compareResult > 0) {
      node.right = delete(node.right, key);
    }

    else if (node.left != null && node.right != null) {
      deleted = true;
      node.key = treeMin(node.right).key;
      node.right = delete(node.right, node.key);
    }

    else {
      deleted = true;
      node = (node.left != null) ? node.left : node.right;
    }
    return balance(node);
  }

  private class MyNode implements INode<T> {

    private T key;
    private MyNode left;
    private MyNode right;
    private int height;

    MyNode(final T value) {

      this.key = value;
      left = null;
      right = null;
      height = 1;
    }

    @Override
    public final INode<T> getLeftChild() {
      // TODO Auto-generated method stub

      return null;
    }

    @Override
    public final INode<T> getRightChild() {
      // TODO Auto-generated method stub

      return null;
    }

    @Override
    public final T getValue() {
      // TODO Auto-generated method stub

      return key;
    }

    @Override
    public final void setValue(final T value) {
      // TODO Auto-generated method stub

      this.key = value;
    }

  }

}
